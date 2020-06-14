package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author fanbaicheng
 * @since 2020/6/12 19:18
 */
public class No46_1 {

    public static List<List<Integer>> permute(int[] nums) {
        // 回溯算法
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList();
        dfs(tmp, 0, result, nums);

        return result;
    }


    //tmp为当前选择过的tmp
    //index为当前需要填充的下标
    //result为最终的返回体
    public static void dfs(List<Integer> tmp, int index, List<List<Integer>> result, int[] nums) {

        if (index == nums.length) {
            result.add(tmp);
            return;
        }

        // 开始选择
        for (int i = 0; i < nums.length; i++) {

            // 如果i值已经存在在tmp了，跳过
            if (tmp.contains(nums[i])) {
                continue;
            }

            // 做选择
            tmp.add(nums[i]);
            List<Integer> t = new ArrayList(tmp);

            int k = index + 1;
            // 选择完，用选择完的结果，继续递归
            dfs(t, k, result, nums);

            // 撤销这次的选择，由下一个for循环选择下一个
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }
}
