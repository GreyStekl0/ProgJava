package oop.furniture;

public class Sofa extends Furniture {
  private final int seatingCapacity;
  private final boolean isConvertible;

  public Sofa(String name, String color, String material, double price, int seatingCapacity, boolean isConvertible) {
    super(name, color, material, price);
    this.seatingCapacity = seatingCapacity;
    this.isConvertible = isConvertible;
  }

  public int getSeatingCapacity() {
    return seatingCapacity;
  }

  public boolean isConvertible() {
    return isConvertible;
  }

  @Override
  public void displayInfo() {
    System.out.println("Sofa: " + getName());
    System.out.println("Color: " + getColor());
    System.out.println("Material: " + getMaterial());
    System.out.println("Price: $" + getPrice());
    System.out.println("Seating capacity: " + seatingCapacity);
    System.out.println("Convertible: " + (isConvertible ? "Yes" : "No"));
  }
}
