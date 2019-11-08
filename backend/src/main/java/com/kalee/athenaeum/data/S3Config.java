package com.kalee.athenaeum.data;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean(destroyMethod = "close")
    public S3Client s3Client() {

        return S3Client.builder()
                .region(Region.EU_WEST_1) // bucket is here
                .build();
    }
}

