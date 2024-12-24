package com.project.easyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan(basePackages = {"com.project.core", "com.project.easyblogs"})
public class EasyBlogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBlogsApplication.class, args);
    }

}
