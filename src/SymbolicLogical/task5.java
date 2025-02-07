package SymbolicLogical;

import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char firstChar = scanner.next().charAt(0);
        char secondChar = scanner.next().charAt(0);
        scanner.close();

        int sum = firstChar + secondChar;
        char resultChar = (char) sum;

        System.out.println(sum);
        System.out.println(resultChar);
    }
}