package LeetCode;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangzhongyu 2022/01/17 4:00 PM
 */
public class LC_3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));

    }

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        // 记录该字符上次出现的index
        int[] last = new int[256];
        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }
        int left = 0, result = 0;
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i);
            // 判断这个字符是否重复出现，第一次出现的是为0，安全，left则不需要变;
            left = Math.max(left, last[c] + 1);
            // 判断result是否更新(长度)
            result = Math.max(result, i - left + 1);
            // 修正这个char的出现的坐标
            last[c] = i;
        }
        return result;
    }
}
