package leetCode;

import java.util.*;

import org.junit.Test;

public class hot100 {
    /**
     * 1
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
     * 49
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
     * 283
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int l=0,r=0;
        while(r<nums.length){
            if (nums[r]==0){
                r++;
            }else{
                int t=nums[r];
                nums[r]=nums[l];
                nums[l]=t;
                l++;
                r++;
            }
        }

    }

    @Test
    public void test(){
        String[] strings={"eat","tea","tan","ate","nat","bat"};
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
