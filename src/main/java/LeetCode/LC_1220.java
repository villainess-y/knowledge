package LeetCode;

/**
 * hard 
 * 
 * 给你一个整数n，请你帮忙统计一下我们可以按下述规则形成多少个长度为n的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音'a'后面都只能跟着'e'
 * 每个元音'e'后面只能跟着'a'或者是'i'
 * 每个元音'i'后面不能 再跟着另一个'i'
 * 每个元音'o'后面只能跟着'i'或者是'u'
 * 每个元音'u'后面只能跟着'a'
 * 由于答案可能会很大，所以请你返回 模10^9 + 7之后的结果。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 *
 * 输入：n = 5
 * 输出：68
 * 
 *
 * 提示：
 *
 * 1 <= n <= 2 * 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-vowels-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LC_1220 {

    /**
     *  由最后一位来往前推
     *  最终返回的计数总和 = 各个字母结尾的数量，相加。
     *  那么分别求各个字母结尾的数量。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(countVowelPermutation(5));
    }

    public static int countVowelPermutation(int n) {
        Long mod= 1000000007L;
        long a=1,o=1,e=1,i=1,u=1;
        for(int m = 2; m <= n;m++ ){
            // a前面可以是u,i,e
            long aa = (u+i+e) % mod;
            long ee = (a+i) % mod;
            long ii = (e + o) % mod;
            long oo = i % mod;
            long uu = (i+o)%mod;
            a = aa;
            e=ee;
            i=ii;
            o=oo;
            u=uu;
        }

        return (int) ((a+o+e+i+u)% mod);
    }
}
