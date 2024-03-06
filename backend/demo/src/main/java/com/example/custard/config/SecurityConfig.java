package com.example.custard.config;


import com.example.custard.config.filter.JwtFilter;
import com.example.custard.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final TokenProvider tokenProvider;
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/customer/*").permitAll()
                    .antMatchers("/member/*").permitAll()
                    .antMatchers("/test/member").hasRole("MEMBER")
                    .antMatchers("/test/customer").hasRole("MEMBER")
                    .antMatchers("/**").permitAll()
                    .anyRequest().permitAll();

            http.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

            http.formLogin().disable();

            return http.build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

