package bstu.sds.computer_equipment_rental.repository;

import bstu.sds.computer_equipment_rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
