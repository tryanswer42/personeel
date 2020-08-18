package be.vdab.personeel.security;
/**
 * @author Mulangu C
 */

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private static final String PERSONEEL = "personeel";
    private static final String USERS_BY_USERNAME = "select email as username, paswoord as password, '1' as enabled" +
            " from werknemers where email = ?";
    private static final String AUTHORITIES_BY_USERNAME = "select werknemers.email as username, 'personeel' as authorities" +
            " from werknemers " +
            " where werknemers.email = ?";


    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        USERS_BY_USERNAME)
                .authoritiesByUsernameQuery(
                        AUTHORITIES_BY_USERNAME
                );

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**")
        //.mvcMatchers("/error/**")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login", "/jobtitels/**").permitAll()
                .mvcMatchers("/**").authenticated());
        http.logout(logout -> logout.logoutSuccessUrl("/"));
    }
}