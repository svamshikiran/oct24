package com.example.oct2024.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/student/**").permitAll()
                            .requestMatchers("/courses/**").hasAuthority("ROLE_ADMIN")
                            .requestMatchers("/calculate/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                            .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // 1. Provide the Authentication mechanism (User Validation)
    // 2. Provide the Authorization mechanism (ROLE Based access)

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        return manager;


        /*
                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder()
                .username("testuser")
                .password(encoder.encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.builder()
                .username("testadmin")
                .password(encoder.encode("password"))
                .roles("USER", "ADMIN")
                .build());
        manager.createUser(User.builder()
                .username("vamshi")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build());
        return manager;
         */
    }
}

/**
 *  TDD - Testing Driven Design/Development - Developers - Unit Testing - Whitebox Testing
 *  BDD - Business Driven Design/Development - Tests/QA - Functional Testing - Blackbox Testing
 *  For every condition that we have in code, we need to have a testcase/testscenario
 *  Every testcase - we have expected result and the actual result
 */
