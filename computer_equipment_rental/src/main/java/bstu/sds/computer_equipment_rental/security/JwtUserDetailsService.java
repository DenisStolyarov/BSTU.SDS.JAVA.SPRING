package bstu.sds.computer_equipment_rental.security;

import bstu.sds.computer_equipment_rental.model.User;
import bstu.sds.computer_equipment_rental.security.jwt.JwtUser;
import bstu.sds.computer_equipment_rental.security.jwt.JwtUserFactory;
import bstu.sds.computer_equipment_rental.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final IUserService userService;

    @Autowired
    public JwtUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userService.findByName(username);

        if (user == null)
            throw new UsernameNotFoundException(String.format("User with username %s not found", username));

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("loadUserByUsername - user with username: {} loaded", username);

        return jwtUser;
    }
}
