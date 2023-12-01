package leetCode.questionOfDay;

import org.junit.Test;

import java.util.*;

public class Main {

    public int countPairs1(List<Integer> nums, int target) {
        int count = 0;

        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    count++;
                }
            }
        }

        return count;
    }

    public int countPairs(List<Integer> nums, int target) {
        int count = 0;
        Collections.sort(nums);
        int l = 0, r = nums.size() - 1;

        while (l < r) {
            int num = nums.get(l) + nums.get(r);
            if (num < target) {
                //如果最大那个数相加都小于，那在r和l之间的数字加上l肯定都小于
                count += r - l;
                l++;
            } else {
                r--;
            }
        }

        return count;
    }

    /**
     * 1657 确定两个字符串是否接近
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            char ch = word1.charAt(i);
            arr1[ch-'a']++;
        }

        for (int i = 0; i < word2.length(); i++) {
            char ch = word2.charAt(i);
            arr2[ch-'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((arr1[i] == 0 && arr2[i] > 0) || (arr1[i] > 0 && arr2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1,arr2);
    }


    /**
     *2661 找出叠涂元素
     * @param arr
     * @param mat
     * @return
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }
        int[] rowCnt = new int[n];
        int[] colCnt = new int[m];
        for (int i = 0; i < arr.length; ++i) {
            int[] v = map.get(arr[i]);
            ++rowCnt[v[0]];
            if (rowCnt[v[0]] == m) {
                return i;
            }
            ++colCnt[v[1]];
            if (colCnt[v[1]] == n) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        closeStrings("abc", "bca");
    }
}
