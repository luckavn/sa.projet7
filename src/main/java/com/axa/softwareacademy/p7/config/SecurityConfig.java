package com.axa.softwareacademy.p7.config;

import com.axa.softwareacademy.p7.services.MyAppUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This configuration file is aimed to configure secure login to admin and users and specify passsword validation method
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LogManager.getLogger(SecurityConfig.class);

    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;

    /**
     * This method allows endpoints access to users and related actions on behalf
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**").hasAnyAuthority("ADMIN", "USER").antMatchers("/user/**").permitAll().and().formLogin()  //login configuration
                .defaultSuccessUrl("/bidList/list").and().logout()    //logout configuration
                .logoutUrl("/app-logout").logoutSuccessUrl("/").and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("/app/error");
    }

    /**
     * This method encode the password that is informed by a new user
     * @param auth is user or admin info that are informed at sign in form
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder
                passwordEncoder =
                new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * This method check the password validation constraint
     * @param password is the password informed at user's sign in
     * @return true if the password is correct and false if incorrect
     */
    public boolean isPasswordValid(String password) {
        //Les chiffres sont considérés comme des majuscules
        String regEx = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^_&+=])\\S{8,}\\z";
        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) return true;
        else return false;
    }
}
