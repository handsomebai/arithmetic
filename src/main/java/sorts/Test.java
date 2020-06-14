package sorts;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fanbaicheng
 * @since 2020/5/24 17:01
 */
public class Test {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 先用最简单的方式。将两个数组合并成一个数组。

        // 两个有序数据的合并
        int[] merge = merge(nums1, nums2);

        // 判断是否为偶数  偶数取平均  奇数直接取中位数
        int middle = merge.length / 2;
        if (merge.length % 2 == 0) {

            return ( merge[middle] + merge[middle - 1] ) / 2.0;
        } else{
            return merge[middle];
        }
    }

    public static int[] merge(int[] nums1, int[] nums2) {

        int index1 = 0;
        int index2 = 0;
        int max1 = nums1.length;
        int max2 = nums2.length;
        int[] merge = new int[max1 + max2];
        int indexMerge = 0;

        // 如果一个数组遍历完就退出
        while(index1 < max1 && index2 < max2) {

            if (nums1[index1] < nums2[index2]) {
                merge[indexMerge++] = nums1[index1];
                index1++;
            } else {
                merge[indexMerge++] = nums2[index2];
                index2++;
            }
        }

        // max1 遍历完了。 将max2未遍历的数据，放入merge
        if (index1 >= max1) {
            while(index2 < max2) {
                merge[indexMerge++] = nums2[index2++];
            }
        } else {
            while(index1 < max1) {
                merge[indexMerge++] = nums1[index1++];
            }
        }

        return merge;
    }

    /*public static void main(String[] args) {

       int[] nums = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(nums);

    }*/

    public static void sortColors(int[] nums) {

        // 第一遍循环，计数
        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        int[] tmp = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i ++) {
            if (nums[i] == 0) {
                num0++;
            } else if(nums[i] == 1) {
                num1++;
            } else {
                num2++;
            }
            tmp[i] = nums[i];
        }

        int index0 = 0;
        int index1 = num0;
        int index2 = num0 + num1;

        for (int j = 0 ; j < tmp.length; j ++) {
            if (tmp[j] == 0) {
                nums[index0++] = tmp[j];
            } else if (tmp[j] == 1) {
                nums[index1++] = tmp[j];
            } else {
                nums[index2++] = tmp[j];
            }
        }
    }

    public static String longestPalindrome(String s) {

        int len = s.length();
        int start = 0;
        int end = 0;

        for (int i = 0; i < len; i++) {

            int len1 = getLen(s, i, i);
            int len2 = getLen(s, i , i + 1);
            int currentLen = len1 > len2 ? len1 : len2;
            // 判断该长度，是否是最大长度
            if (currentLen > end - start) {
                start = i - ((currentLen - 1) / 2);
                end = i + (currentLen / 2);
            }
        }

        // 出来的start和end就是最长的start和end
        return s.substring(start, end+1);
    }

    public static int getLen(String s, int left, int right) {
        int l = left;
        int r = right;

        while (l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return r - l - 1;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{3,3,4};
        int[] nums1 = new int[]{3,4,4};
        int i = canCompleteCircuit(nums, nums1);
        System.out.println(i);

    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        // double数据，手动成环
        int[] doubleGas = new int[gas.length * 2];
        int[] doubleCost = new int[cost.length * 2];
        for(int i = 0; i < doubleGas.length; i++) {
            int index = i % gas.length;
            doubleGas[i] = gas[index];
            doubleCost[i] = cost[index];
        }

        int len = gas.length;

        // 一个节点一个节点的遍历
        for (int i = 0 ; i < len; i++) {

            // 初始剩余油量为0
            int reduce = 0;
            int j = i;

            // 判断每个节点能不能跑一圈
            for (int num = 0; num < gas.length; num++) {

                // 判断当前节点，能不能到下一个节点
                reduce = isFull(reduce + doubleGas[j], doubleCost[j]);

                if(reduce == -1) {
                    break;
                }

                j++;
            }

            if (reduce >= 0) {
                return i;
            }
        }

        return -1;
    }

    public static int isFull(int currentGas, int cost) {

        // 判断当前油量，能不能到下一个加油站
        if (currentGas >= cost) {
            return currentGas - cost;
        }

        return -1;
    }


}
