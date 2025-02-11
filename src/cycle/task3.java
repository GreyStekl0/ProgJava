package cycle;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = 0;
        int min = 0;

        while (true) {
            int number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            if (number > max) {
                max = number;
            } else if (number < min) {
                min = number;
            }
        }
        scanner.close();

        System.out.println(min + " " + max);
    }
}
