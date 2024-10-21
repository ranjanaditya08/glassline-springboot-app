package com.spring.Glassline.service;

import com.spring.Glassline.entity.User;
import com.spring.Glassline.repository.UserRepository;
import com.spring.Glassline.security.CustomUserDetailService;
import com.spring.Glassline.utils.ApiResponse;
import com.spring.Glassline.utils.LoginRequest;
import com.spring.Glassline.utils.LoginResponse;
import com.spring.Glassline.utils.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Getter
@Setter
public class UserService {


    private final JwtTokenService  jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getAuthorities());

        return userDto;
    }

    @Autowired
    public UserService(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ApiResponse<?> createUser(User user) {
        Optional<User> isUserExist = userRepository.findByEmail(user.getEmail());
        if (isUserExist.isPresent()){
            return new ApiResponse<>(null,user.getEmail()+ " Already exists!",false);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        UserDto userDto = userToDto(savedUser);
        return new ApiResponse<>(userDto,"User created successfully", true);
    }

    public ApiResponse<?> validateUser(LoginRequest loginRequest) {

        try {
           Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            User userDetails = (User) authentication.getPrincipal();

            UserDto userDto = userToDto(userDetails);

           if (authentication.isAuthenticated()){
              String token = jwtTokenService.generateToken(loginRequest.getEmail());
              return new ApiResponse<>(new LoginResponse(token, userDto), "Login successful", true );
           }

        } catch (Exception e) {
            log.error("e: ", e);
            return new ApiResponse<>("Invalid email or password", "Login failed", false);
        }
        return new ApiResponse<>("Authentication failed", "Login failed",false);
    }

}
