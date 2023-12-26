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
            if (--arr[sandwiches[i]]==-1){
                return sandwiches.length-i;
            }
        }
        return 0;
    }

    @Test
    public void test(){
        int[] arr={1,2,3,4};
        rangeSum(arr,4,1,5);
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