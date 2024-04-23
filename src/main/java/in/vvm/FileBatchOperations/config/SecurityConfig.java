//package in.vvm.FileBatchOperations.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/public/**")
//				.addResourceLocations("classpath:/static/public/");
//		// Resources controlled by Spring Security, which
//		// adds "Cache-Control: must-revalidate".
//		registry.addResourceHandler("/static/**")
//				.addResourceLocations("classpath:/static/")
//				.setCachePeriod(3600 * 24);
//	}
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
//				.formLogin(form -> form.defaultSuccessUrl("/Home")).httpBasic(Customizer.withDefaults());
//		return httpSecurity.build();
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
//			PasswordEncoder passwordEncoder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("user")
//				.password(passwordEncoder.encode("password")).roles("USER").and().withUser("admin")
//				.password(passwordEncoder.encode("admin")).roles("ADMIN");
//	}
//}