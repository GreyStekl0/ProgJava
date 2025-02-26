package string;

public class task1 {
    public static boolean isPalindrome(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String[] str = cleanedText.split("");
        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals(str[str.length - i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "Madam, I'm Adam!";
        System.out.println(isPalindrome(str));
    }
}