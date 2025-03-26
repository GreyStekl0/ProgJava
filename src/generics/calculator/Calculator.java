package generics.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

  public static <T1 extends Number, T2 extends Number> Number sum(T1 a, T2 b) {
    if (a instanceof BigDecimal || b instanceof BigDecimal) {
      return toBigDecimal(a).add(toBigDecimal(b));
    } else if (a instanceof Double || b instanceof Double) {
      return a.doubleValue() + b.doubleValue();
    } else if (a instanceof Float || b instanceof Float) {
      return a.floatValue() + b.floatValue();
    } else if (a instanceof Long || b instanceof Long) {
      return a.longValue() + b.longValue();
    } else {
      return a.intValue() + b.intValue();
    }
  }

  public static <T1 extends Number, T2 extends Number> Number multiply(T1 a, T2 b) {
    if (a instanceof BigDecimal || b instanceof BigDecimal) {
      return toBigDecimal(a).multiply(toBigDecimal(b));
    } else if (a instanceof Double || b instanceof Double) {
      return a.doubleValue() * b.doubleValue();
    } else if (a instanceof Float || b instanceof Float) {
      return a.floatValue() * b.floatValue();
    } else if (a instanceof Long || b instanceof Long) {
      return a.longValue() * b.longValue();
    } else {
      return a.intValue() * b.intValue();
    }
  }

  public static <T1 extends Number, T2 extends Number> Number divide(T1 a, T2 b) {
    if (b.doubleValue() == 0) {
      throw new ArithmeticException("Division by zero is not allowed");
    }

    if (a instanceof BigDecimal || b instanceof BigDecimal) {
      return toBigDecimal(a).divide(toBigDecimal(b), 3, RoundingMode.HALF_UP);
    }

    if (a instanceof Double || b instanceof Double) {
      return a.doubleValue() / b.doubleValue();
    }
    if (a instanceof Float || b instanceof Float) {
      return a.floatValue() / b.floatValue();
    }

    return integerDivision(a, b);
  }

  private static <T1 extends Number, T2 extends Number> Number integerDivision(T1 a, T2 b) {
    if (a instanceof Long || b instanceof Long) {
      long x = a.longValue();
      long y = b.longValue();
      return (x % y == 0) ? (x / y) : ((double) x / y);
    } else {
      int x = a.intValue();
      int y = b.intValue();
      return (x % y == 0) ? (x / y) : ((double) x / y);
    }
  }

  public static <T1 extends Number, T2 extends Number> Number subtraction(T1 a, T2 b) {
    if (a instanceof BigDecimal || b instanceof BigDecimal) {
      return toBigDecimal(a).subtract(toBigDecimal(b));
    } else if (a instanceof Double || b instanceof Double) {
      return a.doubleValue() - b.doubleValue();
    } else if (a instanceof Float || b instanceof Float) {
      return a.floatValue() - b.floatValue();
    } else if (a instanceof Long || b instanceof Long) {
      return a.longValue() - b.longValue();
    } else {
      return a.intValue() - b.intValue();
    }
  }

  private static BigDecimal toBigDecimal(Number number) {
    if (number instanceof BigDecimal) {
      return (BigDecimal) number;
    } else {
      return new BigDecimal(number.toString());
    }
  }

  @Override
  public String toString() {
    return "Calculator for performing arithmetic operations on different number types";
  }
}
