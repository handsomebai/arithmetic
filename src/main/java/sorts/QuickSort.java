package sorts;

import java.util.Arrays;

/**
 * @auther fanbaicheng
 * @description 快速排序
 * @since 2020/4/4 14:29
 */
public class QuickSort {

    public static void quickSort(int[] list , int startIndex, int endIndex) {

        /**
         * 思路：
         * 每次选最右边的数作为中间数， 将数组排位 比中间数小的数组  中间数 比中间数大的数组
         * 再对比中间数小的数组 进行快排 再变成 比中间数小的数组  中间数 比中间数大的数组
         * 直到分为小大数组中只有一位 退出
         */

        /**
         * 递归的结束条件 左index >= 右index
         * 走到这一步说明，左右都只有一个元素 且是排好序的了
         */
        if (startIndex >= endIndex) {
            return;
        }

        /**
         * 快排，就是先分组（分组就是小规模排序了）
         * 返回分区后的分区点
         */
        int middleIndex = partition(list, startIndex, endIndex);

        /**
         * 根据分区点 递归进行 快排
         */
        quickSort(list, startIndex, middleIndex - 1);
        quickSort(list, middleIndex + 1, endIndex);

    }

    public static int partition(int[] list, int startIndex, int endIndex) {

        /**
         * 思路：
         * 1、中间节点默认定位最后一个节点
         * 2、快慢指针
         * 3、快慢指针同时向前走，慢指针遇到比中间节点大的元素停下，标记住
         * 4、快指针继续向前，遇到比中间节点小的元素，停下
         * 5、此时，快指针慢指针的元素交换，快慢指针继续向前，找下一个交换的位置
         * 6、当快指针指向最后一个节点的时候，慢指针之前的数据一定是小于最后一个节点的，此时再将快慢指针交换
         * 7、此时，比中间数小的数组  中间数 比中间数大的数组 就排好了
         */
        int pivot = list[endIndex];
        int slowIndex = startIndex;

        for (int fastIndex = startIndex; fastIndex < endIndex; fastIndex++) {

            /**
             * 如果 快指针元素 小于 中间节点
             */
            if (list[fastIndex] < pivot) {

                // 当快慢指针相同的时候，不处理，因为此节点本来就比中间节点要小了，前后指针同时向前移
                if (slowIndex == fastIndex) {
                    slowIndex++;
                    continue;
                }

                /**
                 * 走到这 快指针一定在慢指针前面  且  快指针小于中间节点
                 *
                 * 此时 交换快慢指针中的元素
                 */
                int tmp = list[slowIndex];
                list[slowIndex] = list[fastIndex];
                list[fastIndex] = tmp;

                /**
                 * 这个时候，慢指针指向的元素，一定是小于中间节点的
                 *
                 * 此时让慢指针前移
                 */
                slowIndex++;
            }
        }

        /**
         * 能跳出循环，说明快指针已经走到最后一个节点了
         * 换句话说，所有比最后一个节点小的元素，都已经放进慢指针中了，且放完慢指针就++
         * 所以慢指针之前的所有元素都是比最后一个节点小的
         * 此时让最后一个节点的元素和慢指针中的元素互换就好了
         * 互换完，慢指针就是中间节点的位置了，将该位置返回
         */
        int tmp = list[slowIndex];
        list[slowIndex] = list[endIndex];
        list[endIndex] = tmp;

        return slowIndex;
    }

    public static void main(String[] args) {

        System.out.println("=============快速排序=================");
        int[] list1 = new int[]{4, 5, 6, 7, 1, 2, 4, 9};
        quickSort(list1, 0, list1.length - 1);
        System.out.println(Arrays.toString(list1));
        System.out.println("======================================");
        System.out.println();
    }
}
