package inputOutpit;

import java.util.Scanner;

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] lines = new String[3];
        for (int i = 0; i < 3; i++) {
            lines[i] = scanner.nextLine();
        }
        scanner.close();
        
        for (int i = 2; i >= 0; i--) {
            System.out.println(lines[i]);
        }
    }
}