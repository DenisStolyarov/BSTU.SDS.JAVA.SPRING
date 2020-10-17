package bstu.sds.computer_equipment_rental.service;

import java.util.List;

public interface IService<Instance> {
    Instance create(Instance instance);
    Instance findByName(String name);
    Instance findById(Long id);
    void delete(Long id);
    List<Instance> getAll();
}
