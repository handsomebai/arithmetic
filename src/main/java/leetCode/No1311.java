package leetCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fanbaicheng
 * @since 2020/6/3 11:56
 */
public class No1311 {



    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        // 先找到到level级朋友的朋友id列表
        Set<Integer> find = new HashSet();
        find.add(id);
        for (int i = 0; i < level; i++) {
            find = getFriend(find, friends);
        }

        // 得把自己排除


        // 现在所有的朋友id找到了，找朋友id对应的视频list，并对视频进行排序
        List<String> videos = new ArrayList();
        for (Integer cid : find) {
            List<String> currentVideo = watchedVideos.get(cid);
            videos.addAll(currentVideo);
        }

        // 排序去重

        // 计数排序得需要一个map了
        final Map<String, Integer> map = new HashMap<>();
        for (String video: videos) {
            if (map.containsKey(video)) {
                int count = map.get(video);
                map.put(video, count + 1);
            } else {
                map.put(video, 1);
            }
        }

        // 按观看频率升序返回，如果存在频率相同的视频按名字字典序从小到大排列
        Collections.sort(videos, new Comparator<String>() {
            @Override
            public int compare(String v1, String v2) {
                if ( map.get(v1) - map.get(v2) == 0 ) {
                    return v1.compareTo(v2);
                }
                return map.get(v1) - map.get(v2);
            }
        });

        return videos;
    }

    // 查询给定集合的朋友列表
    public static Set<Integer> getFriend(Set<Integer> find, int[][] friends) {

        Set<Integer> allFriends = new HashSet();

        for (Integer current: find) {
            int[] currentFriends = friends[current];
            for (int k = 0; k < currentFriends.length; k++) {
                allFriends.add(currentFriends[k]);
            }
        }

        return allFriends;
    }

    public static void main(String[] args) {

        List<List<String>> watchedVideos = new ArrayList<>();
        List<String> k1 = new ArrayList<>();
        k1.add("A");
        k1.add("B");
        List<String> k2 = new ArrayList<>();
        k2.add("C");
        List<String> k3 = new ArrayList<>();
        k3.add("C");
        k3.add("B");
        List<String> k4 = new ArrayList<>();
        k4.add("D");
        watchedVideos.add(k1);
        watchedVideos.add(k2);
        watchedVideos.add(k3);
        watchedVideos.add(k4);

        int[][] friends = {{1,2}, {0,3}, {0,3}, {1,2}};



        List<String> result = watchedVideosByFriends(watchedVideos,friends,0, 2);
        System.out.println(result);


    }

}
