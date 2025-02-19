package massif;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();

        for (int i = 1; i < n; i++) {
            if (array[i] > array[i - 1]) {
                System.out.print(array[i] + " ");
            }
        }
    }
}