package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fanbaicheng
 * @since 2020/6/6 19:22
 */
public class No128 {
    public int longestConsecutive(int[] nums) {

        // 最长序列长度。x的最长子序列长度 如果x-1存在 + 1 如果不存在就是1
        Set<Integer> set = new HashSet<>();
        for(int x : nums) {
            set.add(x);
        }

        // 遍历set
        int max = 0;
        for (int cur : set) {

            if (set.contains(cur - 1)) {
                continue;
            }

            // 不包含比自己小的，那就从自己开始数
            int current = 1;
            int tmp = cur;
            while(set.contains(tmp + 1)) {
                current++;
                tmp++;
            }

            max = max > current ? max : current;
        }

        return max;
    }
}
