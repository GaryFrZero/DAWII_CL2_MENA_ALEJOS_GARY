package com.cibertec.configuracion;

import com.cibertec.service.UsuarioDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final UsuarioDetalleService usuarioDetalleService;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests()
                    .requestMatchers("/auth/login",
                            "/auth/registrar",
                            "/auth/guardarUsuario",
                            "/resources/**",
                            "/static/**",
                            "/styles/**",
                            "/scripts/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin().loginPage("/auth/login")
                    .defaultSuccessUrl("/Alumno/frmAlumno")
                    .usernameParameter("nomusuario")
                    .passwordParameter("password")
                    .and()
                    .authenticationProvider(authenticationProvider());
            http.csrf().disable();
            return http.build();
        }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(usuarioDetalleService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
}
