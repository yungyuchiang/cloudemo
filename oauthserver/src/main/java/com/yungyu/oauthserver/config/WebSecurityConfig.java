package com.yungyu.oauthserver.config;

import com.yungyu.oauthserver.filter.ValidateCodeFilter;
import com.yungyu.oauthserver.handler.AuthenctiationFailureHandler;
import com.yungyu.oauthserver.handler.AuthenctiationSuccessHandler;
import com.yungyu.oauthserver.handler.MyAuthenticationAccessDeniedHandler;
import com.yungyu.oauthserver.handler.MyLogOutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenctiationSuccessHandler authenctiationSuccessHandler;

    @Autowired
    private AuthenctiationFailureHandler authenctiationFailureHandler;

    @Autowired
    private MyLogOutSuccessHandler myLogOutSuccessHandler;

    @Autowired
    private MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/authentication/require")                        // 设置登录页面
                .loginProcessingUrl("/login")          // 自定义的登录接口
                .successHandler(authenctiationSuccessHandler)
                .failureHandler(authenctiationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler(myLogOutSuccessHandler)
                .deleteCookies("JSESSIONID")
                // .defaultSuccessUrl("/home").permitAll()     // 登录成功之后，默认跳转的页面
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)// 给i哦其时间
                .userDetailsService(userDetailsService)
                .and().authorizeRequests()                  // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/authentication/require", "/login.html", "/code/image").permitAll()       // 设置所有人都可以访问登录页面
                .anyRequest().authenticated()               // 任何请求,登录后可以访问
                .and()
                .sessionManagement() // Session 管理
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(2)
                .maxSessionsPreventsLogin(true) // 除了后者将前者踢出的策略，我们也可以控制当Session达到最大有效数的时候，不再允许相同的账户登录。
                .expiredSessionStrategy(sessionExpiredStrategy)
                .and().and().csrf().disable();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    public PersistentTokenRepository persistentTokenRepository () {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

}
