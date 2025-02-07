package SymbolicLogical;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstString = scanner.nextLine();
        String secondString = scanner.nextLine();
        scanner.close();

        System.out.println(firstString.equalsIgnoreCase(secondString));
    }
}