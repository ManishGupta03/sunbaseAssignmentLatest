
package com.example.Sunbase_project.controller;

import com.example.Sunbase_project.dto.request.LoginRequest;
import com.example.Sunbase_project.dto.request.OtpRequest;
import com.example.Sunbase_project.dto.response.BaseResponseDto;
import com.example.Sunbase_project.security.JwtHelper;
import com.example.Sunbase_project.services.AuthService;
import com.example.Sunbase_project.services.UserService;
//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

//    @Autowired
//    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public BaseResponseDto login(@RequestBody LoginRequest request ) {
       BaseResponseDto responseDto ;
        responseDto = authService.login(request);
        try {
            responseDto = authService.login(request);
            log.debug("Response Sent For /login :{}", responseDto);
        }catch (Exception e) {
            responseDto = new BaseResponseDto(e);
            log.error("Exception: ", e);
        }
        return responseDto;



//        this.doAuthenticate(request.getEmail(), request.getPassword());
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.generateToken(userDetails);
//
////
//          responseDto.setToken(token);
////        BaseResponseDto responseDto = BaseResponseDto.builder()
////                .jwtToken(token)
////                .username(userDetails.getUsername())
////                .build();
//
//        return responseDto;




//        BaseResponseDto responseDto = new BaseResponseDto();
//
//        try {
//            // Call your authentication service to authenticate the user
//            UserDetails userDetails = authService.login(request);
//
//            // Generate JWT token
//            String token = helper.generateToken(responseDto);
//
//            // Include the token in the response
//            responseDto.setToken(token);
//            responseDto.setUsername(userDetails.getUsername());
//
//            log.debug("Response Sent For /login :{}", responseDto);
//        } catch (Exception e) {
//            responseDto.setError(e.getMessage());
//            log.error("Exception: ", e);
//        }
//        return responseDto;
    }

//    private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            manager.authenticate(authentication);
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }
    @PostMapping("/verify-otp")
    public BaseResponseDto verifyOtp(@RequestBody OtpRequest otp) {
        BaseResponseDto responseDto;
        try {
            responseDto = authService.verifyOtp(otp);
            log.debug("Response Sent For /login :{}", responseDto);
        }catch (Exception e) {
            responseDto = new BaseResponseDto(e);
            log.error("Exception: ", e);
        }
        return responseDto;
    }
}
