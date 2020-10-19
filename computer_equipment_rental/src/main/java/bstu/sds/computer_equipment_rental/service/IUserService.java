package bstu.sds.computer_equipment_rental.service;

import bstu.sds.computer_equipment_rental.model.User;

import java.util.List;

public interface IUserService {
    User create(User user);
    User findByName(String name);
    User findById(Long id);
    void delete(Long id);
    List<User> getAll();
}
