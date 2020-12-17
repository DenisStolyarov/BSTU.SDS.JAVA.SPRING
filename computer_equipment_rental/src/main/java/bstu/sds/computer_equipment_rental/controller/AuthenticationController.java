package bstu.sds.computer_equipment_rental.controller;

import bstu.sds.computer_equipment_rental.dto.AuthenticationDto;
import bstu.sds.computer_equipment_rental.dto.RegistrationDto;
import bstu.sds.computer_equipment_rental.excaption.UserValidationException;
import bstu.sds.computer_equipment_rental.model.User;
import bstu.sds.computer_equipment_rental.security.jwt.JwtTokenProvider;
import bstu.sds.computer_equipment_rental.service.implementation.UserService;
import bstu.sds.computer_equipment_rental.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/authentication/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserValidator userValidator;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserService userService,
            UserValidator userValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity Register(@Valid @RequestBody RegistrationDto requestDto, BindingResult errors) {
        try {
            userValidator.validate(requestDto, errors);

            if(errors.hasErrors()){
                throw new UserValidationException(errors);
            }

            User user = new User();
            user.setUsername(requestDto.getUsername());
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            user.setEmail(requestDto.getEmail());
            user.setPassword(requestDto.getPassword());

            var registeredUser = userService.create(user);
            if (registeredUser == null) {
                throw new UsernameNotFoundException("User not created");
            }

            String token = jwtTokenProvider.createToken(registeredUser.getUsername(), registeredUser.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", registeredUser.getUsername());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}