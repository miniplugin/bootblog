package org.timespace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.timespace.security.GitProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({GitProperties.class})
public class Application {
    public static void main(String arg[]){
        SpringApplication.run(Application.class, arg);
    }
}
