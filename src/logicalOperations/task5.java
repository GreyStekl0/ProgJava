package logicalOperations;

import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.close();

        boolean isInRange = (x <= -4) || (x > -2 && x <= 7) || (x >= 31);
        System.out.println(isInRange);
    }
}