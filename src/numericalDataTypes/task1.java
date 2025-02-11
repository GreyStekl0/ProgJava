package numericalDataTypes;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long num1 = scanner.nextLong();
        Long num2 = scanner.nextLong();
        scanner.close();

        Long result = num1 + num2;
        System.out.println(result);
    }
}
