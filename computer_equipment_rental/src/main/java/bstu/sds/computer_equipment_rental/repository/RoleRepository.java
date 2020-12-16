package bstu.sds.computer_equipment_rental.repository;

import bstu.sds.computer_equipment_rental.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
