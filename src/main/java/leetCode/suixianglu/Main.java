package leetCode.suixianglu;

import org.junit.Test;

import java.util.*;

public class Main {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
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

    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];

        int l = 0, r = nums.length - 1, index = nums.length;
        while (l <= r) {
            if (Math.abs(nums[l]) >= Math.abs(nums[r])) {
                arr[index--] = (int) Math.pow(nums[l], 2);
                l++;
            } else {
                arr[index--] = (int) Math.pow(nums[r], 2);
                r--;
            }
        }

        return arr;
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int sum = 0;

        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }

        if (res == Integer.MAX_VALUE) {
            return 0;
        }

        return res;
    }


    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int l = 0, r = n - 1;
        int top = 0, bottom = n - 1;
        int num = 1;

        while (num <= n * n) {
            //上面，从左往右
            for (int i = l; i < r; i++) {
                arr[top][i] = num++;
            }

            if (num >= n * n) {
                break;
            }

            //右边，从上到下
            for (int i = top; i < bottom; i++) {
                arr[i][r] = num++;
            }

            if (num >= n * n) {
                break;
            }

            //下面，从右往左
            for (int i = r; i >= l; i--) {
                arr[bottom][i] = num++;
            }

            if (num >= n * n) {
                break;
            }

            //左边，从下到上
            for (int i = bottom; i >= top; i--) {
                arr[i][l] = num++;
            }
        }

        return arr;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode listNode = new ListNode(-1);
        ListNode pre = listNode;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return listNode.next;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            arr[ch - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            arr[ch - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (res.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }

        int[] arr = new int[res.size()];
        int index = 0;
        for (Integer re : res) {
            arr[index++] = re;
        }

        return arr;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            n = getHappy(n);
        }
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getHappy(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                arr[0] = map.get(target - nums[i]);
                arr[1] = i;
                return arr;

            }
            map.put(nums[i], i);
        }

        return arr;
    }


    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = -(nums3[i] = nums4[j]);
                res += map.getOrDefault(tmp, 0);
            }
        }

        return res;
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (--arr[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                }
                l++;
                r--;
            }
        }

        return res;
    }

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;

        while (l < r) {
            char ch = s[l];
            s[l] = s[r];
            s[r] = ch;
            l++;
            r--;
        }
    }


    @Test
    public void test() {
        int[] arr = {2, 3, 1, 2, 4, 3};
        minSubArrayLen(7, arr);
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
