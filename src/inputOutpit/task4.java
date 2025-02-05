package inputOutpit;

public class task4 {
    public static void main(String[] args) {
        int layers = 4;
        for (int i = 1; i <= layers; i++) {
            for (int j = i; j < layers; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}