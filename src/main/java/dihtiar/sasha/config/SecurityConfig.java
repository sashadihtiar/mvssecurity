package dihtiar.sasha.config;

import dihtiar.sasha.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("dihtiar.sasha.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/user").anonymous()
                .antMatchers("/yourprof", "/films", "/yourprof/config", "/halls",
                        "session", "/ticket/your", "/places", "/ticket/buy","/payments").authenticated()
                .antMatchers("/roles", "/users/finduserbyid", "/roles/new"
                        , "/roles/findrolebyid"
                        , "/roles/delete"
                        , "/roles/update",
                        "/users", "/films/delete",
                        "/films/new", "/halls/new",
                        "/halls/delete","/properties","/properties/get").hasAuthority("ADMIN")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/login/process")
                .usernameParameter("login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/yourprof")
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
