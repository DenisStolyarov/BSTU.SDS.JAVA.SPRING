package bstu.sds.computer_equipment_rental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    @Column(name = "date_from")
    private Date From;

    @Column(name = "date_to")
    private Date To;

    @Column(name = "price")
    private float Price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_devices",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id")})
    private List<Device> devices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="id")
    private User user;
}
