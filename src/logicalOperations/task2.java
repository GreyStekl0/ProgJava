package logicalOperations;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();
        scanner.close();

        boolean exists = (A + B > C) && (A + C > B) && (B + C > A);
        System.out.println(exists);
    }
}