package com.fresh.restapi.configs;


import com.fresh.restapi.constants.API;
import com.fresh.restapi.repositories.UserRepository;
import com.fresh.restapi.services.auth.JwtAuthenticationService;
import com.fresh.restapi.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
//                .usersByUsernameQuery("select username, password from user_table where username=?")
//                .authoritiesByUsernameQuery("select username, role from user_table where username=?");
    }


    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(new JwtAuthenticationService()));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                // dont authenticate this particular request
                .antMatchers(
                        API.V1 + API.USERS,
                        API.V1 + API.USERS + "/**"
                )
                .permitAll()
                // all other requests need to be authenticated
                .and()
                // make sure we use stateless session; session won't be used to store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().httpBasic().disable()

                // Add a filter to validate the tokens with every request
                .addFilterBefore(
                        new JwtRequestFilter(
                                this.jwtTokenUtil,
                                this.userRepository
                        ),
                        UsernamePasswordAuthenticationFilter.class
                );
    }

}
