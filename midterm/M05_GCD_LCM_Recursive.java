import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a,long b){ return b==0?a:gcd(b,a%b); }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        long a=Long.parseLong(st.nextToken()), b=Long.parseLong(st.nextToken());
        long g=gcd(a,b);
        long l = (a/g)*b; // 先除後乘避免溢位
        System.out.println("GCD: "+g);
        System.out.println("LCM: "+l);
    }
}
/*
 * Time Complexity: O(log min(a,b))
 * 說明：遞迴歐幾里得演算法每次以取餘數縮小一個量級，次數與輸入值位數成正比。
 * 空間複雜度 O(log min(a,b))（遞迴堆疊）。
 */
