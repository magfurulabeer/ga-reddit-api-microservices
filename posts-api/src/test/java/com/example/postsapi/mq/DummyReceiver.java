package com.example.postsapi.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class DummyReceiver {
    private String id;

    @RabbitHandler
    public void receive(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
