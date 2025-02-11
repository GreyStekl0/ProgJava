package logicalOperations;

import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        scanner.close();

        boolean isSymmetrical = number.charAt(0) == number.charAt(3) && number.charAt(1) == number.charAt(2);
        System.out.println(isSymmetrical);
    }
}