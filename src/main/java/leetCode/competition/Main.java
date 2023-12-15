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
                res = Math.min(res, nums[i] + lMin[i] + rMin[i]);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        int[] res = new int[2];

        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (set1.contains(nums1[i])) {
                count++;
            }
        }
        res[0] = count;
        count = 0;

        for (int i = 0; i < nums2.length; i++) {
            if (set2.contains(nums2[i])) {
                count++;
            }
        }
        res[1] = count;

        return res;
    }

    public int removeAlmostEqualCharacters(String word) {
        int res = 0;
        for (int i = 1; i < word.length(); i++) {
            if (Math.abs(word.charAt(i) - word.charAt(i - 1)) <= 1) {
                res++;
                i++;
            }
        }

        return res;
    }

    public int countTestedDevices(int[] batteryPercentages) {
        int count = 0;
        for (int i = 0; i < batteryPercentages.length - 1; i++) {
            if (batteryPercentages[i] > 0) {
                for (int j = i + 1; j < batteryPercentages.length; j++) {
                    batteryPercentages[j]--;
                }
                count++;
            }
        }
        if (batteryPercentages[batteryPercentages.length - 1] > 0) {
            count++;
        }
        return count;
    }

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] arr = variables[i];
            int num = pow(pow(arr[0], arr[1], 10), arr[2], arr[3]);
            if (num == target) {
                list.add(i);
            }
        }
        return list;
    }

    public int pow(int x, int y, int mod) {
        if (x == 0) {
            return 0;
        }
        if (y == 0) {
            return 1;
        }

        int res = 1;
        while (y > 0) {
            if (y % 2 != 0) {
                res = res * y % mod;
            }
            x = x * x % mod;
            y = y / 2;
        }

        return res;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? myPow1(x, n) : 1.0 / myPow1(x, -n);
    }

    public double myPow1(double x, long n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        double res = 1;

        while (n != 0) {
            if (n % 2 != 0) {
                res = res * x;
            }
            x = x * x;
            n = n / 2;
        }

        return res;
    }


    public long countSubarrays(int[] nums, int k) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            count++;
        }
        if (count < k) {
            return 0;
        }

        count = 0;
        long res = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (j < nums.length && count < k) {
                if (nums[j] == max) {
                    count++;
                }
                j++;
            }
            if (count>=k){
                res += nums.length - j + 1;
            }
            if (nums[i] == max) {
                count--;
            }
        }

        return res;
    }


    @Test
    public void test() {
        int[] arr = {31, 12, 21, 24};
        double v = Math.pow((Math.pow(arr[0], arr[1]) % 10), arr[2]) % arr[3];
        System.out.println(v);
    }
}
