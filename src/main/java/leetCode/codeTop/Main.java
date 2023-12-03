package leetCode.codeTop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int res = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }

            map.put(ch, end);
            res = Math.max(res, end - start + 1);
        }

        return res;
    }


    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode temp = null;
        ListNode res = null;

        while (cur != null) {
            temp = cur.next;
            cur.next = res;
            res = cur;
            cur = temp;
        }

        return res;
    }


    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            res = Math.max(count, res);
            if (count < 0) {
                count = 0;
            }
        }
        return res;
    }


    public int[] sortArray(int[] nums) {
        quickSort(nums,0, nums.length-1);
        return nums;
    }

    public void quickSort(int[] arr, int l, int r) {
        //边界条件
        if (l >= r) {
            return;
        }

        //得到随机数,将其与第一位交换
        int random = (int) (Math.random() * (r - l + 1) + l);
        swap(arr,l,random);

        //使用双指针，分别位于两边
        int i = l, j = r;

        //temp就是基准位
        int temp = arr[l];

        while (i < j) {
            //从右侧开始,查询小于基准位的值
            while (i < j && temp <= arr[j]) {
                j--;
            }
            // 5. 右侧查询到后，放置于l空位处，r处位置便空出
            arr[l] = arr[r];

            while (i < j && temp >= arr[i]) {
                i++;
            }
            arr[r] = arr[l];

        }

        arr[l] = temp;


        quickSort(arr, l, j - 1);
        quickSort(arr, j + 1, r);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
