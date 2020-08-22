package com.foodgram.config;

import com.foodgram.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserTypesOAuth2UserService oAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll();
        http.authorizeRequests()
                .mvcMatchers("/", "login", "sign-up", "/css/**", "/images/**", "/js/**").permitAll()
                .mvcMatchers("api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                    .and()
                        .logout()
                            .logoutSuccessUrl("/")
                    .and()
                        .oauth2Login()
                            .userInfoEndpoint()
                                .userService(oAuth2UserService);
    }
}
