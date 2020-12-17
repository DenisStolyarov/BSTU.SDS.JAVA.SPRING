package bstu.sds.computer_equipment_rental.controller;

import bstu.sds.computer_equipment_rental.model.Order;
import bstu.sds.computer_equipment_rental.service.implementation.DeviceService;
import bstu.sds.computer_equipment_rental.service.implementation.OrderService;
import bstu.sds.computer_equipment_rental.service.implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/orders/")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Order> getStudentByUsername(@PathVariable(name = "id") Long id){
        Order order = orderService.findById(id);

        if(order == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info("Get request : /api/orders/id");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "getUserOrders")
    public ResponseEntity<List<Order>> getUserDevices(@RequestParam Map<String, String> mapParam){
        var username = mapParam.get("username");
        log.info("Get request : /api/devices/getUserDevices");

        var user = userService.findByName(username);
        var orders = user.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
