public abstract class Vehicle {
    private String licensePlate;
    private String color;
    private double pricePerDay;
    private boolean isRented;
    private String model;   // New property for vehicle model
    private int year;       // New property for manufacturing year

    // Constructor with model and year parameters
    public Vehicle(String licensePlate, String color, double pricePerDay, String model, int year) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isRented = false;

        this.year = year;
    }

    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColor() {
        return color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setRented(boolean rented) {
        isRented = rented;
    }

    // Method to display detailed information about the vehicle
    public void displayVehicleInfo() {
        System.out.println("Vehicle Information:");
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Color: " + color);
        System.out.println("Price per Day: $" + pricePerDay);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Available: " + (!isRented ? "Yes" : "No"));
    }
}
