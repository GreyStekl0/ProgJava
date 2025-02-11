package logicalOperations;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.close();

        boolean isInRange = (x >= -43 && x <= 29);
        System.out.println(isInRange);
    }
}