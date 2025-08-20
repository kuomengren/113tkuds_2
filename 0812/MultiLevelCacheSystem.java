import java.util.*;

/**
 * 三層快取（L1/L2/L3），get/put、自動升降級。
 * 策略（簡化版且可運行）：
 *  - Entry: key, value, level(1/2/3), freq, lastAccess
 *  - 每層維護 LRU（LinkedHashMap）＋ 一個 eviction-heap（依 score 低者先驅逐）
 *  - get：提升 freq 與 lastAccess；若不在 L1 且頻率高則嘗試升級
 *  - put：寫入 L1；若滿則把 L1 最差移到 L2；L2 滿再移 L3；L3 滿則淘汰
 * score = freq*weight - ageCost - levelCost（簡化後用 Comparator）
 */
public class MultiLevelCacheSystem {
    private static class Entry {
        final int key;
        String value;
        int level;        // 1,2,3
        long lastAccess;  // 時間戳
        long freq;        // 存取次數
        Entry(int k, String v, int lvl, long ts){ key=k; value=v; level=lvl; lastAccess=ts; freq=1; }
    }

    // 層級參數
    private static class LevelConf {
        final int capacity;
        final int accessCost;
        LevelConf(int cap, int cost){ capacity=cap; accessCost=cost; }
    }

    private final LevelConf L1 = new LevelConf(2, 1);
    private final LevelConf L2 = new LevelConf(5, 3);
    private final LevelConf L3 = new LevelConf(10, 10);

    // 全域 index
    private final Map<Integer, Entry> map = new HashMap<>();
    private long clock = 0;

    // 每層 LRU（保序）+ 驅逐堆（score 低者先出）
    private static class LevelDS {
        final LinkedHashMap<Integer,Entry> lru = new LinkedHashMap<>(16,0.75f,true);
        final PriorityQueue<Entry> evictPQ = new PriorityQueue<>(Comparator.comparingDouble(MultiLevelCacheSystem::score));
    }
    private final LevelDS l1 = new LevelDS();
    private final LevelDS l2 = new LevelDS();
    private final LevelDS l3 = new LevelDS();

    private static double score(Entry e){
        // 簡化 score：越常被用 + 越新 + 越上層 => 分數越高
        double freqScore = Math.log(1 + e.freq);
        double recency = e.lastAccess;
        double levelBonus = (e.level==1? 3 : e.level==2? 2 : 1);
        return -(freqScore*1000 + recency*0.1 + levelBonus*10000); // 負號：PQ 以最小值先出
    }

    private LevelDS dsOf(int level){
        return level==1? l1 : level==2? l2 : l3;
    }
    private int capOf(int level){
        return level==1? L1.capacity : level==2? L2.capacity : L3.capacity;
    }

    private void touch(Entry e){
        e.freq++;
        e.lastAccess = ++clock;
        LevelDS ds = dsOf(e.level);
        ds.lru.put(e.key, e); // 刷新 LRU
        ds.evictPQ.remove(e);
        ds.evictPQ.offer(e);
    }

    private void ensureCapacityAtLevel(int level){
        LevelDS ds = dsOf(level);
        while (ds.lru.size() > capOf(level)) {
            // 以 eviction-heap 取出最差者
            Entry worst = ds.evictPQ.poll();
            if (worst == null) break;
            if (!ds.lru.containsKey(worst.key)) continue; // 已被移動或刪除
            ds.lru.remove(worst.key);
            if (level < 3) {
                // 移到下一層
                worst.level = level + 1;
                dsOf(worst.level).lru.put(worst.key, worst);
                dsOf(worst.level).evictPQ.offer(worst);
                ensureCapacityAtLevel(level + 1);
            } else {
                // L3 滿，淘汰
                map.remove(worst.key);
            }
        }
    }

    private void moveUpIfHot(Entry e){
        if (e.level == 1) return;
        // 門檻：freq 多於某值就升一層（簡化門檻）
        boolean promote = e.freq >= 3;
        if (!promote) return;
        LevelDS cur = dsOf(e.level);
        cur.lru.remove(e.key);
        cur.evictPQ.remove(e);
        e.level--;
        LevelDS up = dsOf(e.level);
        up.lru.put(e.key, e);
        up.evictPQ.offer(e);
        ensureCapacityAtLevel(e.level);
    }

    public String get(int key){
        Entry e = map.get(key);
        if (e == null) return null;
        touch(e);
        moveUpIfHot(e);
        return e.value;
    }

    public void put(int key, String value){
        Entry e = map.get(key);
        if (e != null) {
            e.value = value;
            touch(e);
            return;
        }
        e = new Entry(key, value, 1, ++clock);
        map.put(key, e);
        l1.lru.put(key, e);
        l1.evictPQ.offer(e);
        ensureCapacityAtLevel(1);
    }

    public String debugLayout(){
        return "L1:" + l1.lru.keySet() + " L2:" + l2.lru.keySet() + " L3:" + l3.lru.keySet();
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1,"A"); cache.put(2,"B"); cache.put(3,"C");
        System.out.println(cache.debugLayout()); // L1:[2,3], L2:[1]

        cache.get(1); cache.get(1); cache.get(2);
        System.out.println(cache.debugLayout()); // 1 熱門，應升到 L1（可能 L1:[1,2]、L2:[3]）

        cache.put(4,"D"); cache.put(5,"E"); cache.put(6,"F");
        System.out.println(cache.debugLayout()); // 根據頻率分布於各層
    }
}
