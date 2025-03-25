package oop.furniture;

public class Main {
  public static void main(String[] args) {
    // Create a shop
    Shop furnitureShop = new Shop("Cozy Home Furniture");

    // Create different types of furniture
    Sofa sofa = new Sofa("Luxury Sofa", "Black", "Leather", 999.99, 3, true);
    Chair chair = new Chair("Office Chair", "Gray", "Mesh", 199.99, true, true);
    Table table = new Table("Dining Table", "Brown", "Oak", 499.99, 180, 90, 75);

    // Add furniture to the shop
    furnitureShop.addFurniture(sofa);
    furnitureShop.addFurniture(chair);
    furnitureShop.addFurniture(table);

    // Display inventory
    furnitureShop.displayInventory();

    // Calculate and display total value
    System.out.println("Total inventory value: $" + furnitureShop.calculateTotalValue());

    // Remove an item
    furnitureShop.removeFurniture(chair);

    // Display updated inventory
    furnitureShop.displayInventory();

    // Display updated total value
    System.out.println("Updated total inventory value: $" + furnitureShop.calculateTotalValue());
  }
}
