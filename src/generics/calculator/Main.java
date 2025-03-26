package generics.calculator;


import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    System.out.println(calculator);
    System.out.println();

    testAddition();
    testMultiplication();
    testDivision();
    testSubtraction();
    testExceptions();
  }

  private static void testAddition() {
    System.out.println("=== Addition Tests ===");
    System.out.println("5 + 3 = " + Calculator.sum(5, 3));
    System.out.println("5 + 3.5 = " + Calculator.sum(5, 3.5));
    System.out.println("5L + 3 = " + Calculator.sum(5L, 3));
    System.out.println("5.5f + 3.5 = " + Calculator.sum(5.5f, 3.5));
    System.out.println("BigDecimal(5) + 3 = " + Calculator.sum(new BigDecimal("5"), 3));
    System.out.println();
  }

  private static void testMultiplication() {
    System.out.println("=== Multiplication Tests ===");
    System.out.println("5 * 3 = " + Calculator.multiply(5, 3));
    System.out.println("5 * 3.5 = " + Calculator.multiply(5, 3.5));
    System.out.println("5L * 3 = " + Calculator.multiply(5L, 3));
    System.out.println("5.5f * 3.5 = " + Calculator.multiply(5.5f, 3.5));
    System.out.println("BigDecimal(5) * 3 = " + Calculator.multiply(new BigDecimal("5"), 3));
    System.out.println();
  }

  private static void testDivision() {
    System.out.println("=== Division Tests ===");
    System.out.println("6 / 3 = " + Calculator.divide(6, 3));
    System.out.println("5 / 2 = " + Calculator.divide(5, 2));
    System.out.println("5L / 2 = " + Calculator.divide(5L, 2));
    System.out.println("5.5f / 2 = " + Calculator.divide(5.5f, 2));
    System.out.println("BigDecimal(5) / 2 = " + Calculator.divide(new BigDecimal("5"), 2));
    System.out.println();
  }

  private static void testSubtraction() {
    System.out.println("=== Subtraction Tests ===");
    System.out.println("5 - 3 = " + Calculator.subtraction(5, 3));
    System.out.println("5 - 3.5 = " + Calculator.subtraction(5, 3.5));
    System.out.println("5L - 3 = " + Calculator.subtraction(5L, 3));
    System.out.println("5.5f - 3.5 = " + Calculator.subtraction(5.5f, 3.5));
    System.out.println("BigDecimal(5) - 3 = " + Calculator.subtraction(new BigDecimal("5"), 3));
    System.out.println();
  }

  private static void testExceptions() {
    System.out.println("=== Exception Tests ===");
    try {
      System.out.println("5 / 0 = " + Calculator.divide(5, 0));
    } catch (ArithmeticException e) {
      System.out.println("Caught exception: " + e.getMessage());
    }

    try {
      System.out.println("BigDecimal(5) / 0 = " + Calculator.divide(new BigDecimal("5"), 0));
    } catch (ArithmeticException e) {
      System.out.println("Caught exception: " + e.getMessage());
    }
  }
}