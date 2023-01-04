package com.online_movie_ticket_reservation_system.omtrs.serviceImpl;

import com.online_movie_ticket_reservation_system.omtrs.Enum.Roles;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.LoginRequest;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.SignupRequest;
import com.online_movie_ticket_reservation_system.omtrs.config.jwtUtill.AuthEntryPointJwt;
import com.online_movie_ticket_reservation_system.omtrs.config.jwtUtill.JwtUtills;
import com.online_movie_ticket_reservation_system.omtrs.config.userdetailservice.UserDetailsImpl;
import com.online_movie_ticket_reservation_system.omtrs.model.Role;
import com.online_movie_ticket_reservation_system.omtrs.model.User;
import com.online_movie_ticket_reservation_system.omtrs.repository.RoleRepository;
import com.online_movie_ticket_reservation_system.omtrs.repository.UserRepository;
import com.online_movie_ticket_reservation_system.omtrs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtills jwtUtills;
    @Autowired
    PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    /*register user details in database and also check the validation that are given */
    @Override
    public User registerUser(SignupRequest signupRequest) {


        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException(signupRequest.getUsername() + " UserName  is already exists !");
        } else if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException(signupRequest.getUsername() + " Email is already exists !");
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        if (signupRequest.getRole() == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            roles.add(userRole);
        } else {
            signupRequest.getRole().forEach(s -> {
                switch (s) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        user.setPassword(null);
        logger.info("=====user registration end =====");

        return user;

    }


    /**
     * @param loginRequest user login method
     *                     it authenticate the user and generate the token for specific user
     * @return user
     */

    @Override
    public User UserLogin(LoginRequest loginRequest) {
        logger.info("=====user login start =====");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtills.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        User user = new User();
        user.setRoless(roles);
        user.setId(userDetails.getId());
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setType("Bearer");
        user.setJwtToken("Bearer "+jwt);

        logger.info("=====user login end =====");

        return user;
    }

}
