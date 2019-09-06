package com.yungyu.kafkademo.demo;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TopicDemo {

    public static void main(String[] args) {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "192.168.20.129:9092");
        try (AdminClient adminClient = AdminClient.create(props)) {
            List<NewTopic> topics = new ArrayList<>();
            NewTopic topic = new NewTopic("ccmpCdr1", 1, (short) 1);
            topics.add(topic);

            CreateTopicsResult result = adminClient.createTopics(topics);
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
