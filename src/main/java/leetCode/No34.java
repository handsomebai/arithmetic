package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/15 21:09
 */
public class No34 {

    public static int[] searchRange(int[] nums, int target) {

        // 二分查找
        int index = search(nums, target, 0, nums.length -1);

        if (index == -1) {
            return new int[]{-1, -1};
        }

        int left = index - 1;
        // 向左
        while(left >= 0) {
            if (nums[left] != target) {
                break;
            }

            left--;
        }

        int right = index + 1;
        // 向左
        while(right < nums.length) {
            if (nums[right] != target) {
                break;
            }

            right++;
        }

        return new int[]{left+1, right-1};
    }

    public static int search(int[] nums, int target, int begin, int end) {

        if (begin > end) {
            return -1;
        }

        int middleIndex = begin + (end - begin) / 2;
        if (nums[middleIndex] == target) {
            return middleIndex;
        }

        if (nums[middleIndex] > target) {
            return search(nums, target, begin, middleIndex);
        } else {
            return search(nums, target, middleIndex + 1, end);
        }
    }

    public static void main(String[] args) {


        int[] result = searchRange(new int[]{1,1,2}, 1);
        System.out.println(result);
    }
}
