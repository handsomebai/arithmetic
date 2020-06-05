package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/5 10:07
 */
public class No29 {
    public static int[] spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        // 肯定是i,j两个指针定位
        // 先右下左上，转一圈
        // 右：j不变，i++，i到达右边界的时候停止
        // 下：i变，j--，j到达下边界时停止

        int lastLeft = -1;
        int lastRight = matrix[0].length;
        int lastDown = matrix.length;
        int lastUp = -1;

        int[] resule = new int[lastRight * lastDown];
        int index = 0;

        int currentI = 0;
        int currentJ = 0;
        while(currentI != lastDown && currentJ != lastRight) {

            //遍历输出圆

            // 右
            int count = 0;
            while(currentJ < lastRight) {
                resule[index++] = matrix[currentI][currentJ];
                currentJ++;
                count++;
            }

            // 没走，跳出
            if (count == 0) {
                break;
            }

            currentJ--;


            // 交叉点跳过
            currentI++;

            // 下
            count = 0;
            while(currentI < lastDown) {
                resule[index++] = matrix[currentI][currentJ];
                currentI++;
                count++;
            }

            // 没走，跳出
            if (count == 0) {
                break;
            }

            currentI--;

            // 交叉点掉过
            currentJ--;

            // 左
            count = 0;
            while(currentJ > lastLeft) {
                resule[index++] = matrix[currentI][currentJ];
                currentJ--;
                count++;
            }
            // 没走，跳出
            if (count == 0) {
                break;
            }

            currentJ++;

            // 交叉点掉过
            currentI--;

            // 上 原起点不输出
            while(currentI > lastUp + 1) {
                resule[index++] = matrix[currentI][currentJ];
                currentI--;
            }

            // 将last赋新值
            lastLeft++;
            lastRight--;
            lastDown--;
            lastUp++;

            // current向右下移动，进入下一圈
            currentI = lastUp + 1;
            currentJ = lastLeft + 1;

            if (currentI == lastDown || currentJ == lastRight) {
                break;
            }

        }

        return resule;
    }

    public static void main(String[] args) {

        //int[][] matrix = {{1,2,3,5}, {5,6,7,8}, {9,10,11,12}};
        int[][] matrix = {{7}, {9}, {6}};
        int[] result = spiralOrder(matrix);
        System.out.println(result);
    }
}
