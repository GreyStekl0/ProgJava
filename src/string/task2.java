package string;

public class task2 {
    public static String printTextPerRole(String[] roles, String[] textLines) {
        java.util.Map<String, java.util.List<String>> roleLines = new java.util.HashMap<>();
        for (String role : roles) {
            roleLines.put(role, new java.util.ArrayList<>());
        }

        for (int i = 0; i < textLines.length; i++) {
            String line = textLines[i];
            int colonIndex = line.indexOf(':');
            String role = line.substring(0, colonIndex);
            String text = line.substring(colonIndex + 1).trim();
            roleLines.get(role).add((i + 1) + ") " + text);
        }

        StringBuilder result = new StringBuilder();
        for (String role : roles) {
            result.append(role).append(":\n");
            java.util.List<String> lines = roleLines.get(role);
            for (String line : lines) {
                result.append(line).append("\n");
            }
            result.append("\n");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        String[] roles = {
                "Городничий",
                "Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"
        };

        String[] textLines = {
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"
        };
    System.out.println(printTextPerRole(roles, textLines));
    }
}