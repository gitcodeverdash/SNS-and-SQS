package com.example.SNS_Topic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class Snsservice {


    private final SnsClient snsClient;


    public Snsservice(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    @Value("${aws.sns-topic-arn}")
    private String snsArn;


    public void publishMessage(String message){
        PublishRequest  request = PublishRequest.builder()
                .topicArn(snsArn)
                .message(message)
                .build();
        snsClient.publish(request);

    }
}
