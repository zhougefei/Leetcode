class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode cur = head;
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (head == cur) {
                pre = head;
                head = head.next;
            } else {
                head = cur.next;
                pre.next = head;
            }
        }
        return dummy.next;
    }
}