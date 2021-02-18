/*
 * package com.vtt.apps; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * import com.vtt.apps.service.VttUserDetailsService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired VttUserDetailsService vttUserDetailsService;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(vttUserDetailsService);
 * 
 * http .authorizeRequests() .antMatchers("/", "/home").permitAll()
 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login")
 * .permitAll() .and() .logout() .permitAll();
 * 
 * }
 * 
 * 
 * @Bean
 * 
 * @Override public UserDetailsService userDetailsService() { UserDetails user =
 * User.withDefaultPasswordEncoder() .username("user") .password("password")
 * .roles("USER") .build();
 * 
 * return new InMemoryUserDetailsManager(user); }
 * 
 * }
 */