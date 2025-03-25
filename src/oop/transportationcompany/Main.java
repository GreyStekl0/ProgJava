package oop.transportationcompany;

public class Main {
  public static void main(String[] args) {
    TransportationCompany fastTransport = new TransportationCompany("Fast Transport");

    Car car1 = new Car("Toyota", "Camry", 220, 7.5);
    Car car2 = new Car("Honda", "Accord", 210, 8.0);
    Truck truck1 = new Truck("Volvo", "FH16", 120, 30.0, 20.0);
    Bike bike1 = new Bike("Giant", "Defy", 40);

    fastTransport.addVehicle(car1);
    fastTransport.addVehicle(car2);
    fastTransport.addVehicle(truck1);
    fastTransport.addVehicle(bike1);

    fastTransport.displayAllVehicles();

    fastTransport.removeVehicle(car2);

    fastTransport.displayAllVehicles();
  }
}
