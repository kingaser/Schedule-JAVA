package com.sparta.schedule.config;

import com.sparta.schedule.jwt.JwtAuthFilter;
import com.sparta.schedule.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();

            cors.setAllowedOriginPatterns(List.of("*"));      //  주소값 (*로 하면 전부다 허용이 됍니다.)
            cors.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS","PATCH"));
            cors.setAllowedHeaders(List.of("*"));
            cors.addExposedHeader("Authorization");                 //  인증값 토큰
//            cors.addExposedHeader("Refresh_Token");                 //  Refresh 구현하면 쓰고 아니면 필요없음.
            cors.setAllowCredentials(true);
            return cors;
        });

        http.csrf().disable();

        // 기본 설정인 Session 방식 사용하지 않고 JWT 방식 사용
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/user/**").permitAll()
                .antMatchers("/schedule/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.formLogin().permitAll();

//        http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

        return http.build();
    }
}
