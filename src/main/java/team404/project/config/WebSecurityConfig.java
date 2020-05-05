package team404.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import team404.project.filter.CustomFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomFilter customFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.addFilterAfter(customFilter, FilterSecurityInterceptor.class);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/signUp", "/signIn", "/").permitAll()
                .antMatchers("/friends", "/profile", "/debt", "/debts").authenticated();

        http.formLogin()
                .loginPage("/signIn")
                .usernameParameter("username")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .permitAll();
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
