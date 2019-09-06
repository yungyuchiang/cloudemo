package com.yungyu.kafkademo.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

public class ProducerDemo {

    public static void main (String[] args) {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "192.168.20.129:9092");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 16384);
        props.put("linger.ms", 300);
        props.put("buffer.memory", 1635215678);
        props.put("retry.backoff.ms", 100);
        props.put("request.timeout.ms", 20000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100000; i++) {
            StringJoiner cdr = new StringJoiner("|");
            cdr.add("ec:"+ i/10000)
                    .add("001")
                    .add("002")
                    .add(i%5 == 0 ? "2":"1")
                    .add(UUID.randomUUID().toString())
                    .add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                    .add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                    .add("004")
                    .add("msisdn:"+ i/10)
                    .add("005")
                    .add("0")
                    .add("006")
                    .add(i%2 ==0 ? "100" : "200")
                    .add("2")
                    .add("121");

            producer.send(new ProducerRecord<>("ccmpCdr1", cdr.toString()));
        }

        producer.close();
    }

}
