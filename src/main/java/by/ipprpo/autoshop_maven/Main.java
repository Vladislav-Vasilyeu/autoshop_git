package by.ipprpo.autoshop_maven;

import by.ipprpo.autoshop_maven.model.Car;
import by.ipprpo.autoshop_maven.model.Customer;
import by.ipprpo.autoshop_maven.model.RepairOrder;
import by.ipprpo.autoshop_maven.repository.InMemoryRepairRepository;
import by.ipprpo.autoshop_maven.service.RepairService;

public class Main {

    public static void main(String[] args) {

        Car car =
                new Car(
                        1,
                        "Toyota",
                        "Camry"
                );

        Customer customer =
                new Customer(
                        1,
                        "Иван Иванов"
                );

        RepairOrder order =
                new RepairOrder(
                        1,
                        car,
                        customer
                );

        /*
         * Dependency Injection:
         * InMemoryRepairRepository
         * передается в RepairService
         * через конструктор.
         */
        RepairService service =
                new RepairService(
                        new InMemoryRepairRepository()
                );

        service.createOrder(order);

        System.out.println(
                "Заказ создан."
        );

        System.out.println(
                "Статус: "
                        + service.getOrder(1).getStatus()
        );

        service.startRepair(1);

        System.out.println(
                "Ремонт начат."
        );

        System.out.println(
                "Статус: "
                        + service.getOrder(1).getStatus()
        );

        service.completeRepair(1);

        System.out.println(
                "Ремонт завершён."
        );

        System.out.println(
                "Статус: "
                        + service.getOrder(1).getStatus()
        );
    }
}