package LeetCode;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * 示例 1：
 * <p>
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangzhongyu 2022/01/18 10:18 AM
 */
public class LC_539 {

    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList(new String[]{"23:59", "00:00"});
        System.out.println(findMinDifference(timePoints));
    }

    public static int findMinDifference(List<String> timePoints) {
        if (timePoints.size() < 2 || timePoints.size() > 1440) {
            return 0;
        }
        // 转换成分钟
        int[] minList = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] time = timePoints.get(i).split(":");
            minList[i] = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
        }
        Arrays.sort(minList);
        int result = Integer.MAX_VALUE, len = minList.length;
        for (int i = 0; i < len - 1; i++) {
            result = Math.min(result, (minList[i + 1] - minList[i]));
        }
        return Math.min(result, (1440 - minList[len - 1] + minList[0]));
    }

}
