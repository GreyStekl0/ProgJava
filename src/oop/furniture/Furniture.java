package oop.furniture;

public abstract class Furniture {
  private String name;
  private String color;
  private final String material;
  private double price;

  public Furniture(String name, String color, String material, double price) {
    this.name = name;
    this.color = color;
    this.material = material;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMaterial() {
    return material;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public abstract void displayInfo();
}
