package leetCode;

import java.util.*;

/**
 * @author fanbaicheng
 * @since 2020/6/7 11:26
 */
public class No126 {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 穷举每个单词能被谁变过来
        // map  key为变之后的单词   value为变之前的单词

        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String to = wordList.get(i);

            for (int j = 0; j < wordList.size(); j++) {
                String from = wordList.get(j);
                if (isTransfer(from, to)) {

                    if (map.containsKey(to)) {
                        map.get(to).add(from);
                    } else {
                        List<String> fromList = new ArrayList<>();
                        fromList.add(from);
                        map.put(to, fromList);
                    }
                }
            }
        }

        // 至此，谁能到的map已经构建好了
        // 递归，看谁最终能到达endWord
        // 广度优先搜索
        List<List<String>> result = new ArrayList();
        // 压栈
        ArrayDeque<List<String>> queue = new ArrayDeque<>();

        List<String> keda = new ArrayList();
        keda.add(endWord);
        queue.add(keda);

        // 如果到当前节点可达
        while(!queue.isEmpty()) {

            List<String> currentWorldRoad = queue.pop();

            // 列表最后一个为当前的数字
            String currentWord = currentWorldRoad.get(currentWorldRoad.size() - 1);

            if (map.get(currentWord) != null && map.get(currentWord).size() > 0 ) {
                // 可达节点 fromList
                List<String> fromList = map.get(currentWord);

                // 将当前节点的可达路径压入队列
                for(String from : fromList) {

                    // 如果路径里已经包括他了，下一个
                    if (currentWorldRoad.contains(from)) {
                        continue;
                    }

                    List<String> innerKeda = new ArrayList(currentWorldRoad);
                    innerKeda.add(from);
                    // 如果from是开始节点，那直接放入返回list中
                    if (beginWord.equals(from)) {
                        result.add(innerKeda);
                    } else {
                        // 否则入队列继续搜索
                        queue.add(innerKeda);

                    }
                }
            }
        }

        int min = 999999;
        // 最短
        for (List<String> l : result) {
            min = l.size() < min ? l.size() : min;
        }

        List<List<String>> r = new ArrayList<>();
        for (List<String> l : result) {

            if (l.size() == min) {

                // 让数组倒序
                List<String> inn = new ArrayList<>();
                for (int k = l.size() - 1; k >= 0; k--) {
                    inn.add(l.get(k));
                }

                r.add(inn);
            }
        }

        return r;
    }

    public static boolean isTransfer(String from, String to) {

        int notEqual = 0;
        for (int i = 0; i < from.length(); i ++) {
            if (from.charAt(i) != to.charAt(i)) {
                notEqual ++;
            }

            if (notEqual > 1) {
                break;
            }
        }

        if (notEqual == 1) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        // ["hot","dot","dog","lot","log","cog"]
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");

        list.add("log");
        list.add("cog");

        List<List<String>> result = findLadders("hit", "cog", list);
        System.out.println(result);

    }
}
