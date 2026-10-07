package com.cybersoft.cozastore03.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Autowired
    // private CustomAuthenProvider customAuthenProvider;

    // Custom lại AuthenticationManager để sử dụng customAuthenProvider mà mình tạo
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
            CustomAuthenProvider customAuthenProvider) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }

    // Thay đổi thông tin về rule đường dẫn của Security
    // Config spring security filter chain đoạn này
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .build();
    }

    // tạo 2 account admin và user ảo để test trên trình duyệt
    // @Bean
    // public UserDetailsService userDetailService(PasswordEncoder passwordEncoder)
    // {

    // UserDetails admin = User.withUsername("admin")
    // .password(passwordEncoder.encode("123456"))
    // .roles("ADMIN")
    // .build();
    // UserDetails user = User.withUsername("user")
    // .password(passwordEncoder.encode("123456"))
    // .roles("USER")
    // .build();

    // return new InMemoryUserDetailsManager(admin, user);

    // }

}
