package logicalIfElse;

import java.util.Locale;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();

        if (c * a == b * b) {
            double ratio = (double) b / a;
            System.out.printf(Locale.US, "%.2f%n", ratio);
        } else {
            System.out.println(0);
        }
    }
}