package logicalIfElse;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        scanner.close();

        if (score >= 81 && score <= 100) {
            System.out.println("отлично");
        } else if (score >= 69 && score <= 80) {
            System.out.println("хорошо");
        } else if (score >= 46 && score <= 68) {
            System.out.println("удовлетворительно");
        } else {
            System.out.println("плохо");
        }
    }
}