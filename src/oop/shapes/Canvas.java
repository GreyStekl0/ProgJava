package oop.shapes;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
  private List<Shape> shapes;

  public Canvas() {
    shapes = new ArrayList<>();
  }

  public void addShape(Shape shape) {
    if (shape != null) {
      shapes.add(shape);
      System.out.println("Added shape: " + shape.getName());
    } else {
      System.out.println("Error: Cannot add null shape");
    }
  }

  public void removeShape(Shape shape) {
    if (shapes.contains(shape)) {
      shapes.remove(shape);
      System.out.println("Removed shape: " + shape.getName());
    } else {
      System.out.println("Error: Shape not found on canvas");
    }
  }

  public void drawAll() {
    System.out.println("\n=== Drawing all shapes ===");
    for (Shape shape : shapes) {
      shape.draw();
    }
  }

  public void moveAll() {
    System.out.println("\n=== Moving all shapes ===");
    for (Shape shape : shapes) {
      if (shape instanceof Movable) {
        Movable movable = (Movable) shape;
        movable.moveRight();
      } else {
        System.out.println(shape.getName() + " is not movable");
      }
    }
  }

  public void resizeAll() {
    System.out.println("\n=== Resizing all shapes ===");
    for (Shape shape : shapes) {
      if (shape instanceof Resizable) {
        Resizable resizable = (Resizable) shape;
        resizable.increaseSize();
      } else {
        System.out.println(shape.getName() + " is not resizable");
      }
    }
  }

  public void displayInfo() {
    System.out.println("\n=== Shapes information ===");
    for (Shape shape : shapes) {
      System.out.println(shape.getInfo());
    }
  }
}
