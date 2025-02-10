package com.infosys.purchase.rewardpoints.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/**
	 * Method to configure security filter chain
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .csrf((csrf) -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
        return http.build();
    }

}