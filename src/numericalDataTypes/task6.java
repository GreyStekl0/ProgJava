package numericalDataTypes;

import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.close();

        double result = (171 * x + 1) / (Math.pow(x, 2) - 5 * x + 17);
        long roundedResult = Math.round(result);

        System.out.println(roundedResult);
    }
}