package com.online_movie_ticket_reservation_system.omtrs.service;

import com.online_movie_ticket_reservation_system.omtrs.Pojo.LoginRequest;
import com.online_movie_ticket_reservation_system.omtrs.Pojo.SignupRequest;
import com.online_movie_ticket_reservation_system.omtrs.model.User;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
public interface UserService {

    User registerUser(SignupRequest signupRequest);
    User UserLogin(LoginRequest loginRequest);

}
