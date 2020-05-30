package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/5/29 15:03
 */
public class No300 {

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        //int[] tmp = new int[nums.length];
        //tmp[0] = nums[0];

        int max = 0;
        for (int i = 0; i< nums.length; i++) {
            int current = getMax(nums, i);
            if (current > max) {
                max = current;
            }
        }

        return max;
    }

    // 针对每个节点找升序子序列的长度吧
    public static int getMax(int[] nums, int i) {
        int count = 1;
        int min = nums[i];
        for(int j = i+1; j < nums.length; j++) {
            if (nums[j] > min) {
                min = nums[j];
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = new int[] {10,9,2,5,3,4};
        int result = lengthOfLIS(nums);
        System.out.println(result);
    }
}
