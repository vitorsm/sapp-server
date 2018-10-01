package br.cefetmg.vitor.sappserver.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriter;

import br.cefetmg.vitor.sappserver.facade.SAPPFacade;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	SAPPFacade sf;
	
	@Value("${server.allow.origin}")
	String allowOrigin;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors().and().csrf().disable();
		httpSecurity.headers().frameOptions().sameOrigin();
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/h2/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/serv/init").permitAll()
				.antMatchers(HttpMethod.POST, "/serv/auth/authenticate").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new JWTLoginFilter("/serv/auth/authenticate", sf.authenticateService),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		
//		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new MD5PasswordEncoder();
	}
}
