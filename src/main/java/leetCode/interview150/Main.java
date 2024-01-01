package leetCode.interview150;

import java.util.*;

public class Main {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int l = 0, r = 0, index = 0;

        while (l < m && r < n) {
            if (nums1[l] <= nums2[r]) {
                arr[index++] = nums1[l++];
            } else {
                arr[index++] = nums2[r++];
            }
        }

        while (l < m) {
            arr[index++] = nums1[l++];
        }

        while (r < n) {
            arr[index++] = nums2[r++];
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = arr[i];
        }
    }

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }

    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public int removeDuplicates1(int[] nums) {
        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
                count++;
            } else if (nums[i] != res) {
                count--;
            } else {
                count++;
            }
        }

        return res;
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[(i + k) % len] = nums[i];
        }

        System.arraycopy(arr, 0, nums, 0, len);
    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (max < prices[i] - min) {
                max = prices[i] - min;
            }
        }

        return max;
    }

    public int maxProfit1(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
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
