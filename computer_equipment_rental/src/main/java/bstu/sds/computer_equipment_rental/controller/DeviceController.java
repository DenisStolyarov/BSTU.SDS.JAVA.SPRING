package bstu.sds.computer_equipment_rental.controller;


import bstu.sds.computer_equipment_rental.model.Device;
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
@RequestMapping(value = "/api/devices/")
public class DeviceController {
    private final UserService userService;
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(UserService userService, DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping(value = "{name}")
    public ResponseEntity<Device> getStudentByUsername(@PathVariable(name = "name") String name){
        Device device = deviceService.findByName(name);

        if(device == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info("Get request : /api/devices/name");
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @GetMapping(value = "getUserDevices")
    public ResponseEntity<List<Device>> getUserDevices(@RequestParam Map<String, String> mapParam){
        var username = mapParam.get("username");
        log.info("Get request : /api/devices/getUserDevices");

        var devices = new ArrayList<Device>();
        var user = userService.findByName(username);
        var orders = user.getOrders();
        for (var order: orders
             ) {
            devices.addAll(order.getDevices());
        }
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}
