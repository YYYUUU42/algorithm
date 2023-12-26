package leetCode.daily;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        int mod = 1000000007;

        for (int i = 0; i < n; i++) {
            int len = scanner.nextInt();
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                int i1 = scanner.nextInt();
                integers.add(i1);
            }
            list.add(integers);
        }

        for (int i = 0; i < list.size(); i++) {
            long sum = 0;
            long l = 0;
            int res = 0;

            List<Integer> arr = list.get(i);
            for (int j = 0; j < arr.size(); j++) {
                sum += arr.get(j);
            }
            if (sum % 2 == 0) {
                res += 2;
            }

            for (int j = 0; j < arr.size(); j++) {
                l += arr.get(j);
                if (l % 2 == 0) {
                    res++;
                }
                if (sum - l % 2 == 0) {
                    res++;
                }
                res=res%mod;
            }
            System.out.println(res);
        }

    }


}
