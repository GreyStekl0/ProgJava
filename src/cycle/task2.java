package cycle;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        int count = 0;

        while (true) {
            int number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            if (number > max) {
                max = number;
                count = 1;
            } else if (number == max) {
                count++;
            }
        }
        scanner.close();

        System.out.println(max + " " + count);
    }
}