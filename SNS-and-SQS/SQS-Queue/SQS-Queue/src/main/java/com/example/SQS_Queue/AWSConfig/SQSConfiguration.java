package com.example.SQS_Queue.AWSConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfiguration {

    @Value("${aws.region}")
    private String awsregion;
    @Bean
    public SqsClient sqsClient(){
        return SqsClient.builder()
                .region(Region.of(awsregion))
                .build();
    }
}
