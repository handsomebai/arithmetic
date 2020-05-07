package search;

/**
 * @author fanbaicheng
 * @since 2020/5/7 19:11
 *
 * 二分查找
 */
public class BinarySearch {

    /**
     * 简单循环二分查找
     *
     * @param list
     * @param low
     * @param high
     * @param value
     */
    public static int simpleSearch(int[] list, int low, int high, int value) {

        while (low <= high ) {
            int middle = low + (high - low) / 2;

            if (list[middle] == value) {
                return middle;
            }

            if (list[middle] < value) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }

    /**
     * 递归二分查找
     *
     * @param list
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int recursionSearch(int[] list, int low, int high, int value) {

        if (low > high) {
            return -1;
        }

        int middle = low + (high - low) / 2;

        if (list[middle] == value) {
            return middle;
        }

        if (list[middle] < value) {
            return recursionSearch(list, middle + 1, high, value);
        } else {
            return recursionSearch(list, low , middle - 1, value);
        }
    }

    /**
     * 查询第一个相等的值
     *
     * @param list
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int firstSearch(int[] list, int low, int high, int value) {

        while (low <= high) {

            int middle = low + (high - low) / 2;

            if (list[middle] > value) {
                high = middle - 1;
            } else if (list[middle] < value) {
                low = middle + 1;
            } else {
                // 此时 当前节点 等于 value

                // 当前节点是第一个节点，那就它了
                if (middle == low) {
                    return middle;
                }

                // 如果当前节点的前一个节点不等，那就它了
                if (list[middle -1] != value) {
                    return middle;
                }

                // 不然，接着往左挪(因为选第一个相等的)
                high = middle - 1;
            }
        }

        return -1;
    }

    /**
     * 查询最后一个相等的值
     *
     * @param list
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int lastSearch(int[] list, int low, int high, int value) {

        while (low <= high) {

            int middle = low + (high - low) / 2;

            if (list[middle] > value) {
                high = middle - 1;
            } else if (list[middle]  < value) {
                low = middle + 1;
            } else {

                // 此时 当前节点 等于 value

                // 当前节点是最后一个节点，那就它了
                if (middle == high) {
                    return middle;
                }

                // 如果当前节点的后一个节点不等，那就它了
                if (list[middle + 1] != value) {
                    return middle;
                }

                // 不然，接着往右挪(因为选最后一个相等的)
                low = middle + 1;
            }
        }

        return -1;
    }

    /**
     * 查询第一个大于的值
     *
     * @param list
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int firstGreaterSearch(int[] list, int low, int high, int value) {

        while (low <= high) {

            int middle = low + (high - low) / 2;

            if (list[middle] >= value) {

                // 此时 当前节点 是大于等于 value

                // 如果当前节点大于 且 当前节点的前一个小于等于 那就它了
                if (list[middle] > value && list[middle - 1] <= value) {
                    return middle;
                } else {
                    // 不然， 接着往右挪(因为选的是大于，大于肯定在右边)
                    low = middle + 1;
                }

            } else {
                high = middle - 1;
            }
        }

        return -1;
    }

    /**
     * 查询最后一个小于
     *
     * @param list
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int lastLessSearch(int[] list, int low, int high, int value) {

        while (low <= high) {

            int middle = low + (high - low) / 2;

            if (list[middle] > value) {
                low = middle - 1;
            } else {
                // 现在是 小于等于

                // 如果当前小于 且 当前+1 大于等于 那么就是它了
                if (list[middle] < value && list[middle + 1] >= value) {
                    return middle;
                } else {
                    // 不然，接着往左挪(因为选的最后一个小于，因为小于肯定在左边)
                    high = middle - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println("=============归并排序=================");
        int[] list1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int index1 = simpleSearch(list1, 0, list1.length - 1, 6);
        System.out.println(index1);
        System.out.println("======================================");
        System.out.println();
        int index2 = recursionSearch(list1, 0, list1.length - 1, 5);
        System.out.println(index2);
        System.out.println("======================================");
        System.out.println();

        int[] list2 = new int[]{1, 2, 3, 3, 3, 3, 7, 8};
        int index3 = firstSearch(list2, 0, list1.length - 1, 3);
        System.out.println(index3);
        System.out.println("======================================");
        System.out.println();

        int index4 = lastSearch(list2, 0, list1.length - 1, 3);
        System.out.println(index4);
        System.out.println("======================================");
        System.out.println();

        int index5 = firstGreaterSearch(list2, 0, list1.length - 1, 3);
        System.out.println(index5);
        System.out.println("======================================");
        System.out.println();

        int index6 = lastLessSearch(list2, 0, list1.length - 1, 3);
        System.out.println(index6);
        System.out.println("======================================");
        System.out.println();
    }

}
