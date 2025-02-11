package cycle;

import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        int result = 1;
        for (int i = 1; i <= n; i++) {
            result*=i;
        }
        System.out.println(result);
    }
}
