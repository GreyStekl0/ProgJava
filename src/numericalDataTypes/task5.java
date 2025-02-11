package numericalDataTypes;

import java.util.Locale;
import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        double number = scanner.nextDouble();
        scanner.close();

        double result = number - (int) number;
        System.out.println(result);
    }
}