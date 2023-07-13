package com.example.imsmanagemnt.user;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> getAll();

}
