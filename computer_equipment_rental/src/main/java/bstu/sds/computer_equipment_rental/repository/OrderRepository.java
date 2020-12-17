package bstu.sds.computer_equipment_rental.repository;

import bstu.sds.computer_equipment_rental.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}