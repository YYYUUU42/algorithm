package leetCode.LCRating;

import org.junit.Test;

import java.util.*;

public class Main {

    /**
     * 1402
     *
     * @param nums
     * @param n
     * @param left
     * @param right
     * @return
     */
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long sum = nums[i];
            list.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                list.add(sum);
            }
        }

        Long[] arr = list.toArray(new Long[list.size()]);
        Arrays.sort(arr);
        int res = 0;
        int mod = 1000000000 + 7;

        for (int i = left; i <= right; i++) {
            res += arr[i];
            res = res % mod;
        }

        return res;
    }

    /**
     * 1404
     *
     * @param students
     * @param sandwiches
     * @return
     */
    public int countStudents(int[] students, int[] sandwiches) {
        int[] arr = new int[2];
        for (int i = 0; i < students.length; i++) {
            arr[students[i]]++;
        }

        for (int i = 0; i < sandwiches.length; i++) {
            if (--arr[sandwiches[i]] == -1) {
                return sandwiches.length - i;
            }
        }
        return 0;
    }

    public String smallestString(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 'a') {
                for (; i < arr.length && arr[i] != 'a'; i++) {
                    arr[i]--;
                }
                return new String(arr);
            }
        }
        arr[arr.length - 1] = 'z';

        return new String(arr);
    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int l = 0, r = piles.length - 1, res = 0;
        while (l < r) {
            res += piles[r - 1];
            r -= 2;
            l++;
        }

        return res;
    }

    public int maximumNumberOfStringPairs(String[] words) {
        int count = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String s = new StringBuilder(words[i]).reverse().toString();
                if (s.equals(words[i])) {
                    count++;
                }
            }
        }

        return count;
    }

    public String oddString(String[] words) {
        int[] diff0 = getDiffArr(words[0]);
        int[] diff1 = getDiffArr(words[1]);

        if (Arrays.equals(diff1, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (Arrays.equals(diff0, getDiffArr(words[i]))) {
                    return words[i];
                }
            }
        }

        return Arrays.equals(diff0, getDiffArr(words[2])) ? words[1] : words[0];
    }

    public int[] getDiffArr(String s) {
        int[] arr = new int[s.length() - 1];
        for (int i = 1; i < s.length(); i++) {
            arr[i - 1] = s.charAt(i) - s.charAt(i - 1);
        }

        return arr;
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return root;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (String s : words1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }

        for (String s : words2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }

        int count = 0;

        for (String s : words1) {
            if (map1.get(s) == 1 && map2.getOrDefault(s,0) == 1) {
                count++;
            }
        }

        return count;
    }


    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4};
        rangeSum(arr, 4, 1, 5);
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