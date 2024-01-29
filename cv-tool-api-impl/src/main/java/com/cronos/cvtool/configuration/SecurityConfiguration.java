package com.cronos.cvtool.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    		.csrf(CsrfConfigurer::disable)
    		.authorizeHttpRequests((authorize) -> authorize
		    	.requestMatchers(
		    		// Allow access to Actuator
	    			new AntPathRequestMatcher("/actuator/**"),
		    		// Allow access to Swagger
	    			new AntPathRequestMatcher("/v3/api-docs/**"),
	    			new AntPathRequestMatcher("/swagger-ui/**"),
	    			new AntPathRequestMatcher("/swagger-ui.html")
				).permitAll()
		    	// Authenticate all other requests
	    		.anyRequest().authenticated()
    		)
    		// Use basic authentication (user/pass)
			.httpBasic(Customizer.withDefaults());

    	return http.build();
    }

}
