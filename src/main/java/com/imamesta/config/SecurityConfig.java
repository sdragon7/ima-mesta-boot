package com.imamesta.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
private static final String USERS_BY_USERNAME_QUERY = "select username, password, enabled from user where username = ?";
	
	private static final String AUTHORITIES_BY_USERNAME_QUERY = "select u.username, r.authority "
			+ "from user u, user_role r, user_authority ua "
			+ "where ua.user_id = u.id "
			+ "and ua.user_role_id = r.id "
			+ "and u.username = ?";
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
		.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests().antMatchers("/rest/**").authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		/*http.cors().and()
		.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();	*/
	}	
	
	@Configuration
	@EnableWebMvc
	class CorsConfiguration implements WebMvcConfigurer
	{
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowCredentials(true).allowedOrigins("*")
	                .allowedMethods("GET", "POST");
	    }
	}	 
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
