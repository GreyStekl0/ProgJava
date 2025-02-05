package inputOutpit;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        int num4 = scanner.nextInt();
        int num5 = scanner.nextInt();
        scanner.close();

        System.out.printf("%d, %d, %d, %d, %d%n", num1, num2, num3, num4, num5);
    }
}