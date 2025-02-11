package logicalOperations;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();

        int digit4 = (number / 1000) % 10;
        int digit3 = (number / 100) % 10;
        int digit2 = (number / 10) % 10;
        int digit1 = number % 10;

        boolean result = (digit1 + digit3) == Math.abs(digit2 - digit4);
        System.out.println(result);
    }
}