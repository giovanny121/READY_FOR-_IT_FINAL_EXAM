import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalManagement {
    private List<Vehicle> vehicles;
    private List<Rental> rentals;
    private List<Customer> customers;  // To keep track of registered customers

    public RentalManagement() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
        customers = new ArrayList<>();
    }

    // Method to add a new vehicle to the system
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Method to add a new customer to the system
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to retrieve a customer by name
    public Customer getCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null; // Return null if no customer found with the given name
    }

    // Method to retrieve a vehicle by its license plate
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return vehicle;
            }
        }
        return null; // Return null if no vehicle found with the given license plate
    }

    // Method to rent a vehicle if it's available
    public void rentVehicle(Customer customer, Vehicle vehicle, Date startDate, Date endDate) {
        if (!vehicle.isRented()) {
            Rental rental = new Rental(vehicle, customer, startDate, endDate);
            rentals.add(rental);
            vehicle.setRented(true); // Mark vehicle as rented
            System.out.println("Rental created successfully for " + customer.getName());
        } else {
            System.out.println("Vehicle is currently rented.");
        }
    }

    // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle) {
        for (Rental rental : rentals) {
            if (rental.getVehicle().equals(vehicle) && vehicle.isRented()) {
                rental.endRental();
                vehicle.setRented(false); // Mark vehicle as available
                System.out.println("Vehicle returned successfully.");
                return;
            }
        }
        System.out.println("Vehicle was not rented or does not exist.");
    }

    // Method to display all available vehicles
    public void showAvailableVehicles() {
        System.out.println("Available Vehicles:");
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isRented()) {
                System.out.println(vehicle.getClass().getSimpleName() + " - Model: " + vehicle.getModel()
                        + " - License Plate: " + vehicle.getLicensePlate());
            }
        }
    }

    // Method to calculate total rental price
    public double calculateTotalRentalPrice(Rental rental) {
        return rental.getTotalRentalPrice();
    }

    // Method to get the last 5 rentals for a given vehicle
    public List<Rental> getLast5RentalsForVehicle(Vehicle vehicle) {
        List<Rental> vehicleRentals = new ArrayList<>();
        for (int i = rentals.size() - 1; i >= 0; i--) {
            if (rentals.get(i).getVehicle().equals(vehicle)) {
                vehicleRentals.add(rentals.get(i));
                if (vehicleRentals.size() == 5) {
                    break;
                }
            }
        }
        return vehicleRentals;
    }

    // Method to get the last 5 rentals for a given customer
    public List<Rental> getLast5RentalsForCustomer(Customer customer) {
        List<Rental> customerRentals = new ArrayList<>();
        for (int i = rentals.size() - 1; i >= 0; i--) {
            if (rentals.get(i).getCustomer().equals(customer)) {
                customerRentals.add(rentals.get(i));
                if (customerRentals.size() == 5) {
                    break;
                }
            }
        }
        return customerRentals;
    }

    // Method to display rental details
    public void displayRentalDetails(List<Rental> rentals) {
        for (Rental rental : rentals) {
            System.out.println("Vehicle: " + rental.getVehicle().getModel() +
                    ", License Plate: " + rental.getVehicle().getLicensePlate() +
                    ", Customer: " + rental.getCustomer().getName() +
                    ", Start Date: " + rental.getStartDate() +
                    ", End Date: " + rental.getEndDate() +
                    ", Total Price: $" + rental.getTotalRentalPrice());
        }
    }
}
