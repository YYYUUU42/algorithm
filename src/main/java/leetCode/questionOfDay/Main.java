package leetCode.questionOfDay;

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
}
