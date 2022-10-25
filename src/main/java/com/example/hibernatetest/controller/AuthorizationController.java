package com.example.hibernatetest.controller;


import com.example.hibernatetest.config.jwt.JWTProvider;
import com.example.hibernatetest.dto.AuthorizationRequest;
import com.example.hibernatetest.dto.AuthorizationResponse;
import com.example.hibernatetest.dto.IntrospectRequest;
import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorizationController {

    private final AuthorService service;
    private final JWTProvider provider;

    @Autowired
    public AuthorizationController(AuthorService service, JWTProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthorizationRequest request){
        Author author = new Author();
        author.setNickName(request.getNickName());
        author.setPassword(request.getPassword());
        service.register(author);
        return "OK";
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthorizationResponse> authorizationResponse(@RequestBody AuthorizationRequest request){
        Optional<Author> authorOptional = service.findByNickNameAndPassword(request.getNickName(),request.getPassword());
        if(authorOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return getAuth(request.getNickName());
    }


    @PostMapping("/introspect")
    public ResponseEntity<AuthorizationResponse> introspect(@RequestBody IntrospectRequest request){

        boolean isValid = provider.validate(request.getRefreshToken());
        if(isValid){
            String nickName = provider.getNickNameFromToken(request.getRefreshToken());
            getAuth(nickName);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<AuthorizationResponse> getAuth(String nickName){
        String accessToken = provider.generateAccessToken(nickName);
        String refreshToken = provider.generateRefreshToken(nickName);
        return new ResponseEntity<>(new AuthorizationResponse(accessToken,refreshToken),HttpStatus.OK);
    }

}
