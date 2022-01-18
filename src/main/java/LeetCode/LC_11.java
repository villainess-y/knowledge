package LeetCode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * 8       |                   |
 * 7       |                   |       |
 * 6       |   |               |       |
 * 5       |   |       |       |       |
 * 4       |   |       |   |   |       |
 * 3       |   |       |   |   |   |   |
 * 2       |   |   |   |   |   |   |   |
 * 1   |   |   |   |   |   |   |   |   |
 * 0   1   2   3   4   5   6   7   8   9   10
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49:(2,7)(9,7)。
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Peter.Wang 2022/01/18 1:45 PM
 */
public class LC_11 {

    public static void main(String[] args) {
        int[] height = {1, 2, 1};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int area = 0, left = 0, right = height.length - 1;
        while (left < right) {
            // 高度只能是最低的
            int high = Math.min(height[left], height[right]);
            area = Math.max(area, high * (right - left));
            // 因为left与right都是向内收缩，所以容器宽变小，如果想面积变大，只能高越大越好
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return area;
    }
}
