package numericalDataTypes;

import java.util.Scanner;

public class task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int b1 = scanner.nextInt();
        int q = scanner.nextInt();
        int N = scanner.nextInt();
        scanner.close();

        long result = (long) (b1 * Math.pow(q, N - 1));
        System.out.println(result);
    }
}