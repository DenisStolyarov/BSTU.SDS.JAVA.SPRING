package bstu.sds.computer_equipment_rental.service;

import bstu.sds.computer_equipment_rental.model.Device;

import java.util.List;

public interface IDeviceService {
    Device create(Device device);
    Device findById(Long id);
    void delete(Long id);
    List<Device> getAll();
}
