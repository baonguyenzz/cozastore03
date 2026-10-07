package com.cybersoft.cozastore03.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybersoft.cozastore03.repository.UserRepository;
import com.cybersoft.cozastore03.service.UserService;
import com.cybersoft.cozastore03.service.imp.LoginServiceImpl;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Nếu trả ra authentication thì đăng nhập thành công, nếu trả ra null thì đăng
    // nhập thất bại
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        boolean isAuthenticated = loginServiceImpl.checkLogin(username, password);
        if (isAuthenticated)
            return authentication;
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
