package com.example.SQS_Queue.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class SqsService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${aws.sqs-queue-url}")
    private String sqsQueueUrl;

    private final SqsClient sqsClient;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    @PostConstruct  // This method runs automatically after Spring initializes the bean.
    public void startPolling(){
        Executors.newSingleThreadExecutor().submit(()->{
            while (true){
                ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                        .queueUrl(sqsQueueUrl)
                        .maxNumberOfMessages(5)
                        .waitTimeSeconds(10)
                        .build();

                for (Message message : sqsClient.receiveMessage(request).messages()) {
                    try {
                        // Parse the JSON message body
                        JsonNode outerNode = objectMapper.readTree(message.body());
                        String innerJson = outerNode.get("Message").asText(); // This is the JSON string

                        JsonNode innerNode = objectMapper.readTree(innerJson);
                        String orderId = innerNode.get("orderId").asText();

                        System.out.println(" Received orderId: " + orderId);
                        System.out.println(" Message Attributes: " + message.messageAttributes());
                        System.out.println("Message Body: " + message.body());

                        //Database logic here


                        // ✅ Delete message from the queue after processing
                        sqsClient.deleteMessage(DeleteMessageRequest.builder()
                                .queueUrl(sqsQueueUrl)
                                .receiptHandle(message.receiptHandle())
                                .build());

                        System.out.println("🗑️ Message deleted from SQS.");

                    } catch (Exception e) {
                        System.err.println("❗ Error processing message: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
