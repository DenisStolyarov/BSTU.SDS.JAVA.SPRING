package bstu.sds.computer_equipment_rental.service.implementation;

import bstu.sds.computer_equipment_rental.model.Device;
import bstu.sds.computer_equipment_rental.repository.DeviceRepository;
import bstu.sds.computer_equipment_rental.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceService implements IDeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device create(Device device) {

        var registeredDevice = deviceRepository.save(device);

        log.info("register - Device: {} successfully registered", registeredDevice);

        return registeredDevice;
    }

    @Override
    public Device findById(Long id) {
        var Device = deviceRepository.findById(id).orElse(null);

        if (Device == null) {

            log.info("findById - Device not found by id: {}", id);

            return null;
        } else {
            log.info("findById - Device: {} found by id: {}", Device, id);

            return Device;
        }
    }

    public Device findByName(String name) {
        var device = deviceRepository.findByName(name);

        if (device == null) {

            log.info("findById - Device not found with name: {}", device);

            return null;
        } else {
            log.info("findByName - Device: {} found by name: {}", device, name);

            return device;
        }
    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);

        log.info("delete - Device with id: {} deleted", id);
    }

    @Override
    public List<Device> getAll() {
        var Devices = deviceRepository.findAll();

        log.info("getAll - {} Devices found", Devices.size());

        return Devices;
    }
}