import java.util.Date;

public class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private Date startDate;
    private Date endDate;
    private double totalRentalPrice;

    public Rental(Vehicle vehicle, Customer customer, Date startDate, Date endDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalRentalPrice = calculateTotalRentalPrice();
        vehicle.setRented(true);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private double calculateTotalRentalPrice() {
        long rentalDays = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        return rentalDays * vehicle.getPricePerDay();
    }

    public double getTotalRentalPrice() {
        return totalRentalPrice;
    }

    public void endRental() {
        vehicle.setRented(false);
    }
}
