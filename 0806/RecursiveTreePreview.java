import java.util.*;

public class RecursiveTreePreview {

    public static void main(String[] args) {
        // 1️⃣ 模擬資料夾樹狀結構計算檔案數
        Folder root = mockFileSystem();
        System.out.println("📁 總檔案數：" + countFiles(root));

        // 2️⃣ 遞迴列印多層選單
        System.out.println("\n📋 多層選單結構：");
        MenuItem menu = mockMenu();
        printMenu(menu, 0);

        // 3️⃣ 巢狀陣列展平
        Object[] nestedArray = {1, new Object[]{2, 3}, new Object[]{4, new Object[]{5, 6}}, 7};
        List<Object> flatList = new ArrayList<>();
        flattenArray(nestedArray, flatList);
        System.out.println("\n📦 展平後陣列：" + flatList);

        // 4️⃣ 巢狀清單最大深度
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4), 5), 6);
        System.out.println("\n🧱 巢狀清單最大深度：" + maxDepth(nestedList));
    }

    // -------------------------------------
    // 1️⃣ 遞迴計算總檔案數
    static class Folder {
        String name;
        List<Folder> subFolders = new ArrayList<>();
        List<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subFolders) {
            count += countFiles(sub);
        }
        return count;
    }

    public static Folder mockFileSystem() {
        Folder root = new Folder("root");
        root.files.add("file1.txt");
        root.files.add("file2.txt");

        Folder sub1 = new Folder("sub1");
        sub1.files.add("a.txt");
        sub1.files.add("b.txt");

        Folder sub2 = new Folder("sub2");
        Folder sub2_1 = new Folder("sub2_1");
        sub2_1.files.add("c.txt");

        sub2.subFolders.add(sub2_1);
        root.subFolders.add(sub1);
        root.subFolders.add(sub2);

        return root;
    }

    // -------------------------------------
    // 2️⃣ 遞迴列印選單結構
    static class MenuItem {
        String name;
        List<MenuItem> subItems = new ArrayList<>();

        MenuItem(String name) {
            this.name = name;
        }
    }

    public static void printMenu(MenuItem item, int indent) {
        System.out.println("  ".repeat(indent) + "• " + item.name);
        for (MenuItem sub : item.subItems) {
            printMenu(sub, indent + 1);
        }
    }

    public static MenuItem mockMenu() {
        MenuItem root = new MenuItem("主選單");
        MenuItem item1 = new MenuItem("檔案");
        MenuItem item2 = new MenuItem("編輯");
        MenuItem item3 = new MenuItem("檢視");

        item1.subItems.add(new MenuItem("新增"));
        item1.subItems.add(new MenuItem("開啟"));
        item1.subItems.add(new MenuItem("儲存"));

        item2.subItems.add(new MenuItem("復原"));
        item2.subItems.add(new MenuItem("剪下"));
        item2.subItems.add(new MenuItem("貼上"));

        MenuItem zoom = new MenuItem("縮放");
        zoom.subItems.add(new MenuItem("放大"));
        zoom.subItems.add(new MenuItem("縮小"));
        item3.subItems.add(zoom);

        root.subItems.add(item1);
        root.subItems.add(item2);
        root.subItems.add(item3);
        return root;
    }

    // -------------------------------------
    // 3️⃣ 巢狀陣列展平
    public static void flattenArray(Object[] input, List<Object> result) {
        for (Object obj : input) {
            if (obj instanceof Object[]) {
                flattenArray((Object[]) obj, result);
            } else {
                result.add(obj);
            }
        }
    }

    // -------------------------------------
    // 4️⃣ 巢狀清單最大深度
    public static int maxDepth(Object obj) {
        if (obj instanceof List<?>) {
            int max = 0;
            for (Object item : (List<?>) obj) {
                max = Math.max(max, maxDepth(item));
            }
            return max + 1;
        }
        return 0;
    }
}
