package bstu.sds.computer_equipment_rental.service.implementation;

import bstu.sds.computer_equipment_rental.model.Role;
import bstu.sds.computer_equipment_rental.model.Status;
import bstu.sds.computer_equipment_rental.model.User;
import bstu.sds.computer_equipment_rental.repository.RoleRepository;
import bstu.sds.computer_equipment_rental.repository.UserRepository;
import bstu.sds.computer_equipment_rental.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    public UserService(
            RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User create(User user) {
        var role = roleRepository.findByName("ROLE_USER");
        var userRoles = new ArrayList<Role>();
        userRoles.add(role);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());

        /*String message = String.format(
                "Hello %s!\n" +
                        "activate your code , need to visit http://localhost:5050/login/%s",
                user.getFirstName(),
                user.getUsername()
        );
        mailSender.send(user.getEmail(), "Activation code", message);*/

        var registeredUser = userRepository.save(user);

        log.info("register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public User findByName(String name) {
        var user = userRepository.findByUsername(name);

        log.info("findByUsername - user: {} found by username {}", user, name);

        return user;
    }

    @Override
    public User findById(Long id) {
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {

            log.info("findById - user not found by id: {}", id);

            return null;
        } else {
            log.info("findById - user: {} found by id: {}", user, id);

            return user;
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

        log.info("delete - user with id: {} deleted", id);
    }

    @Override
    public List<User> getAll() {
        var users = userRepository.findAll();

        log.info("getAll - {} users found", users.size());

        return users;
    }
}
