package com.example.hibernatetest.config.jwt;


import com.example.hibernatetest.config.CustomAuthorDetails;
import com.example.hibernatetest.config.CustomAuthorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JWTFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER="Authorization";
    private final JWTProvider provider;
    private final CustomAuthorDetailsService service;

    @Autowired
    public JWTFilter(JWTProvider provider, CustomAuthorDetailsService service) {
        this.provider = provider;
        this.service = service;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String bearer = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION_HEADER);
        String token ="";
        if(hasText(bearer) && bearer.startsWith("Bearer ")){
            token = bearer.substring(7);
        }

        if(!token.equals("") && provider.validate(token)){
            String nickName = provider.getNickNameFromToken(token);
            CustomAuthorDetails authorDetails = service.loadUserByNickName(nickName);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(authorDetails,null,authorDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
