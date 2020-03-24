/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // Just use the normal Queue merge each 2 lists
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (lists == null || n == 0) {
            return null;
        }
        Queue<ListNode> data = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            data.add(lists[i]);
        }
        while (data.size() > 1) {
            ListNode p = data.poll();
            ListNode q = data.poll();
            ListNode newOne = mergeTwoListNode(p, q);
            data.add(newOne);
        }
        return data.poll();
    }
    
    private ListNode mergeTwoListNode(ListNode p, ListNode q) {
        ListNode dummy = new ListNode(0), head = dummy;
        while (p != null && q != null) {
            if (p.val < q.val) {
                head.next = new ListNode(p.val);
                p = p.next;
            } else {
                head.next = new ListNode(q.val);
                q = q.next;
            }
            head = head.next;
        }
        head.next = p == null ? q : p;
        return dummy.next;
    }
}

// Approach 2 : Use the Priority Queue

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b) {
                if (a.val < b.val) {
                    return -1;
                } else if (a.val == b.val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        for (ListNode header : lists) {
            if (header != null) {
                pq.add(header);
            }
        }
        ListNode head = new ListNode(0), dummy = head;
        while (!pq.isEmpty()) {
            ListNode top = pq.peek();
            top = top.next;
            head.next = pq.poll();
            head = head.next;
            if (top != null) {
                pq.add(top);
            }
        }
        return dummy.next;
    }
}