package tacos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import tacos.data.UserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new Pbkdf2PasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/api/ingredients").permitAll()
                .antMatchers("/design","/orders/**").permitAll()
                //.access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.PATCH,"/ingredients").permitAll()
                .antMatchers("/**").access("permitAll")

                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .httpBasic()
                .realmName("Taco Cloud")

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**","/ingredients", "/design", "/orders/**", "/api/**")

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        ;

    }

    /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
*/


    /**
    * Сведения о пользователях хранятся непосредственно в файле конфигурации. ПОЛЕЗНО ДЛЯ ТЕСТИРОВАНИЯ
    * @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("buzz")
                .password("infinity")
                .authorities("ROLE USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE USER");
    }*/

   /**
    * Хранение сведений в базе данных используя кодировщик пароля
     @Override
    @Autowired
    DataSource dataSource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "SELECT username, password, enabled from schema.users where username=?")
                .authoritiesByUsernameQuery("SELECT username, authority from schema.userauthorities where username=?")
        .passwordEncoder(new Pbkdf2PasswordEncoder());
    }*/
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
        .passwordCompare()
        .passwordEncoder(new BCryptPasswordEncoder())
        .passwordAttribute("passcode")
        .and().contextSource()
        .root("dc=tacocloud.dc=com")
        .ldif("classpath:users.ldif");
    }*/

}
