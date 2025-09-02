// 題目：12. Integer to Roman
// 將 1..3999 的整數轉為羅馬數字。
// 解法：貪心，把值由大到小的 (value, symbol) 表列出（含 4、9 的減法表示），能扣多少扣多少。
// 時間複雜度 O(1)（表固定），空間 O(1)

public class lt_12_intToRoman {
    private static final int[] VAL = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] SYM = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VAL.length; i++) {
            while (num >= VAL[i]) {
                num -= VAL[i];
                sb.append(SYM[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3749)); // MMMDCCXLIX
        System.out.println(intToRoman(58));   // LVIII
        System.out.println(intToRoman(1994)); // MCMXCIV
    }
}
/*
解題思路：
1. 以降序列舉所有合法羅馬片段（含 IV, IX, XL, XC, CD, CM）。
2. 每次能減就減，並將符號加入答案。
*/
