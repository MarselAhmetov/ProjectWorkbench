package team404.project;

import bell.oauth.discord.main.OAuthBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.socket.WebSocketSession;
import team404.project.processor.CustomBeanFactoryPostProcessor;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProjectApplication {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @ApplicationScope
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public OAuthBuilder oAuthBuilder() {
        OAuthBuilder builder = new OAuthBuilder("709778458049249353", "JgOfQHBVPhr3VjMVFaSUnSQUfLEpx8At")
                .setScopes(new String[]{"connections", "guilds", "email"})
                .setRedirectURI("http://127.0.0.1:8080/discord");
        return builder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
