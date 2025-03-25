package oop.shapes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    try {
      Circle circle = new Circle("My Circle", 10, 10, 5.0);
      Square square = new Square("My Square", 20, 20, 8.0);

      Canvas canvas = new Canvas();
      canvas.addShape(circle);
      canvas.addShape(square);

      canvas.displayInfo();

      canvas.drawAll();

      canvas.moveAll();

      canvas.resizeAll();

      canvas.displayInfo();

      System.out.println("\n=== Individual shape operations ===");
      circle.moveLeft();
      square.decreaseSize();

      canvas.removeShape(circle);

      canvas.drawAll();

    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error occurred in shapes demo", e);
    }
  }
}
