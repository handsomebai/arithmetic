package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/15 21:29
 */
public class No33 {

    public static int search(int[] nums, int target) {
        // 二分查找
        return innerSearch(nums, target, 0, nums.length);
    }

    public static int innerSearch(int[] nums, int target, int left, int right) {

        // 没找到
        if (left > right) {
            return -1;
        }

        int middleIndex = left + (right - left) / 2;

        if (nums[middleIndex] == target) {
            return middleIndex;
        }

        if (nums[middleIndex] > target) {
            return innerSearch(nums, target, left, middleIndex - 1);
        } else{
            return innerSearch(nums, target, middleIndex + 1, right);
        }
    }

    public static void main(String[] args) {

        int result = search(new int[]{4,5,6,7,0,1,2}, 0);
        System.out.println(result);
    }
}
