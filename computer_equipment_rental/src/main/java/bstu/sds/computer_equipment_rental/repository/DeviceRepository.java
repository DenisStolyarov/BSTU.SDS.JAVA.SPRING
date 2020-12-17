package bstu.sds.computer_equipment_rental.repository;

import bstu.sds.computer_equipment_rental.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByName(String name);
}
