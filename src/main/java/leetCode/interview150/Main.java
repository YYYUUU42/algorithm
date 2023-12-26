package leetCode.interview150;

import java.util.*;

public class Main {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int l = 0, r = 0, index = 0;
        if (m == 0) {
            arr = nums2;
        } else if (n == 0) {
            arr = nums1;
        } else {
            while (l < m && r < n) {
                if (nums1[l] <= nums2[r]) {
                    arr[index] = nums1[l];
                    l++;
                } else {
                    arr[index] = nums2[r];
                    r++;
                }
                index++;
            }
        }

        while (l < m) {
            arr[index++] = nums1[l];
            l++;
        }

        while (r < n) {
            arr[index++] = nums2[r];
            r++;
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = arr[i];
        }
    }

    public int removeElement(int[] nums, int val) {
        int slow=0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast]!=val){
                nums[slow]=nums[fast];
                slow++;
            }
        }

        return slow;
    }

    public int removeDuplicates(int[] nums) {
        int slow=0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow]!=nums[fast]){
                nums[++slow]=nums[fast];
            }
        }

        return slow;
    }

    public int removeDuplicates2(int[] nums) {
        int slow=2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[slow]!=nums[fast]){
                nums[slow++]=nums[fast];
            }
        }
        return slow;
    }

    public int majorityElement(int[] nums) {
        int count=0;
        int res=Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (count==0){
                res=nums[i];
                count++;
            }else if (res!=nums[i]){
                count--;
            }else{
                count++;
            }
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
