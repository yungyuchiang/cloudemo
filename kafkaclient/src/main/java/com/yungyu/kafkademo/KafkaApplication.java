package com.yungyu.kafkademo;

import com.yungyu.kafkademo.component.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        KafkaSender kafkaSender = context.getBean(KafkaSender.class);

        for(int i = 0; i < 3; i++) {
            kafkaSender.send();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
