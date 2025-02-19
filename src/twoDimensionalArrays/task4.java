package twoDimensionalArrays;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] seats = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                seats[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            int consecutiveFreeSeats = 0;
            for (int j = 0; j < m; j++) {
                if (seats[i][j] == 0) {
                    consecutiveFreeSeats++;
                    if (consecutiveFreeSeats == k) {
                        System.out.println(i + 1);
                        return;
                    }
                } else {
                    consecutiveFreeSeats = 0;
                }
            }
        }
        System.out.println(0);
    }
}
