package oop.shapes;

public class Circle extends Shape implements Movable, Resizable {
  private double radius;

  public Circle(String name, int x, int y, double radius) {
    super(name, x, y);
    this.radius = radius;
  }

  @Override
  public void draw() {
    System.out.println(
        "Drawing a circle with radius " + radius
            +
            " at position (" + posX + ", " + posY + ")"
    );
  }

  @Override
  public void moveForward() {
    posY -= 1;
    System.out.println("Moving circle forward to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveBackward() {
    posY += 1;
    System.out.println("Moving circle backward to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveLeft() {
    posX -= 1;
    System.out.println("Moving circle left to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveRight() {
    posX += 1;
    System.out.println("Moving circle right to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void increaseSize() {
    radius *= 1.1;
    System.out.println("Increasing circle radius to " + radius);
  }

  @Override
  public void decreaseSize() {
    if (radius > 0.1) {
      radius *= 0.9;
      System.out.println("Decreasing circle radius to " + radius);
    } else {
      System.out.println("Cannot decrease size: radius is too small");
    }
  }

  @Override
  public String getInfo() {
    return super.getInfo() + ", radius: " + radius;
  }
}
