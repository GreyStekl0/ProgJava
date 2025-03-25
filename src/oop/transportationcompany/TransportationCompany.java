package oop.transportationcompany;

import java.util.ArrayList;
import java.util.List;

public class TransportationCompany {
  private String companyName;
  private final List<Vehicle> vehicleFleet;

  public TransportationCompany(String companyName) {
    this.companyName = companyName;
    this.vehicleFleet = new ArrayList<>();
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void addVehicle(Vehicle vehicle) {
    vehicleFleet.add(vehicle);
    System.out.println(vehicle.getVehicleType() + " added to " + companyName + "'s fleet.");
  }

  public void removeVehicle(Vehicle vehicle) {
    boolean removed = vehicleFleet.remove(vehicle);
    if (removed) {
      System.out.println(vehicle.getVehicleType() + " removed from " + companyName + "'s fleet.");
    } else {
      System.out.println("Vehicle not found in the fleet.");
    }
  }

  public void displayAllVehicles() {
    System.out.println("--- " + companyName + "'s Vehicle Fleet ---");
    if (vehicleFleet.isEmpty()) {
      System.out.println("The fleet is empty.");
    } else {
      for (Vehicle vehicle : vehicleFleet) {
        System.out.println(vehicle.getInfo());
      }
    }
    System.out.println("--------------------------------");
  }

  public int getFleetSize() {
    return vehicleFleet.size();
  }
}
