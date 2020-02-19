package org.timespace.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.timespace.github.GithubClient;
import org.timespace.github.GithubUser;
import org.timespace.user.User;
import org.timespace.user.UserRepository;

@Configuration
@EnableOAuth2Sso
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/posts/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/posts/**").hasRole("ADMIN")
                .antMatchers("/categories/**").hasRole("ADMIN")
                .antMatchers("/", "/js/**", "/vendor/**", "/codemirror/**", "/markdown/**", "/login/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    @Bean
    public AuthoritiesExtractor authoritiesExtractor() {
        return map -> {
            String username = (String) map.get("login");
            if ("miniplugin".contains(username)) { //XXXXX 자리에 여러분의 github 이름을 입력하시면 관리자로 인증됩니다.
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN");
            } else {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            }
        };
    }

    @Bean
    public PrincipalExtractor principalExtractor(GithubClient githubClient, UserRepository userRepository) {
        return map -> {
            String githubLogin = (String) map.get("login");
            User loginUser = userRepository.findByGithub(githubLogin);
            if (loginUser == null) {
                logger.info("Initialize user with githubId {}", githubLogin);
                GithubUser user = githubClient.getUser(githubLogin);
                loginUser = new User(user.getEmail(), user.getName(), githubLogin, user.getAvatar());
                userRepository.save(loginUser);
            }
            return loginUser;
        };
    }
}