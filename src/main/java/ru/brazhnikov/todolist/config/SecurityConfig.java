package ru.brazhnikov.todolist.config;

import javax.sql.DataSource;
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

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
     *  @access private
     *  @var DataSource myDataSource -
     */
    private DataSource myDataSource;

    /**
     * setMyDataSource -
     * @param myDataSource -
     * @return void
     */
    @Autowired
    public void setMyDataSource( DataSource myDataSource ) {
        this.myDataSource = myDataSource;
    }

    /**
     * configure -
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication().dataSource( this.myDataSource );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
//            .exceptionHandling()
//                .accessDeniedHandler( new MissingCsrfTokenAccessDeniedHandler() )
//            .and()
                .authorizeRequests()
            .and()
                .formLogin()
                .loginPage( "/login" )
                .loginProcessingUrl( "/authenticateTheUser" )
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
            .and()
                .csrf()
                .disable().anonymous()
            .and()
                .rememberMe()
            .and().exceptionHandling().accessDeniedPage("/errors/error403");
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource( this.myDataSource );
//        return db;
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService( this.userAuthService );
        auth.setPasswordEncoder( this.passwordEncoder );
        return auth;
    }
}
