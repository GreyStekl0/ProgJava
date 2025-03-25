package oop.furniture;

public class Chair extends Furniture {
  private final boolean hasArmrests;
  private final boolean isAdjustable;

  public Chair(
      String name,
      String color,
      String material,
      double price,
      boolean hasArmrests,
      boolean isAdjustable
  ) {
    super(name, color, material, price);
    this.hasArmrests = hasArmrests;
    this.isAdjustable = isAdjustable;
  }

  public boolean hasArmrests() {
    return hasArmrests;
  }

  public boolean isAdjustable() {
    return isAdjustable;
  }

  @Override
  public void displayInfo() {
    System.out.println("Chair: " + getName());
    System.out.println("Color: " + getColor());
    System.out.println("Material: " + getMaterial());
    System.out.println("Price: $" + getPrice());
    System.out.println("Has armrests: " + (hasArmrests ? "Yes" : "No"));
    System.out.println("Adjustable: " + (isAdjustable ? "Yes" : "No"));
  }
}
