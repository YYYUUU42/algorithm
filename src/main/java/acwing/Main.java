package acwing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int c=scanner.nextInt();

        int[] arr = new int[3];
        arr[0]=a;
        arr[1]=b;
        arr[2]=c;

        Arrays.sort(arr);
        System.out.println(arr[1]);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }
}
