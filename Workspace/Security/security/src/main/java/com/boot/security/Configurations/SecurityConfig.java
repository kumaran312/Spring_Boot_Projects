package com.boot.security.Configurations;


import com.boot.security.Service.CustomUserDetailsService;
import com.boot.security.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private UserServiceImpl userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http.csrf(AbstractHttpConfigurer::disable)

        .authorizeHttpRequests(request ->
            request.requestMatchers("/admin").hasAuthority("ADMIN")
                    .requestMatchers("/user").hasAuthority("USER")
                    .requestMatchers("/register", "/css").permitAll()
                    .anyRequest().authenticated()
        )

        .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
                .successHandler(customSuccessHandler.successHandler()).permitAll())

        .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll());


        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth)throws Exception{

        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }


}
