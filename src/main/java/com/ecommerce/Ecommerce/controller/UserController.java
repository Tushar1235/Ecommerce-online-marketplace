package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Dto.SignInDto;
import com.ecommerce.Ecommerce.view.SignInResponse;
import com.ecommerce.Ecommerce.view.SignUpResponse;
import com.ecommerce.Ecommerce.Dto.SignUpDto;
import com.ecommerce.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    //signUp
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpDto signUpDto){
        SignUpResponse signUpResponse = userService.signUp(signUpDto);
        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
    }


    //sighIn
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInDto signInDto) throws NoSuchAlgorithmException {
        SignInResponse signInResponse = userService.signIn(signInDto);
        return new ResponseEntity<>(signInResponse, HttpStatus.CREATED);
    }

}
