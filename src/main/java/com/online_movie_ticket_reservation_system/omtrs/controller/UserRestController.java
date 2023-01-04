package com.online_movie_ticket_reservation_system.omtrs.controller;

import com.online_movie_ticket_reservation_system.omtrs.Pojo.LoginRequest;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.SignupRequest;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.response.GlobalResponseApi;
import com.online_movie_ticket_reservation_system.omtrs.model.User;
import com.online_movie_ticket_reservation_system.omtrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class UserRestController extends GlobalResponseApi {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<GlobalResponseApi> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            User user = userService.registerUser(signupRequest);
            return ResponseEntity.ok(successResponse("User register successFully !", user));
        } catch (Exception e) {
            return ResponseEntity.ok(errorResponse("Errror : ", e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<GlobalResponseApi> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.UserLogin(loginRequest);
            return ResponseEntity.ok(successResponse("Use login successFully !", user));
        } catch (Exception e) {
            return ResponseEntity.ok(errorResponse("Errror : ", e.getMessage()));
        }
    }

}
