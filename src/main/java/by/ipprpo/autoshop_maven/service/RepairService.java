package by.ipprpo.autoshop_maven.service;

import by.ipprpo.autoshop_maven.model.RepairOrder;
import by.ipprpo.autoshop_maven.model.RepairStatus;
import by.ipprpo.autoshop_maven.repository.RepairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RepairService {

    private static final Logger logger =
            LoggerFactory.getLogger(RepairService.class);
    private final RepairRepository repository;

    /*
     * Dependency Injection.
     *
     * Репозиторий передается извне,
     * а не создается внутри сервиса.
     */
    public RepairService(RepairRepository repository) {
        this.repository = repository;
    }

    public void createOrder(RepairOrder order) {

        logger.info("Начало создания заказа на обслуживание автомобиля");

        if (order == null) {
            logger.warn("Попытка создания заказа с пустыми данными (null)");
            throw new IllegalArgumentException("Заказ не может быть null");
        }

        try {
            repository.save(order);
            logger.info("Заказ успешно создан");
        } catch (RuntimeException e) {
            logger.error("Ошибка при создании заказа", e);
            throw e;
        }
    }

    public void startRepair(int orderId) {

        RepairOrder order =
                getOrder(orderId);

        if (order.getStatus() == RepairStatus.COMPLETED
                || order.getStatus() == RepairStatus.CANCELLED) {

            throw new IllegalStateException(
                    "Нельзя начать завершённый ремонт или отмененный ремонт"
            );
        }

        order.setStatus(
                RepairStatus.IN_PROGRESS
        );

        repository.save(order);
    }

    public void completeRepair(int orderId) {

        RepairOrder order =
                getOrder(orderId);

        /*
         * Индивидуальная бизнес-логика
         * предметной области автосервиса:
         *
         * нельзя завершить ремонт,
         * если он еще не выполняется.
         */
        if (order.getStatus() !=
                RepairStatus.IN_PROGRESS) {

            throw new IllegalStateException(
                    "Нельзя завершить ремонт, "
                            + "который ещё не выполняется"
            );
        }

        order.setStatus(
                RepairStatus.COMPLETED
        );

        repository.save(order);
    }

    public void cancelRepair(int orderId){

        RepairOrder order = getOrder(orderId);

        if (order.getStatus() != RepairStatus.CREATED){
            throw  new IllegalStateException("Отменить можно только заказ, по которому ещё не начался ремонт");
        }

        order.setStatus(RepairStatus.CANCELLED);
        repository.save(order);

    }

    public RepairOrder getOrder(int orderId) {

        return repository.findById(orderId)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Заказ с id "
                                        + orderId
                                        + " не найден"
                        )
                );
    }
}