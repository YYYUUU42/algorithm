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
                start = Math.max(start, map.get(ch)+1);
            }
            map.put(ch, end);
            res = Math.max(res, end - start + 1);
        }

        return res;
    }

    /**
     * 438 找到字符串中所有字母异位符
     * @param s
     * @param p
     * @return
     */
    public  List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> list = new ArrayList<>();
        int len=p.length();
        for (int i = 0; i <= s.length()-len; i++) {
            String s1=s.substring(i,i+len);
            if(s1.equals(p)){
                list.add(i);
            }
            else if (isAnagrams(s1,p)){
                list.add(i);
            }
        }
        return list;
    }

    public boolean isAnagrams(String a,String b){
        int[] arr = new int[26];

        for (int i = 0; i < a.length(); i++) {
            arr[a.charAt(i)-'a']++;
        }

        for (int i = 0; i < b.length(); i++) {
            if (--arr[b.charAt(i)-'a']<0){
                return false;
            }
        }
        return true;
    }

    /**
     * 560 和为K的子数组
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count=0;
        int[] arr = new int[nums.length];

        arr[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i]=arr[i-1]+nums[i];
        }

        int l=0,r=nums.length;

        for (int i = 0; i < nums.length; i++) {
            if (arr[i]==k){
                count++;
            }

            for (int j = i+1; j < nums.length; j++) {
                if (arr[j]-arr[i]==k){
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 53 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre=0,max=nums[0];

        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre+nums[i],nums[i]);
            max=Math.max(max,pre);
        }

        return max;
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
