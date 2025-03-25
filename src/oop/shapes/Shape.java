package oop.shapes;

public abstract class Shape implements Drawable {
  protected String name;
  protected int posX;
  protected int posY;

  public Shape(String name, int x, int y) {
    this.name = name;
    this.posX = x;
    this.posY = y;
  }

  public String getName() {
    return name;
  }

  public String getInfo() {
    return "Shape: " + name + " at position (" + posX + ", " + posY + ")";
  }
}
