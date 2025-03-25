package oop.transportationcompany;

public class Truck implements Vehicle {
  private final String brand;
  private final String model;
  private final double maxSpeed;
  private final double fuelConsumption;
  private final double cargoCapacity;

  public Truck(
      String brand,
      String model,
      double maxSpeed,
      double fuelConsumption,
      double cargoCapacity
  ) {
    this.brand = brand;
    this.model = model;
    this.maxSpeed = maxSpeed;
    this.fuelConsumption = fuelConsumption;
    this.cargoCapacity = cargoCapacity;
  }

  @Override
  public double getSpeed() {
    return maxSpeed;
  }

  @Override
  public double getFuelConsumption() {
    return fuelConsumption;
  }

  @Override
  public String getVehicleType() {
    return "Truck";
  }

  @Override
  public String getInfo() {
    return "Truck: " + brand + " " + model
        +
        ", Max Speed: " + maxSpeed + " km/h"
        +
        ", Fuel Consumption: " + fuelConsumption + " l/100km"
        +
        ", Cargo Capacity: " + cargoCapacity + " tons";
  }
}
