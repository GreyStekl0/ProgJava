package twoDimensionalArrays;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        int[][] array = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                array[row][col] = scanner.nextInt();
            }
        }
        scanner.close();

        for (int row = 0; row < n; row++) {
            int temp = array[row][i];
            array[row][i] = array[row][j];
            array[row][j] = temp;
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }
    }
}
