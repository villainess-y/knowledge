package LeetCode.common;

/**
 * description
 *
 * @author wangzhongyu 2022/01/17 11:08 AM
 **/

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode setAndGetNext(ListNode next) {
        this.next = next;
        return this.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode root = this;
        while (root != null) {
            sb.append(root.val + " ---> ");
            root = root.next;
        }
        return sb.substring(0, sb.length() - 6);
    }
}
