package by.ipprpo.autoshop_maven.repository;

import by .ipprpo.autoshop_maven.model.RepairOrder;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public class InMemoryRepairRepository
        implements RepairRepository {

    private final List<RepairOrder> orders =
            new ArrayList<>();

    @Override
    public void save(RepairOrder order) {

        orders.removeIf(
                existing ->
                        existing.getId() == order.getId()
        );

        orders.add(order);
    }

    @Override
    public Optional<RepairOrder> findById(int id) {

        return orders.stream()
                .filter(
                        order ->
                                order.getId() == id
                )
                .findFirst();
    }

    @Override
    public List<RepairOrder> findAll() {

        return List.copyOf(orders);
    }
}
