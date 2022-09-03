package ru.shornikov.restapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers(HttpMethod.GET,"/users").hasRole("READ")
                        .antMatchers(HttpMethod.PUT,"/users").hasRole("WRITE")
                        .antMatchers(HttpMethod.POST,"/users").hasRole("WRITE")
                        .antMatchers(HttpMethod.DELETE,"/users").hasRole("WRITE")
                        .anyRequest().permitAll())
                .csrf().disable()//TODO Как реализовывать его я так и не понял, надо видимо на стороне клиента генерировать*/
                .httpBasic();
        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager detailsManager(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails user1 = User.withDefaultPasswordEncoder().username("user1").password("pass").roles("READ").build();
        UserDetails user2 = User.withDefaultPasswordEncoder().username("user2").password("pass").roles("READ","WRITE").build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }


}
