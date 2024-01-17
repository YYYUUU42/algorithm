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


    public boolean hasTrailingZeros(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                count++;
            }

            if (count >= 2) {
                return true;
            }
        }

        return false;
    }

    public int[] runningSum(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }

        return arr;
    }

    public String defangIPaddr(String address) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) != '.') {
                stringBuilder.append(address.charAt(i));
            } else {
                stringBuilder.append("[.]");
            }
        }

        return stringBuilder.toString();
    }

    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (int key : map.keySet()) {
            if (key == map.get(key) && key > max) {
                max = key;
            }
        }

        return max;
    }

    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[nums.length];
        int l = 0, r = n;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                arr[i] = nums[l++];
            } else {
                arr[i] = nums[r++];
            }
        }

        return arr;
    }

    public int maxProduct(int[] nums) {
        int len = nums.length - 1;
        Arrays.sort(nums);
        return (nums[len] - 1) * (nums[len - 1] - 1);
    }


    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] split = sentence.split(" ");

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.length() < searchWord.length()) {
                continue;
            }

            if (s.substring(0, searchWord.length()).equals(searchWord)) {
                return i;
            }
        }

        return -1;
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                count++;
            }
        }

        return count;
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
            if (count >= k) {
                res += nums.length - j + 1;
            }
            if (nums[i] == max) {
                count--;
            }
        }

        return res;
    }

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] arr = new int[n * n + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int num = grid[i][j];
                arr[num]++;
            }
        }

        int[] res = new int[2];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 2) {
                res[0] = i;
            } else if (arr[i] == 0) {
                res[1] = i;
            }
        }

        return res;
    }

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length / 3;
        int[][] arr = new int[n][3];
        int count1 = 0;

        for (int i = 0; i < n; i++) {
            int[] ints = new int[3];
            for (int j = 0; j < 3; j++) {
                int count = i * 3 + j;
                ints[j] = nums[count];
            }
            arr[count1] = ints;
            count1++;
        }

        for (int i = 0; i < arr.length; i++) {
            int[] array = arr[i];
            if (array[2] - array[0] > k) {
                return new int[0][];
            }
        }
        return arr;
    }

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int res = -1;
        double lenMax = -1;

        Map<Double, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < dimensions.length; i++) {
            int x = dimensions[i][0];
            int y = dimensions[i][1];
            double len = Math.sqrt(x * x + y * y);
            List<Integer> list = map.getOrDefault(len, new ArrayList<>());
            list.add(x * y);
            map.put(len, list);
        }

        for (double key : map.keySet()) {
            if (key > lenMax) {
                lenMax = key;
            }
        }

        List<Integer> list = map.get(lenMax);
        for (Integer integer : list) {
            if (integer > res) {
                res = integer;
            }
        }


        return res;
    }

    public int maxFrequencyElements(int[] nums) {
        int max = -1;
        int[] arr = new int[101];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                res += arr[i];
            }
        }

        return res;
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= s.length() - a.length(); i++) {
            String sub = s.substring(i, i + a.length());
            if (sub.equals(a)) {
                aList.add(i);
            }
        }

        for (int i = 0; i <= s.length() - b.length(); i++) {
            String sub = s.substring(i, i + b.length());
            if (sub.equals(b)) {
                bList.add(i);
            }
        }

        for (int i = 0; i < aList.size(); i++) {
            for (int j = 0; j < bList.size(); j++) {
                int num = Math.abs(aList.get(i) - bList.get(j));
                if (num <= k) {
                    res.add(aList.get(i));
                    break;
                }
            }
        }

        return res;
    }

    public long findMaximumNumber(long k, int x) {
        long count = 0;
        int n = 1;
        while (true) {
            count += getBinary(n, x);

            if (count>k){
                break;
            }
            n++;
        }

        return n;
    }

    public int getBinary(int num, int x) {
        if (num < x) {
            return 0;
        }

        String res = "";
        while (num > 0) {
            int n = num % 2;
            num /= 2;
            res = res + String.valueOf(n);
        }

        int sum = 0;
        for (int i = 1; i <= res.length(); i++) {
            if (i % x == 0) {
                String sub = res.substring(i - 1, i);
                sum += Integer.valueOf(sub);
            }
        }

        return sum;
    }


    @Test
    public void test() {
    }
}
