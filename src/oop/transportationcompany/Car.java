package oop.transportationcompany;

public class Car implements Vehicle {
  private final String brand;
  private final String model;
  private final double maxSpeed;
  private final double fuelConsumption;

  public Car(String brand, String model, double maxSpeed, double fuelConsumption) {
    this.brand = brand;
    this.model = model;
    this.maxSpeed = maxSpeed;
    this.fuelConsumption = fuelConsumption;
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
    return "Car";
  }

  @Override
  public String getInfo() {
    return "Car: " + brand + " " + model
        +
        ", Max Speed: " + maxSpeed + " km/h"
        +
        ", Fuel Consumption: " + fuelConsumption + " l/100km";
  }
}
