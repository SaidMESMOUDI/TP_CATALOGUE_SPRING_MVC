package fr.saidus.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}adminpass").
		 * roles("USER", "ADMIN");
		 * auth.inMemoryAuthentication().withUser("user").password("{noop}userpass").
		 * roles("USER");
		 */
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select login as principal, pass as credentials, true from users where login = ?")
			.authoritiesByUsernameQuery("select login as principal, role as role from users_roles where login = ?")
			.passwordEncoder(getBCryptPE())
			.rolePrefix("ROLE_");
		
//		String password = "userpassword";
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//		System.err.println("L'encodage du password ("+password+") avec BCrypt est: " + hashedPassword);
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		//http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
		
//		http.logout();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID") ;
	
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPE() {
		return new BCryptPasswordEncoder();
	}

}
