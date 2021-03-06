package cinemaapp.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/showings/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/profile/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                .antMatchers("/reservations/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                .antMatchers("/clubs/{id}/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/Cinema").failureUrl("/login?error=true")
                .and().logout().logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
