package oop.transportationcompany;

public class Bike implements Vehicle {
  private final String brand;
  private final String model;
  private final double maxSpeed;

  public Bike(String brand, String model, double maxSpeed) {
    this.brand = brand;
    this.model = model;
    this.maxSpeed = maxSpeed;
  }

  @Override
  public double getSpeed() {
    return maxSpeed;
  }

  @Override
  public double getFuelConsumption() {
    return 0;
  }

  @Override
  public String getVehicleType() {
    return "Bike";
  }

  @Override
  public String getInfo() {
    return "Bike: " + brand + " " + model
        +
        ", Max Speed: " + maxSpeed + " km/h"
        +
        ", No fuel consumption (eco-friendly)";
  }
}
