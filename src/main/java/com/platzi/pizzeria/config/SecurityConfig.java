package com.platzi.pizzeria.config;

import java.util.Arrays;

import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;




@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig 
{   
   
    private final CustomJWTFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {   
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((custimizeRequest) ->{
                                    custimizeRequest
                                                .requestMatchers("/users/login").permitAll()
                                                .requestMatchers(HttpMethod.GET,"/pizza/**").hasAnyRole("ADMIN","CUSTOMER")
                                                .requestMatchers(HttpMethod.POST, "/pizza/**").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                                                .requestMatchers("/order/random/order").hasAuthority("random_order")
                                                .requestMatchers("/order/**").hasRole("ADMIN")
                                                .anyRequest()
                                                .authenticated();
                                        
            })
              .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .cors(Customizer.withDefaults())
              .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
              
        return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource()
    {   
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
        
    }
    
    /* 
    @Bean
    public UserDetailsService customUserInMemory()
    {
        UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder().encode("admin")) 
                                .roles("ADMIN")
                                .build();

        UserDetails client = User.builder()
                                          .username("client")
                                          .password(passwordEncoder().encode("client")) 
                                          .roles("CLIENT")     
                                          .build();

        return new InMemoryUserDetailsManager(admin); 
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



}
