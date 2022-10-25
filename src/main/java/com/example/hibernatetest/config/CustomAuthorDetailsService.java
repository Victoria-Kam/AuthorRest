package com.example.hibernatetest.config;


import com.example.hibernatetest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthorDetailsService {

    private final AuthorService service;

    @Autowired
    public CustomAuthorDetailsService(AuthorService service) {
        this.service = service;
    }

    public CustomAuthorDetails loadUserByNickName(String nickName) throws UsernameNotFoundException{
        return CustomAuthorDetails.of(service.findByNickName(nickName));
    }
}
