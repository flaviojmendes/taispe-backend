package com.flaviojmendes.zapperson.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppSecurity  extends WebSecurityConfigurerAdapter {

        private JWTAuthorizationFilter jwtAuthorizationFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class)
                    // this disables session creation on Spring Security
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

}
