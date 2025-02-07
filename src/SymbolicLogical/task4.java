package SymbolicLogical;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        int length = input.length();
        System.out.println(length);
        System.out.println(length <= 30);
    }
}