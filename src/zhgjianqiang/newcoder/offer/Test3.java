package zhgjianqiang.newcoder.offer;

import java.util.ArrayList;

public class Test3 {


    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> valList = new ArrayList<>();
        while (listNode != null) {
            valList.add(0, listNode.val);
            listNode = listNode.next;
        }
        return valList;
    }


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
