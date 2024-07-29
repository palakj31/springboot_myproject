package com.itvedant.myproject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
@Configuration
public class SecurityConfigration 
{
      @Bean
    public SecurityFilterChain configure(HttpSecurity http)
    throws Exception{
    http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()  
        .requestMatchers(HttpMethod.POST,"/products/**").permitAll()
        .requestMatchers("/categories/**").hasRole("assistant")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
      
         return http.build();
    }

     @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService users()
    {
    UserDetails user1=User.builder()
                           .username("abc")
                           .password(passwordEncoder().encode("abc@123"))
                           .roles("assistant")
                           .build();
    
    UserDetails user2=User.builder()
                           .username("pqr")
                           .password(passwordEncoder().encode("pqr@123"))
                           .roles("assistant")
                           .build();   
                           
    UserDetails user3=User.builder()
                           .username("xyz")
                           .password(passwordEncoder().encode("xyz@123"))
                           .roles("accountant")
                           .build();
                           
        return new InMemoryUserDetailsManager(user1,user2,user3);              
    }
}
