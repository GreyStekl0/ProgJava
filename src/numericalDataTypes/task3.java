package numericalDataTypes;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        scanner.close();

        char lastNumber = number.charAt(number.length() - 1);
        System.out.println(lastNumber);
    }
}
