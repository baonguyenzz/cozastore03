package com.cybersoft.cozastore03.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybersoft.cozastore03.entity.UserEntity;
import com.cybersoft.cozastore03.repository.UserRepository;
import com.cybersoft.cozastore03.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(String username, String password) {
        UserEntity user = userRepository.findByEmail(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
