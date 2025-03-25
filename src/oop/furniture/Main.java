package oop.furniture;

public class Main {
  public static void main(String[] args) {
    Shop furnitureShop = new Shop("Cozy Home Furniture");

    Sofa sofa = new Sofa("Luxury Sofa", "Black", "Leather", 999.99, 3, true);
    Chair chair = new Chair("Office Chair", "Gray", "Mesh", 199.99, true, true);
    Table table = new Table("Dining Table", "Brown", "Oak", 499.99, 180, 90, 75);

    furnitureShop.addFurniture(sofa);
    furnitureShop.addFurniture(chair);
    furnitureShop.addFurniture(table);

    furnitureShop.displayInventory();

    System.out.println("Total inventory value: $" + furnitureShop.calculateTotalValue());

    furnitureShop.removeFurniture(chair);

    furnitureShop.displayInventory();

    System.out.println("Updated total inventory value: $" + furnitureShop.calculateTotalValue());
  }
}
