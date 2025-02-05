package inputOutpit;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        int experience = scanner.nextInt();
        scanner.close();

        System.out.printf("Привет, меня зовут %s. Я работал программистом %d лет.%n", name, experience);
    }
}
