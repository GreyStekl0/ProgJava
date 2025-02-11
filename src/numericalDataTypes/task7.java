package numericalDataTypes;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        int nextEven = (n % 2 == 0) ? n + 2 : n + 1;
        System.out.println(nextEven);
    }
}