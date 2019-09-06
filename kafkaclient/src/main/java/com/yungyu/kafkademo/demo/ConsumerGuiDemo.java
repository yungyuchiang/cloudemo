package com.yungyu.kafkademo.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConsumerGuiDemo extends Application{

    private TextArea textArea;

    private KafkaConsumer<String, String> consumer;

    public ConsumerGuiDemo () {
        textArea = new TextArea();
        this.initKafka();
    }

    public void appendTextArea(String text) {
        this.textArea.appendText(text + "\r\n");
    }

    public static void main (String[] args) {
        launch(args);
    }

    public void initKafka () {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "192.168.20.129:9092");
        props.put("group.id", "group2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("ccmpCdr1"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //将偏移设置到最开始
                consumer.seekToBeginning(collection);
            }
        });
    }

    public void startKafka () {
        Task task = new Task<Void> () {
            @Override
            protected Void call() throws Exception {
                ConsumerRecords<String, String> records = consumer.poll(100);

                if (!records.isEmpty()) {
                    Platform.runLater(() -> {
                        for (ConsumerRecord<String, String> record : records) {
                            appendTextArea(String.format("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));
                        }
                    });
                }

                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root = new FlowPane();
        root.getChildren().add(textArea);
        TextField textField = new TextField();
        Button button = new Button("发送");
        button.setOnMouseClicked(event -> {
            String text = textField.getText();
            textField.setText("");
            textArea.appendText(text + "\r\n");
        });
        // root.getChildren().addAll(Arrays.asList(textField, button));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        this.startKafka();
    }

}
