package SymbolicLogical;

import java.util.Scanner;

public class task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char lowerCaseChar = scanner.next().charAt(0);
        scanner.close();

        char upperCaseChar = (char) (lowerCaseChar - 'a' + 'A');
        System.out.println(upperCaseChar);
    }
}