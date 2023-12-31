package com.RentACar.RentACar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .antMatchers("/admin/**", "/dashboard", "/manage-roles", "/manage-user", "/add-user", "/manage-car", "/add-car").hasRole("ADMIN")
                .antMatchers("/customer/**", "/profile", "/profile-modify", "/payment", "/comments").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/login-success")
                .failureUrl("/login?error")
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("BurgerFactory-LoggedIn-User")
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(68))
                .key("ccc")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

        return httpSecurity.build();
    }
}
