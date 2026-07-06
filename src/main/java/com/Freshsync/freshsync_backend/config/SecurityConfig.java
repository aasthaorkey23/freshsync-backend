package com.Freshsync.freshsync_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.Freshsync.freshsync_backend.security.JwtFilter;

import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	            .requestMatchers("/api/users/register", "/api/users/login").permitAll()

	            .requestMatchers("/api/users/**").hasRole("ADMIN")

	            .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
	            .requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")

	            .requestMatchers(HttpMethod.GET, "/api/categories/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
	            .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")

	            .requestMatchers(HttpMethod.GET, "/api/suppliers/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
	            .requestMatchers(HttpMethod.POST, "/api/suppliers/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.PUT, "/api/suppliers/**").hasAnyRole("ADMIN", "MANAGER")
	            .requestMatchers(HttpMethod.DELETE, "/api/suppliers/**").hasRole("ADMIN")

	            .requestMatchers("/api/dashboard").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}