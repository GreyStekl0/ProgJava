package numericalDataTypes;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double rib = scanner.nextInt();
        scanner.close();

        int volume = (int) Math.pow(rib, 3.0);
        int area = (int) (6 * Math.pow(rib, 2));

        System.out.printf("%d %d%n", volume, area);
    }
}
