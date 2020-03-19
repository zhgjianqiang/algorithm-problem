package zhgjianqiang.newcoder.offer;

public class Test1 {

    public static void main(String[] args) {
        int[][] array = new int[][]
            {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
            };
            System.out.println(find(1, array));
    }

    //goodAnswer
    public static boolean find(int[][] array,int target) {
        int rowCount = array.length;
        int colCount = array[0].length;
        int i,j;
        for(i=rowCount-1,j=0;i>=0&&j<colCount;)
        {
            if(target == array[i][j])
                return true;
            if(target < array[i][j])
            {
                i--;
                continue;
            }
            if(target > array[i][j])
            {
                j++;
                continue;
            }
        }
        return false;
    }

    //思路有问题
    public static boolean find(int target, int[][] array) {
        int midHighSize = array.length / 2 + array.length % 2;
        int wideSize = array[0].length;
        int startIndex = wideSize - 1;
        int endIndex = 0;
        for (int i = 0; i < midHighSize; i++) {
            for (int e = startIndex; e >= 0; e--) {
                if (array[i][e] < target) {
                    startIndex = e < wideSize - 1 ? e + 1 : wideSize - 1;
                    break;
                } else if (array[i][e] == target) {
                    return true;
                }
            }
            for (int s = endIndex; s < wideSize; s++) {
                if (array[array.length - 1 - i][s] > target) {
                    endIndex = s > 0 ? s - 1 : 0;
                    break;
                } else if (array[array.length - 1 - i][s] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
