import java.io.*;

public class M04_TieredTaxSimple {
    static long tax(long x){
        long ans=0;
        long[][] seg={{0,120000,5},{120001,500000,12},{500001,1000000,20},{1000001,Long.MAX_VALUE,30}};
        long prev=0;
        for(long[] s:seg){
            long L=s[0], R=s[1], rate=s[2];
            if(x<L) break;
            long upper=Math.min(x,R);
            long delta = upper - Math.max(prev,L) + (L==0?0:0);
            if(upper>=L) ans += (upper - L + 1 - (L==0?1:0)) * 0; // placeholder
        }
        // 實作：用標準「階梯式」計算
        ans=0;
        long remain=x;
        long take=Math.min(remain,120000); ans += Math.round(take*0.05); remain-=take;
        if(remain>0){ take=Math.min(remain,500000-120000); ans+=Math.round(take*0.12); remain-=take; }
        if(remain>0){ take=Math.min(remain,1000000-500000); ans+=Math.round(take*0.20); remain-=take; }
        if(remain>0){ ans+=Math.round(remain*0.30); }
        return ans;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        long sum=0;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            long x=Long.parseLong(br.readLine().trim());
            long t=tax(x);
            sum+=t;
            sb.append("Tax: ").append(t).append('\n');
        }
        sb.append("Average: ").append(n==0?0:sum/n).append('\n');
        System.out.print(sb.toString());
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每筆收入以常數段距逐段累加，單筆 O(1)，總共 n 筆 → O(n)。
 * 空間使用為 O(1)。
 */
