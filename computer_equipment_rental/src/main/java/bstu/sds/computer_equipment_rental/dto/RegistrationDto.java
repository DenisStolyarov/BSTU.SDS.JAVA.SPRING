package bstu.sds.computer_equipment_rental.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationDto {
    @NotBlank(message = "Username cannot be empty")
    @Length(min = 2, max = 50, message = "Username length must be between 2 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 2, max = 20, message = "Password length must be between 2 and 20 characters")
    private String password;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    private String email;
}
