package oop.shapes;

public class Square extends Shape implements Movable, Resizable {
  private double side;

  public Square(String name, int x, int y, double side) {
    super(name, x, y);
    this.side = side;
  }

  @Override
  public void draw() {
    System.out.println(
        "Drawing a square with side " + side
            +
            " at position (" + posX + ", " + posY + ")"
    );
  }

  @Override
  public void moveForward() {
    posY -= 1;
    System.out.println("Moving square forward to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveBackward() {
    posY += 1;
    System.out.println("Moving square backward to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveLeft() {
    posX -= 1;
    System.out.println("Moving square left to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void moveRight() {
    posX += 1;
    System.out.println("Moving square right to position (" + posX + ", " + posY + ")");
  }

  @Override
  public void increaseSize() {
    side *= 1.1;
    System.out.println("Increasing square side to " + side);
  }

  @Override
  public void decreaseSize() {
    if (side > 0.1) {
      side *= 0.9;
      System.out.println("Decreasing square side to " + side);
    } else {
      System.out.println("Cannot decrease size: side is too small");
    }
  }

  @Override
  public String getInfo() {
    return super.getInfo() + ", side: " + side;
  }
}
