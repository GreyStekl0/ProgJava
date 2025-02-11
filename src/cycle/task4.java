package cycle;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        int i = 1;
        while (i <= N) {
            System.out.printf("Квадрат числа %d равен %d%n", i, i*i);
            i++;
        }
    }
}
