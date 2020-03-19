package zhgjianqiang.newcoder.offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Test4 {

    //goodAnswer
    public TreeNode _reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }

        return root;
    }

    @Test
    public void test() {
        int[] pre = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] in = new int[]{3, 2, 4, 1, 6, 5, 7};

        System.out.println(reConstructBinaryTree(pre, in));
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) {
            return null;
        }
        List<Integer> preList = new ArrayList<>();
        for (int i : pre) {
            preList.add(i);
        }
        List<Integer> inList = new ArrayList<>();
        for (int i : in) {
            inList.add(i);
        }
        Map<Integer, TreeNode> treeNodeMap = new HashMap<>();
        inList.forEach(integer -> {
            treeNodeMap.put(integer, new TreeNode(integer));
        });
        for (int i = 0; i < pre.length; i++) {
            TreeNode treeNode = treeNodeMap.get(pre[i]);
            if (i != 0) {
                if (inList.indexOf(pre[i]) < inList.indexOf(pre[i - 1])) {
                    treeNodeMap.get(pre[i - 1]).left = treeNode;
                } else {
                    if (inList.indexOf(pre[i]) - inList.indexOf(pre[i - 1]) == 2) {
                        treeNodeMap.get(pre[i - 2]).right = treeNode;
                    } else {
                        int lastInIndex = inList.indexOf(pre[i]) - 1;
                        int lastInValue = inList.get(lastInIndex);
                        if (preList.indexOf(lastInValue) > i) {
                            treeNodeMap.get(lastInValue).left = treeNode;
                        } else {
                            treeNodeMap.get(lastInIndex).right = treeNode;
                        }
                    }
                }
            }
        }
        return treeNodeMap.get(pre[0]);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "\n" + left + " " + right;
        }
    }
}
