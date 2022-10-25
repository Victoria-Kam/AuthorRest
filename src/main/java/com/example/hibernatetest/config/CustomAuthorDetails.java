package com.example.hibernatetest.config;

import com.example.hibernatetest.entity.Author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomAuthorDetails implements UserDetails {

    // на 1:09:56
    private String nickName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomAuthorDetails of(Author author){            // создает объект безопасности, который не изменяется
        CustomAuthorDetails authorDetails = new CustomAuthorDetails();
        authorDetails.nickName = author.getNickName();
        authorDetails.password = author.getPassword();
        authorDetails.authorities = Collections.singletonList(new SimpleGrantedAuthority(author.getRole().getName()));
        return authorDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
