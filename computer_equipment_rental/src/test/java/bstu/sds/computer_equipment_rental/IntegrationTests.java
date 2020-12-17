package bstu.sds.computer_equipment_rental;

import bstu.sds.computer_equipment_rental.model.Role;
import bstu.sds.computer_equipment_rental.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testSetJwtTokenProviderForAdmin() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        var userRole = new Role();
        userRole.setName("ROLE_USER");
        roles.add(userRole);
        var adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roles.add(adminRole);
        String token = jwtTokenProvider.createToken("Denis", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/1").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }

    @Test
    void testSetJwtTokenProviderForUsers() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        var userRole = new Role();
        userRole.setName("ROLE_USER");
        roles.add(userRole);
        String token = jwtTokenProvider.createToken("user", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("api/users/1").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }
}