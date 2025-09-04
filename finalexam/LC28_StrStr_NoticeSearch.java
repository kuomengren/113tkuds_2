import java.io.*;

/** 公告全文搜尋：KMP 失敗函數 O(n+m) */
public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String hay=br.readLine(); if(hay==null) hay="";
        String nee=br.readLine(); if(nee==null) nee="";
        System.out.println(indexOf(hay, nee));
    }
    static int indexOf(String s,String p){
        if(p.length()==0) return 0;
        if(s.length()<p.length()) return -1;
        int[] lps=buildLPS(p);
        int i=0,j=0;
        while(i<s.length()){
            if(s.charAt(i)==p.charAt(j)){ i++; j++; if(j==p.length()) return i-j; }
            else if(j>0) j=lps[j-1];
            else i++;
        }
        return -1;
    }
    static int[] buildLPS(String p){
        int n=p.length(); int[] lps=new int[n];
        int len=0;
        for(int i=1;i<n;){
            if(p.charAt(i)==p.charAt(len)){ lps[i++]=++len; }
            else if(len>0) len=lps[len-1];
            else lps[i++]=0;
        }
        return lps;
    }
}
