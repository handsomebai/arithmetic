package sorts;

import java.util.Arrays;

/**
 * @auther fanbaicheng
 * @description 冒泡排序、插入排序、选择排序
 * @since 2020/4/2 20:01
 */
public class SimpleSorts {

    /**
     * 冒泡排序
     *
     * 思路：
     * 一次for循环排一个，每次遍历当前节点之后的元素
     */
    public static void maoPaoSort(int[] list) {

        for(int i = 0; i < list.length; i++) {

            // 排list[i]
            for (int j = i + 1; j < list.length; j++) {
                // 如果j比i小 换
                if (list[j] < list[i]) {
                    int tmp = list[i];
                    list[i] = list[j];
                    list[j] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * 思路：
     * 将数组分为 已排序 和 未排序两组
     * 每次在未排序的组里取一个，在一排序中插入，已排序的比当前值大的节点向后移动
     */
    public static void chaRuSort(int[] list) {

        for (int j = 1; j < list.length; j++) {

            int value = list[j];
            int i = j - 1;

            for (; i >= 0; i--) {
                if (value < list[i]) {
                    list[i+1] = list[i];
                } else {
                    break;
                }
            }
            list[i+1] = value;
        }
    }

    /**
     * 选择排序
     *
     * 思路：
     * 将数组分为 已排序 和 未排序两组
     * 每次在未排序组里取最小的 和 已排序组里最大的进行比较  后者小 交换 后者大 不变  下一次
     */
    public static void xuanZeSort(int[] list) {

        for (int i = 0 ; i < list.length; i++) {

            int minIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
            }

            int tmp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {

        System.out.println("=============冒泡排序=================");
        int[] list1 = new int[]{4,5,6,7,1,2,4,9};
        maoPaoSort(list1);
        System.out.println(Arrays.toString(list1));
        System.out.println("======================================");
        System.out.println();

        System.out.println("=============插入排序=================");
        int[] list2 = new int[]{4,5,6,7,1,2,4,9};
        chaRuSort(list2);
        System.out.println(Arrays.toString(list2));
        System.out.println("======================================");
        System.out.println();

        System.out.println("=============插入排序=================");
        int[] list3 = new int[]{4,5,6,7,1,2,4,9};
        xuanZeSort(list3);
        System.out.println(Arrays.toString(list3));
        System.out.println("======================================");
        System.out.println();
    }
}
