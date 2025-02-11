package numericalDataTypes;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        scanner.close();

        int firstNumber = number.charAt(0) - '0';
        int secondNumber = number.charAt(1) - '0';
        int threeNumber = number.charAt(2) - '0';

        int result = firstNumber + secondNumber + threeNumber;
        System.out.println(result);
    }
}
