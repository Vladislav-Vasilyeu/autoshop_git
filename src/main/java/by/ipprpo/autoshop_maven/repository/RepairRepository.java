package by.ipprpo.autoshop_maven.repository;

import by.ipprpo.autoshop_maven.model.RepairOrder;
import java.util.List;
import java.util.Optional;


public interface RepairRepository {

    void save(RepairOrder order);

    Optional<RepairOrder> findById(int id);

    List<RepairOrder> findAll();
}
