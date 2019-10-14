package ru.brazhnikov.todolist.config;

import org.springframework.context.annotation.Bean;
import ru.brazhnikov.todolist.service.UserAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * SecurityJavaConfig - конфигурационный класс настройки безопасности
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.config
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *  @access private
     *  @var BCryptPasswordEncoder passwordEncoder - кодировщик пароля
     */
    private PasswordEncoder passwordEncoder;

    /**
     *  @access private
     *  @var UserAuthService userAuthService - кодировщик пароля
     */
    private UserAuthService userAuthService;

    @Autowired
    public void setPasswordEncoder( PasswordEncoder passwordEncoder ) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserAuthService( UserAuthService userAuthService ) {
        this.userAuthService = userAuthService;
    }

    /**
     * configure
     * @param auth - класс указывающий на то каким
     * образом должен осуществляться механизм авторизации пользователя
     */
    @Override
    protected void configure( AuthenticationManagerBuilder auth ) {
        auth.authenticationProvider( authenticationProvider() );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
            .antMatchers( "/" ).authenticated()
            .and()
            .formLogin()
            .loginPage( "/login" )
            .loginProcessingUrl( "/authenticateTheUser" )
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService( this.userAuthService );
        auth.setPasswordEncoder( this.passwordEncoder );
        return auth;
    }
}
