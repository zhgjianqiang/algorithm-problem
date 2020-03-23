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

    /*
    因为是树的结构，一般都是用递归来实现。
    用数学归纳法的思想就是，假设最后一步，就是root的左右子树都已经重建好了，那么我只要考虑将root的左右子树安上去即可。
    根据前序遍历的性质，第一个元素必然就是root，那么下面的工作就是如何确定root的左右子树的范围。
    根据中序遍历的性质，root元素前面都是root的左子树，后面都是root的右子树。那么我们只要找到中序遍历中root的位置，就可以确定好左右子树的范围。
    正如上面所说，只需要将确定的左右子树安到root上即可。递归要注意出口，假设最后只有一个元素了，那么就要返回。
    */
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                //i为中序遍历下的root元素，故in这里是startIn到i-1；
                //同时该root元素下左子树的元素个数为i-startIn，pre第一个为root，故pre这里是startPre+1到startPre+(i-startIn)
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                //pre右子树起始比左子树最后一个元素的index结尾多1，结尾为最大值endPre；in这里简单的i+1即可
                root.right = reConstructBinaryTree(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);
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
