package by.ipprpo.autoshop_maven.service;

import by.ipprpo.autoshop_maven.model.Car;
import by.ipprpo.autoshop_maven.model.Customer;
import by.ipprpo.autoshop_maven.model.RepairOrder;
import by.ipprpo.autoshop_maven.model.RepairStatus;
import by.ipprpo.autoshop_maven.repository.InMemoryRepairRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepairServiceTest {

    @Test
    void shouldCreateAndStartRepair() {

        RepairService service =
                createService();

        RepairOrder order =
                createOrder(1);

        service.createOrder(order);

        service.startRepair(1);

        assertEquals(
                RepairStatus.IN_PROGRESS,
                service.getOrder(1).getStatus()
        );
    }

    @Test
    void shouldCompleteRepairAfterItWasStarted() {

        RepairService service =
                createService();

        RepairOrder order =
                createOrder(1);

        service.createOrder(order);

        service.startRepair(1);

        service.completeRepair(1);

        assertEquals(
                RepairStatus.COMPLETED,
                service.getOrder(1).getStatus()
        );
    }

    @Test
    void shouldNotCompleteRepairBeforeItWasStarted() {

        RepairService service =
                createService();

        RepairOrder order =
                createOrder(1);

        service.createOrder(order);

        assertThrows(
                IllegalStateException.class,
                () -> service.completeRepair(1)
        );
    }

    private RepairService createService() {

        return new RepairService(
                new InMemoryRepairRepository()
        );
    }

    private RepairOrder createOrder(int id) {

        return new RepairOrder(
                id,

                new Car(
                        1,
                        "Toyota",
                        "Camry"
                ),

                new Customer(
                        1,
                        "Иван Иванов"
                )
        );
    }
}