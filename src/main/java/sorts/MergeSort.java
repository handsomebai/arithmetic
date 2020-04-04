package sorts;

import java.util.Arrays;

/**
 * @auther fanbaicheng
 * @description 归并排序
 * @since 2020/4/3 10:58
 */
public class MergeSort {

    public static void mergeSort(int[] a, int startIndex, int endIndex) {

        /**
         * 思路：
         * 1、从终点分为左右两部分，对这两部分进行排序
         * 2、递归，一直递归到左右都各有一个元素
         * 3、从最后一个元素开始排序，两个元素两个元素排，然后再两个有序数组两个有序数组排
         */

        /**
         * 递归的结束条件 左index >= 右index
         * 走到这一步说明，左index与右index相邻，左右两部分都只有一个元素
         */
        if(startIndex >= endIndex) {
            return;
        }

        /**
         * 将start-end  拆成 左右两个start-end
         */
        int middle = startIndex + (endIndex - startIndex) / 2;
        int leftStartIndex = startIndex;
        int leftEndIndex = middle;
        int rightStartIndex = middle + 1;
        int rightEndIndex = endIndex;

        /**
         * 将左右两个数组 接着递归  直到触发递归退出条件
         */
        mergeSort(a, leftStartIndex, leftEndIndex);
        mergeSort(a, rightStartIndex, rightEndIndex);

        /**
         * 递归结束，合并左右两部分
         *
         * 递归第一步：两个元素排序
         * 递归最后一步：两个有序数组排序
         */
        merge(a, leftStartIndex, leftEndIndex, rightStartIndex, rightEndIndex);

    }

    public static void merge(int[] a, int leftStartIndex, int leftEndIndex, int rightStartIndex, int rightEndIndex) {

        /**
         * 思路：
         * 1、开一个临时数组，在这个临时数组里做 两个顺序列表的排序
         * 2、排好序，拿这个临时数组，覆盖原数组，这样，原数组就是排好序的了
         *
         * 因为开启了临时数组，所以归并排序并不是原地排序
         */
        int[] temp = new int[rightEndIndex - leftStartIndex + 1];
        int tmpIndex = 0;
        int leftCurrentIndex = leftStartIndex;
        int rightCurrentIndex = rightStartIndex;
        while (leftCurrentIndex <= leftEndIndex && rightCurrentIndex <= rightEndIndex) {

            if (a[leftCurrentIndex] <= a[rightCurrentIndex]) {
                temp[tmpIndex++] = a[leftCurrentIndex++];
            } else {
                temp[tmpIndex++] = a[rightCurrentIndex++];
            }
        }

        /**
         * 能跳出一定是左右某一边排完了
         * 判断是哪边排完了 直接将另一边顺序放入temp就好
         *
         * 当左边不满足条件出来的时候，leftCurrentIndex已经++过了，所以得和+1判等
         */
        if (leftCurrentIndex == leftEndIndex + 1) {
            while (rightCurrentIndex <= rightEndIndex) {
                temp[tmpIndex++] = a[rightCurrentIndex++];
            }
        } else {
            while (leftCurrentIndex <= leftEndIndex) {
                temp[tmpIndex++] = a[leftCurrentIndex++];
            }
        }

        /**
         * 现在temp已经是排好序的了
         * 接下来就是用temp覆盖原列表
         */
        for (int i = 0; i < rightEndIndex - leftStartIndex; i++) {
            a[leftStartIndex + i] = temp[i];
        }
    }

    public static void main(String[] args) {

        System.out.println("=============归并排序=================");
        int[] list1 = new int[]{4, 5, 6, 7, 1, 2, 4, 9};
        mergeSort(list1, 0, list1.length - 1);
        System.out.println(Arrays.toString(list1));
        System.out.println("======================================");
        System.out.println();
    }
}
