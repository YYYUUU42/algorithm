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
            arr1[ch - 'a']++;
        }

        for (int i = 0; i < word2.length(); i++) {
            char ch = word2.charAt(i);
            arr2[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((arr1[i] == 0 && arr2[i] > 0) || (arr1[i] > 0 && arr2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }


    /**
     * 2661 找出叠涂元素
     *
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

    /**
     * 1094
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[1100];
        for (int[] trip : trips) {
            int num = trip[0];
            int form = trip[1];
            int to = trip[2];

            arr[form + 1] += num;
            arr[to + 1] -= num;
        }

        for (int i = 1; i <= 1000; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * 1423 可获取的最大点数
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        //窗口当前的值
        int cur = 0;
        //全部的总和
        int sum = 0;
        //滑动窗口的长度
        int len = cardPoints.length - k;

        for (int i = 0; i < cardPoints.length; i++) {
            sum += cardPoints[i];
        }

        for (int i = 0; i < len; i++) {
            cur += cardPoints[i];
        }

        //窗口之间的和最小
        int max = cur;

        for (int i = len; i < cardPoints.length; i++) {
            cur = cur + cardPoints[i] - cardPoints[i - len];
            max = Math.min(cur, max);
        }

        return sum - max;
    }

    /**
     * 1038
     *
     * @param rootx
     * @return
     */
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        bst1(root);
        bst2(root);
        return root;
    }

    public void bst1(TreeNode node) {
        if (node == null) {
            return;
        }

        bst1(node.left);
        sum += node.val;
        bst1(node.right);
    }

    public void bst2(TreeNode node) {
        if (node == null) {
            return;
        }

        bst2(node.left);
        int temp = node.val;
        node.val = sum;
        sum -= temp;
        bst2(node.right);
    }

    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; i <= 1224444; ++i) {
            if (isBalance(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBalance(int x) {
        int[] count = new int[10];
        while (x > 0) {
            count[x % 10]++;
            x /= 10;
        }
        for (int d = 0; d < 10; ++d) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;
    }


    public String makeSmallestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                arr[left] = arr[right] = (char) Math.min(arr[left], arr[right]);
            }
            ++left;
            --right;
        }
        return new String(arr);
    }

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid - 1;
            }
        }

        return r;
    }



    public boolean isAcronym(List<String> words, String s) {
        String str = "";
        for (String word : words) {
            String substring = word.substring(0, 1);
            str += substring;
        }

        return str.equals(s);
    }

    public int minStoneSum(int[] piles, int k) {
        Arrays.sort(piles);
        int index = piles.length - 1;
        for (int i = 0; i < k; i++) {
            int num = piles[index] / 2;
            piles[index] = piles[index] - num;
            if (piles[index] < piles[index - 1]) {
                index--;
            }

            if (index == -1) {
                Arrays.sort(piles);
                index = piles.length - 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
        }

        return sum;
    }

    @Test
    public void test() {
        closeStrings("abc", "bca");
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
