package bstu.sds.computer_equipment_rental.repository;

import bstu.sds.computer_equipment_rental.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByName(String name);
}
