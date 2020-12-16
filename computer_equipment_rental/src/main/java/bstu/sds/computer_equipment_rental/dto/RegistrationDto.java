package bstu.sds.computer_equipment_rental.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
