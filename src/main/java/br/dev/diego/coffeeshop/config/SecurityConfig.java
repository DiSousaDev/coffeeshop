package br.dev.diego.coffeeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultFilter(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers(HttpMethod.GET, "/api/products").permitAll();
                            auth.requestMatchers(HttpMethod.GET, "/api/users").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll();
                            auth.anyRequest().authenticated();
                        }).build();
    }

}
