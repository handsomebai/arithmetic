package leetCode;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * @author fanbaicheng
 * @since 2020/6/15 17:49
 */
public class No15 {

    public static List<List<Integer>> threeSum(int[] nums) {

        // 定义一个返回数组
        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, List<List<Integer>>> backup = new HashMap<>();

        // 每个人开始问 两个人
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1; j < nums.length - 1; j++)  {

                int sum = 0 - nums[i] - nums[j];

                if (backup.containsKey(sum)) {
                    List<Integer> inn = new ArrayList();
                    inn.add(nums[i]);
                    inn.add(nums[j]);
                    backup.get(sum).add(inn);
                } else {

                    // 不在backup中
                    List<List<Integer>> in = new ArrayList();
                    List<Integer> inn = new ArrayList();
                    inn.add(nums[i]);
                    inn.add(nums[j]);
                    in.add(inn);
                    backup.put(sum, in);
                }
            }
        }

        // 两两已经加完了，开始问第三个人
        for (int i = 0; i < nums.length - 1; i ++) {

            if (backup.containsKey(nums[i])) {

                List<List<Integer>> containsIndex = backup.get(nums[i]);

                for (List<Integer> list : containsIndex) {

                    if (!list.contains(nums[i])) {

                        List<Integer> re = new ArrayList(list);
                        re.add(nums[i]);
                        result.add(re);
                        backup.remove(nums[i]);
                        continue;
                    }
                }
            }
        }

        Set<String> sort = new HashSet<>();

        for (List<Integer> list : result) {
            Collections.sort(list);
            String tmp = "";
            for (Integer inte : list) {
                tmp = tmp + inte + ",";
            }

            sort.add(tmp);
        }

        List<List<Integer>> r = new ArrayList<>();

        for (String str : sort) {
            String[] strList = StringUtils.splitString(str, ",");
            List<Integer> innn = new ArrayList<>();
            for (int i = 0; i < strList.length; i++) {
                innn.add(Integer.valueOf(strList[i]));
            }
            r.add(innn);
        }

        return r;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }
}
