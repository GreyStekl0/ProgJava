package cycle;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        int i = 1;
        while (i * i <= N) {
            System.out.print(i * i + " ");
            i++;
        }
    }
}
