package oop.furniture;

import java.util.ArrayList;
import java.util.List;

public class Shop {
  private String name;
  private final List<Furniture> inventory;

  public Shop(String name) {
    this.name = name;
    this.inventory = new ArrayList<>();
  }

  public void addFurniture(Furniture furniture) {
    inventory.add(furniture);
    System.out.println(furniture.getName() + " added to " + name + "'s inventory.");
  }

  public boolean removeFurniture(Furniture furniture) {
    boolean removed = inventory.remove(furniture);
    if (removed) {
      System.out.println(furniture.getName() + " removed from " + name + "'s inventory.");
    } else {
      System.out.println("Furniture not found in inventory.");
    }
    return removed;
  }

  public void displayInventory() {
    System.out.println("\n=== " + name + "'s Inventory ===");
    if (inventory.isEmpty()) {
      System.out.println("Inventory is empty.");
    } else {
      for (Furniture furniture : inventory) {
        furniture.displayInfo();
        System.out.println("-----------------------");
      }
    }
  }

  public double calculateTotalValue() {
    double totalValue = 0;
    for (Furniture furniture : inventory) {
      totalValue += furniture.getPrice();
    }
    return totalValue;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
