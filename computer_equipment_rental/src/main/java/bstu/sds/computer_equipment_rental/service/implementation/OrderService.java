package bstu.sds.computer_equipment_rental.service.implementation;

import bstu.sds.computer_equipment_rental.model.Order;
import bstu.sds.computer_equipment_rental.repository.OrderRepository;
import bstu.sds.computer_equipment_rental.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {

        var registeredOrder = orderRepository.save(order);

        log.info("register - Order: {} successfully registered", registeredOrder);

        return registeredOrder;
    }

    @Override
    public Order findById(Long id) {
        var Order = orderRepository.findById(id).orElse(null);

        if (Order == null) {

            log.info("findById - Order not found by id: {}", id);

            return null;
        } else {
            log.info("findById - Order: {} found by id: {}", Order, id);

            return Order;
        }
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);

        log.info("delete - Order with id: {} deleted", id);
    }

    @Override
    public List<Order> getAll() {
        var Orders = orderRepository.findAll();

        log.info("getAll - {} Orders found", Orders.size());

        return Orders;
    }
}
