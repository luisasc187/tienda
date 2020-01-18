package pe.edu.tecsup.tienda.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import pe.edu.tecsup.tienda.services.UsuarioService;
import pe.edu.tecsup.tienda.services.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	/*@Bean
	public Producto createProducto() {
		Producto producto = new Producto();
		producto.setNombre("iphone");
		return producto;
	}*/
	
	@Bean
	public PasswordEncoder createPasswordEnconder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encode(rawPassword).equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}
	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsServiceBean() throws Exception {
//		
//		List<UserDetails> users = new ArrayList<UserDetails>();
//		users.add(User.withUsername("admin").password("admin").roles("ADMIN").build());
//		users.add(User.withUsername("user").password("tecsup").roles("USER").build());
//		
//		return new InMemoryUserDetailsManager(users);
//	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			// Control de accesos
			authorizeRequests()
			.antMatchers("/public").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/productos/**").hasAnyAuthority("Administrador")
			.antMatchers("/usuarios/**").hasAnyAuthority("Administrador")
			.antMatchers("/categorias/**").hasAnyAuthority("Administrador")
			.antMatchers("/admin/**").hasAnyAuthority("Administrador")
			.anyRequest().authenticated()	// Las demás rutas serán autenticadas
			// Configuración del login form
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticate")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			// Configuración del logout
			.and()
			.logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/login")
        	.and().csrf().disable(); // Permmite acceder al logout por método GET

	}

}
