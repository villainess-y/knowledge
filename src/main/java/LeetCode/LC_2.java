package LeetCode;

import LeetCode.common.ListNode;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 
 *
 * 示例 1：
 *  2  ----> 4 ----->3
 *  5  ----> 6 ----->4
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wangzhongyu 2022/01/17 11:07 AM
 */
public class LC_2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode current = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0){
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sum = l1Val + l2Val + carry;
            carry = sum/10;
            current.next = new ListNode(sum%10);
            current = current.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return root.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9))
                .setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9));
        ListNode l2 = new ListNode(9);
        l2.setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9)).setAndGetNext(new ListNode(9));
        System.out.println(addTwoNumbers(l1,l2));
    }

}
