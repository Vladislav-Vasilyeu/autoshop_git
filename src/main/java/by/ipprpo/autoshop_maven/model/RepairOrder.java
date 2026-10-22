package by.ipprpo.autoshop_maven.model;

public class RepairOrder {

    private final int id;
    private final Car car;
    private final Customer customer;

    private RepairStatus status;

    public RepairOrder(
            int id,
            Car car,
            Customer customer
    ) {
        this.id = id;
        this.car = car;
        this.customer = customer;

        this.status = RepairStatus.CREATED;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }
}