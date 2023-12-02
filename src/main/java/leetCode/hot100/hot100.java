package leetCode.hot100;

import java.sql.Array;
import java.util.*;

import org.junit.Test;

public class hot100 {
    /**
     * 1 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if (map.containsKey(t)) {
                res[0] = map.get(t);
                res[1] = i;
                return res;
            }

            map.put(nums[i], i);
        }

        return res;
    }

    /**
     * 49 字母异位分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String s = getSortString(strs[i]);
            boolean b = map.containsKey(s);
            if (b) {
                List<String> list = map.get(s);
                list.add(strs[i]);
                map.put(s, list);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(s, list);
            }
        }

        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            res.add(list);
        }

        return res;
    }


    public String getSortString(String s) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        Arrays.sort(chars);
        String res = new String(chars);

        return res;
    }

    /**
     * 283 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                r++;
            } else {
                int t = nums[r];
                nums[r] = nums[l];
                nums[l] = t;
                l++;
                r++;
            }
        }

    }

    /**
     * 128 最长连续序列
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (Integer num : set) {
            int cur = num;

            //这一条是O(n)的关键，set集合中每个数只被查询过一次，不会重复遍历
            if (!set.contains(cur - 1)) {
                while (set.contains(cur + 1)) {
                    cur++;
                }
            }

            res = Math.max(res, cur - num + 1);

        }
        return res;
    }

    /**
     * 11 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = -1;
        int l = 0, r = height.length - 1;

        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    /**
     * 15 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
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
                    while (r > l && nums[r] == nums[r - 1]) {
                        r--;
                    }

                    while (r > l && nums[l] == nums[l + 1]) {
                        l++;
                    }

                    r--;
                    l++;
                }
            }

        }

        return res;
    }

    /**
     * 3 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

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

    /**
     * 438 找到字符串中所有字母异位符
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> list = new ArrayList<>();
        int len = p.length();
        for (int i = 0; i <= s.length() - len; i++) {
            String s1 = s.substring(i, i + len);
            if (s1.equals(p)) {
                list.add(i);
            } else if (isAnagrams(s1, p)) {
                list.add(i);
            }
        }
        return list;
    }

    public boolean isAnagrams(String a, String b) {
        int[] arr = new int[26];

        for (int i = 0; i < a.length(); i++) {
            arr[a.charAt(i) - 'a']++;
        }

        for (int i = 0; i < b.length(); i++) {
            if (--arr[b.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 560 和为K的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] arr = new int[nums.length];

        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }

        int l = 0, r = nums.length;

        for (int i = 0; i < nums.length; i++) {
            if (arr[i] == k) {
                count++;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (arr[j] - arr[i] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 53 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }

        return max;
    }

    /**
     * 56 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        //先对各数组的第一位进行排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        int[][] res = new int[intervals.length][2];
        int index = -1;

        for (int[] interval : intervals) {
            //如果是第一位就直接放入到res中
            //如果是res[index][1]大于interval[0],也直接放进去
            //例如，res=[[1,2]],interval=[3,4],不在res[index]区间范围内,=> res=[[1,2],[3,4]]
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                //分两种情况，一种是包含，一种是有相交区间,取两个数组的第二位最大值
                //包含:res=[[1,10]],interval=[2,3],=>res=[[1,10]]
                //另一种是相交res=[[1,3]],interval=[2,5],=>res=[1,5]
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }

        return Arrays.copyOf(res, index + 1);
    }

    /**
     * 189 轮转数组
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[i] = nums[(i + k) % n];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }


    /**
     * 238 除以自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        int[] res = new int[nums.length];

        //左累乘，左边没有元素，故为1
        L[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }

        R[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = L[i] * R[i];
        }

        return res;
    }


    /**
     * 73 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        //行
        int[] row = new int[matrix.length];
        //列
        int[] col = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }

    }


    /**
     * 160 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }

        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        int len = Math.abs(lenA - lenB);

        //curA是最长那个
        curA = lenA - lenB >= 0 ? headA : headB;
        curB = lenA - lenB < 0 ? headA : headB;

        while (len > 0) {
            curA = curA.next;
            len--;
        }

        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }


    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode temp = null;
        ListNode cur = head;
        ListNode res = null;

        while (cur != null) {
            temp = cur.next;
            cur.next = res;
            res = cur;
            cur = temp;
        }

        return res;
    }


    /**
     * 234 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        ListNode temp = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            //进行反转
            temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }


    private List<List<Integer>> resList = new ArrayList<List<Integer>>();

    /**
     * 102 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        checkFun(root, 0);
        return resList;
    }

    public void checkFun(TreeNode node, int deep) {
        if (node == null) {
            return;
        }

        deep++;
        if (resList.size() < deep) {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            resList.add(list);
        } else {
            resList.get(deep).add(node.val);
        }

        checkFun(node.left, deep);
        checkFun(node.right, deep);
    }


    /**
     * 108 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToTree(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToTree(nums, left, mid - 1);
        node.right = sortedArrayToTree(nums, mid + 1, right);

        return node;
    }


    /**
     * 98 验证二叉树
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, Long min, Long max) {
        if (node == null) {
            return true;
        } else if (node.val <= min || node.val >= max) {
            return false;
        } else {
            return isValidBST(node.left, min, (long) node.val) && isValidBST(node.right, (long) node.val, max);
        }
    }

    /**
     * 437 路径总和3
     * @param root
     * @param targetSum
     * @return
     */
    int count=0;
    int target=0;

    public int pathSum(TreeNode root, int targetSum) {
        target=targetSum;
        dfs1(root);
        return count;
    }

    public void dfs1(TreeNode node){
        if (node==null){
            return;
        }

        dfs2(node,node.val);
        dfs1(node.left);
        dfs1(node.right);
    }

    public void dfs2(TreeNode node,long val){
        if (val==target){
            count++;
        }

        if (node.left!=null){
            dfs2(node.left,node.left.val+val);
        }
        if (node.right!=null){
            dfs2(node.right,node.right.val+val);
        }
    }


    @Test
    public void test() {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strings);
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
