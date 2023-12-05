package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.view.SignInResponse;
import com.ecommerce.Ecommerce.view.SignUpResponse;
import com.ecommerce.Ecommerce.Dto.SignInDto;
import com.ecommerce.Ecommerce.Dto.SignUpDto;
import com.ecommerce.Ecommerce.customeException.CustomException;
import com.ecommerce.Ecommerce.model.AuthenticationToken;
import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Transactional
    public SignUpResponse signUp(SignUpDto signUpDto) {
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already exists.");
        }
        String encryptedPassword = signUpDto.getPassword();
        try{
             encryptedPassword = hashcode(signUpDto.getPassword());
        }catch(NoSuchAlgorithmException e){
             e.printStackTrace();
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        AuthenticationToken token = new AuthenticationToken(user);
        tokenService.saveToken(token);
        return new SignUpResponse(true,"User created.");
    }

    public SignInResponse signIn(SignInDto signIn) throws NoSuchAlgorithmException {

        //findByEmail
        if(Objects.isNull(userRepository.findByEmail(signIn.getEmail()))){
            throw new CustomException("User does not exists or password does not match");
        }
        User user = userRepository.findByEmail(signIn.getEmail());
        if(!user.getPassword().equals(hashcode(signIn.getPassword()))){
            throw new CustomException("User does not exists or password does not match");
        }
        AuthenticationToken authenticationToken = tokenService.getToken(user);
        return new SignInResponse(true,authenticationToken.getToken());
    }

    private String hashcode(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(("MD5"));
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hashedPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hashedPassword;
    }
}
