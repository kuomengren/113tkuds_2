import java.io.*;

public class M06_PalindromeClean {
    static boolean isLetter(char c){ return ('a'<=c && c<='z')||('A'<=c && c<='Z'); }
    static char norm(char c){ return (char)Character.toLowerCase(c); }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        int i=0,j=s.length()-1;
        while(i<j){
            char ci=s.charAt(i), cj=s.charAt(j);
            if(!isLetter(ci)){ i++; continue; }
            if(!isLetter(cj)){ j--; continue; }
            if(norm(ci)!=norm(cj)){ System.out.println("No"); return; }
            i++; j--;
        }
        System.out.println("Yes");
    }
}
