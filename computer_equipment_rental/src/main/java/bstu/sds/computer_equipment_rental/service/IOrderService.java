package bstu.sds.computer_equipment_rental.service;

import bstu.sds.computer_equipment_rental.model.Order;

import java.util.List;

public interface IOrderService {
    Order create(Order Order);
    Order findById(Long id);
    void delete(Long id);
    List<Order> getAll();
}
