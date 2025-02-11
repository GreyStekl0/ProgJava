package numericalDataTypes;

import java.util.Scanner;

public class task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.close();

        int totalKopecks = (a * 100 + b) * n;
        int rubles = totalKopecks / 100;
        int kopecks = totalKopecks % 100;

        System.out.printf("%d %d%n", rubles, kopecks);
    }
}