import java.io.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s){ String[] p=s.split(":"); return Integer.parseInt(p[0])*60+Integer.parseInt(p[1]); }
    static String toHHMM(int m){ int h=m/60; m%=60; return String.format("%02d:%02d",h,m); }

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        int[] t=new int[n];
        for(int i=0;i<n;i++) t[i]=toMin(br.readLine().trim());
        int q=toMin(br.readLine().trim());
        int lo=0,hi=n; // first > q
        while(lo<hi){
            int mid=(lo+hi)>>>1;
            if(t[mid]<=q) lo=mid+1; else hi=mid;
        }
        if(lo==n) System.out.println("No bike");
        else System.out.println(toHHMM(t[lo]));
    }
}
