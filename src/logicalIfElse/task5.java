package logicalIfElse;

import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[4];
        for (int i = 0; i < 4; i++) {
            numbers[i] = scanner.nextInt();
        }
        scanner.close();

        int product = 1;
        boolean hasNegative = false;

        for (int number : numbers) {
            if (number < 0) {
                product *= number;
                hasNegative = true;
            }
        }

        if (hasNegative) {
            System.out.println(product);
        } else {
            System.out.println(0);
        }
    }
}