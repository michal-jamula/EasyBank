package SpringSecurityUdemy.config;


import SpringSecurityUdemy.filter.AuthoritiesLoggingAfterFilter;
import SpringSecurityUdemy.filter.AuthoritiesLoggingAtFilter;
import SpringSecurityUdemy.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpRetryException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and().csrf().ignoringAntMatchers("/contact").
                csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()

                //This is how to add new authentication and business logic filters into the application
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)


                .authorizeHttpRequests((auth) -> auth
                        /*
                         * Here AntMatchers have been used, but can also use mvcMathers or regexMathers
                         * mvcMatchers is regarded as more secure
                         *      e.g. mavMatchers(/"secured") will match /secured, /secured/, /secured.html and other file extensions
                         * use regexMatchers for a more complex path options */
                         //      e.g. regexMatchers(".*/(en|es)") will look for any folders named "en" or "es"

                        .antMatchers("/myAccount").hasRole("USER")
                        .antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                        .antMatchers("/myLoans").hasRole("ROOT")
                        .antMatchers( "/myCards", "/user").authenticated()
                        .antMatchers("/notices", "/contact").permitAll()
                ).httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}