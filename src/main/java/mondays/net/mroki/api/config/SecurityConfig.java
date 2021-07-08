package mondays.net.mroki.api.config;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.jwt.JwtConfig;
import mondays.net.mroki.api.jwt.JwtTokenVerifier;
import mondays.net.mroki.api.jwt.JwtUsernamePasswordAuthenticationFilter;
import mondays.net.mroki.api.service.impl.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomerDetailService userDetailService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;



    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey ))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER" , "ADMIN" ,"IDOL")
                .antMatchers("/admin/user").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

        // adding some fake account
        auth.inMemoryAuthentication().withUser("admin").roles("ADMIN").password(encoder().encode("password"));
        auth.inMemoryAuthentication().withUser("tung").roles("USER").password(encoder().encode("password"));

    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        return provider;
    }


}