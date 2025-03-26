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
    // Check for division by zero
    if (b.doubleValue() == 0) {
      throw new ArithmeticException("Division by zero is not allowed");
    }

    if (a instanceof BigDecimal || b instanceof BigDecimal) {
      return toBigDecimal(a).divide(toBigDecimal(b), 10, RoundingMode.HALF_UP);
    } else if (a instanceof Double || b instanceof Double) {
      return a.doubleValue() / b.doubleValue();
    } else if (a instanceof Float || b instanceof Float) {
      return a.floatValue() / b.floatValue();
    } else if (a instanceof Long || b instanceof Long) {
      if (a.longValue() % b.longValue() == 0) {
        return a.longValue() / b.longValue();
      } else {
        return (double) a.longValue() / b.longValue();
      }
    } else {
      // Return integer if result is whole number, otherwise double
      if (a.intValue() % b.intValue() == 0) {
        return a.intValue() / b.intValue();
      } else {
        return (double) a.intValue() / b.intValue();
      }
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
