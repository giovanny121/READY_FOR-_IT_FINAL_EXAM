import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Main class for the Rental System
public class Main {
    public static void main(String[] args) throws Exception {
        // Initialize scanner for user input and rental management system
        Scanner scanner = new Scanner(System.in);
        RentalManagement system = new RentalManagement();

        // Add Vehicles with model, year, and other attributes
        Vehicle car1 = new Car("CAR001", "Blue", 50, "Toyota Camry", 2021);
        Vehicle car2 = new Car("CAR002", "Silver", 55, "Honda Accord", 2019);
        Vehicle truck1 = new Truck("TRUCK001", "Red", 100, "Ford F-150", 2022);
        Vehicle truck2 = new Truck("TRUCK002", "Black", 120, "Ram 1500", 2020);
        Vehicle motorcycle1 = new Motorcycle("BIKE001", "Black", 30, "Yamaha R3", 2018);
        Vehicle motorcycle2 = new Motorcycle("BIKE002", "Green", 40, "Kawasaki Ninja", 2023);

        // Add vehicles to the rental management system
        system.addVehicle(car1);
        system.addVehicle(car2);
        system.addVehicle(truck1);
        system.addVehicle(truck2);
        system.addVehicle(motorcycle1);
        system.addVehicle(motorcycle2);

        // Main program loop to display menu and process user choices
        boolean running = true;
        while (running) {
            // Display the menu options
            System.out.println("\n----- Rental System Menu -----");
            System.out.println("1. Add Customer");
            System.out.println("2. View Available Vehicles");
            System.out.println("3. Rent a Vehicle");
            System.out.println("4. Return a Vehicle");
            System.out.println("5. View Last 5 Rentals of a Customer");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> {
                    // Adding a new customer
                    System.out.println("Enter customer details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Create and add customer to the system
                    Customer customer = new Customer(name, address, age);
                    system.addCustomer(customer);
                    System.out.println("Customer added successfully!");
                }
                case 2 -> {
                    // Display available vehicles
                    System.out.println("\nAvailable vehicles:");
                    system.showAvailableVehicles(); // Shows model and year information
                }
                case 3 -> {
                    // Renting a vehicle
                    System.out.println("\nAvailable vehicles:");
                    system.showAvailableVehicles();
                    System.out.print("\nEnter the license plate of the vehicle to rent: ");
                    String licensePlate = scanner.nextLine();

                    // Fetch the chosen vehicle by license plate
                    Vehicle chosenVehicle = system.getVehicleByLicensePlate(licensePlate);

                    // Check if the vehicle is available for rent
                    if (chosenVehicle != null && !chosenVehicle.isRented()) {
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        Customer customer = system.getCustomerByName(customerName);

                        if (customer != null) {
                            // Request rental period from the user
                            System.out.print("Enter rental start date (dd-MM-yyyy): ");
                            String startDateStr = scanner.nextLine();
                            System.out.print("Enter rental end date (dd-MM-yyyy): ");
                            String endDateStr = scanner.nextLine();

                            // Parse the dates
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            Date startDate = sdf.parse(startDateStr);
                            Date endDate = sdf.parse(endDateStr);

                            // Create rental and add to system
                            Rental rental = new Rental(chosenVehicle, customer, startDate, endDate);
                            system.rentVehicle(customer, chosenVehicle, startDate, endDate);

                            System.out.println("Vehicle rented successfully!");
                            System.out.println("Total Rental Price: $" + rental.getTotalRentalPrice());
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } else if (chosenVehicle != null && chosenVehicle.isRented()) {
                        // Vehicle is already rented
                        System.out.println("The chosen vehicle is currently rented.");
                    } else {
                        // Vehicle not found in system
                        System.out.println("Vehicle not found.");
                    }
                }
                case 4 -> {
                    // Returning a rented vehicle
                    System.out.print("Enter the license plate of the vehicle to return: ");
                    String licensePlate = scanner.nextLine();

                    Vehicle chosenVehicle = system.getVehicleByLicensePlate(licensePlate);

                    // Check if vehicle is rented and can be returned
                    if (chosenVehicle != null && chosenVehicle.isRented()) {
                        system.returnVehicle(chosenVehicle);
                        System.out.println("Vehicle returned successfully!");
                    } else {
                        System.out.println("Vehicle is not rented or not found.");
                    }
                }
                case 5 -> {
                    // Viewing last 5 rentals for a specific customer
                    System.out.print("Enter customer name to view last 5 rentals: ");
                    String customerName = scanner.nextLine();
                    Customer customer = system.getCustomerByName(customerName);

                    if (customer != null) {
                        // Fetch and display last 5 rentals
                        List<Rental> last5Rentals = system.getLast5RentalsForCustomer(customer);
                        System.out.println("Last 5 Rentals for " + customer.getName() + ":");
                        system.displayRentalDetails(last5Rentals);
                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 6 -> {
                    // Exiting the program
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        // Close the scanner
        scanner.close();
    }
}
