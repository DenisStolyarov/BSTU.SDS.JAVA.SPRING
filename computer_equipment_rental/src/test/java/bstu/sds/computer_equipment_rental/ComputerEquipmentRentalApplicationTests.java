package bstu.sds.computer_equipment_rental;

import bstu.sds.computer_equipment_rental.model.Device;
import bstu.sds.computer_equipment_rental.model.Order;
import bstu.sds.computer_equipment_rental.repository.DeviceRepository;
import bstu.sds.computer_equipment_rental.repository.OrderRepository;
import bstu.sds.computer_equipment_rental.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ComputerEquipmentRentalApplicationTests {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private DeviceRepository deviceRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddDevice() {
        var device = new Device();
        device.setName("Computer");

        List<Device> devices = new ArrayList<>();
        devices.add(device);

        when(deviceRepository.findAll()).thenReturn(devices);

        Assert.assertEquals(deviceRepository.findAll(), devices);
    }

    @Test
    public void testGetSubjects() throws Exception {
        setUp();
        var device = new Device();
        device.setName("Computer");

        List<Device> devices = new ArrayList<>();
        devices.add(device);

        when(deviceRepository.findAll()).thenReturn(devices);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/devices?name=Computer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[*].devices", Matchers.containsInAnyOrder("Computer")));
    }

    @Test
    public void testAddFaculty() {
        var order = new Order();
        order.setId(1);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        Assert.assertEquals(orderRepository.findAll(), orders);
    }

    @Test
    public void testGetFaculties() throws Exception {
        setUp();
        var order = new Order();
        order.setId(1);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[*].id", Matchers.containsInAnyOrder("1")));
    }
}
