package com.vims_v3.controller;

import com.vims_v3.service.vimsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/","/sidebar","/accidentL","/main","/images/**","/loginSlct","/register","/ipReg","/iaReg","/emReg","/garage","/garageForm","/manager","/garageHome","/admin","/adminForm","/adminRegform","/home","/managerForm","/report/**","/getGarage/**","/getEmergencyVehicle/**","/claimAccept/**","/checkClaim/**","/adjusterUpLoc/**","/getDirection/**","/SentToGarage/**","/reportPost","/files/**","/updateAccident/**","/logIn/**","/getLocation/**","/emvAvail/**","/getPay/**","/acceptance/**","/getEmergencyVehicle/**","/setCoverage/**","/IPRegform","/vForm").permitAll()
                .antMatchers("/GarageLogged").hasRole("GARAGE")
                .antMatchers("/index").hasRole("MANAGER")
                .antMatchers("/admin2").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    private vimsUserDetailsService vimsUserDetails;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(vimsUserDetails);
    }
}