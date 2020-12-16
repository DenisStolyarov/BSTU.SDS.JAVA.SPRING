package bstu.sds.computer_equipment_rental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
public class Device extends BaseEntity {

    @Column(name = "name")
    private String Name;

    @Column(name = "description")
    private String Description;

    @ManyToMany(mappedBy = "devices", fetch = FetchType.LAZY)
    private List<Order> orders;
}
