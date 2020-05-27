package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/5/27 10:56
 */
public class No221 {

    public static int maximalSquare(char[][] matrix) {

        // 动态规划
        int max = 0;
        for (int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int max1 = currentIndex(matrix, i, j);
                if (max1 > max) {
                    max = max1;
                }
            }
        }

        return max;
    }

    // 判断以当前点为起点，围成的最大正方形面积
    // 如果围不成，返回-1
    public static int currentIndex(char[][] matrix, int i, int j) {

        if (matrix[i][j] == '0') {
            return -1;
        }

        int indexI = i;
        int indexJ = j;

        // 先找对称的上和左两条边
        while (indexI < matrix.length && indexJ < matrix[i].length && matrix[i][indexJ] == '1' && matrix[indexI][j] == '1') {

            // 如果等。看右和下是否相等
            int currentI = i;
            int currentJ = j;

            // 看 右和下边 是否都是1
            while (currentJ <= indexJ && currentI <= indexI && matrix[currentI][indexJ] == '1' && matrix[indexI][currentJ] == '1') {
                currentI++;
                currentJ++;
            }

            // 如果围不上，说明不能构成圆
            if (currentI <= indexI && currentJ <= indexJ) {
                break;
            }

            indexJ++;
            indexI++;
        }

        // 计算面积
        int x = indexJ - j;
        return x * x;

    }

    public static void main(String[] args) {

        //[['0','0','1','0'],['1','1','1','1'],['1','1','1','1'],['1','1','1','0'],['1','1','0','0'],['1','1','1','1'],['1','1','1','0']]
        char[][] coins = new char[][] {{'0','0','1','0'},{'1','1','1','1'},{'1','1','1','1'},{'1','1','1','0'},{'1','1','0','0'},{'1','1','1','1'},{'1','1','1','0'}};
        int result = maximalSquare(coins);
        System.out.println(result);
    }
}
