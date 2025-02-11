package logicalOperations;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();

        boolean isBeautiful = (number >= 100 && number <= 999) && (number % 3 == 0) && (number % 7 == 1);
        System.out.println(isBeautiful);
    }
}