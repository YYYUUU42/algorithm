package leetCode.competition;

import org.junit.Test;

import java.util.*;

public class Main {

    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < mountain.length - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                list.add(i);
            }
        }

        return list;
    }

    public boolean areSimilar(int[][] mat, int k) {
        int len = mat[0].length;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < len; j++) {
                //偶数行
                if (i % 2 == 0) {
                    if (mat[i][j] != mat[i][(j + k) % len]) {
                        return false;
                    }
                } else {
                    if (mat[i][(j + k) % len] != mat[i][j]) {

                    }
                }
            }
        }

        return true;
    }

    public int beautifulSubstrings(String s, int k) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            int vowels = 0;
            int consonants = 0;

            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }

                if ((vowels == consonants) && (vowels * consonants % k == 0)) {
                    count++;
                }
            }
        }

        return count;
    }


    public int minimumSum(int[] nums) {
        int res = Integer.MAX_VALUE;
        int[] lMin = new int[nums.length];
        int[] rMin = new int[nums.length];

        //左侧
        int min = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            lMin[i] = min;
            min = Math.min(min, nums[i]);
        }

        //右侧
        min = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            rMin[i] = min;
            min = Math.min(min, nums[i]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > lMin[i] && nums[i] > rMin[i]) {
                res=Math.min(res,nums[i]+lMin[i]+rMin[i]);
            }
        }

        return res==Integer.MAX_VALUE?-1:res;
    }

    @Test
    public void test() {
        System.out.println();
    }
}
