package com.project.core.config;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig implements CommandLineRunner {
    @Value("${server.time-zone}")
    private String timeZone;

    @PostConstruct
    public void init() {
        log.info("{} time zone is set", timeZone);
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }

    @Override
    public void run(String... args) throws Exception {
//        try {
//            server.start();
//        } catch (Exception e) {
//            log.error("--> Error starting socket server: {}", e.getMessage());
//            System.exit(0);
//        }
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
