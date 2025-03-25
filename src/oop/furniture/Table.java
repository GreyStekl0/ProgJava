package oop.furniture;

public class Table extends Furniture {
  private final double length;
  private final double width;
  private final double height;

  public Table(
      String name,
      String color,
      String material,
      double price,
      double length,
      double width,
      double height
  ) {
    super(name, color, material, price);
    this.length = length;
    this.width = width;
    this.height = height;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public double getArea() {
    return length * width;
  }

  @Override
  public void displayInfo() {
    System.out.println("Table: " + getName());
    System.out.println("Color: " + getColor());
    System.out.println("Material: " + getMaterial());
    System.out.println("Price: $" + getPrice());
    System.out.println("Dimensions: " + length + "x" + width + "x" + height + " cm");
    System.out.println("Surface area: " + getArea() + " cm²");
  }
}
