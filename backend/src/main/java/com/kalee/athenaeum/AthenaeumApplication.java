package com.kalee.athenaeum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AthenaeumApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AthenaeumApplication.class);

    public static void main(String[] args) {
        LOG.info("--- Application is starting up ---");
        SpringApplication.run(AthenaeumApplication.class, args);
        LOG.info("--- Application has been started ---");
    }

}
