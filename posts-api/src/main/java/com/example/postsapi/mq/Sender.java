package com.example.postsapi.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Sender {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send(String id) {
        template.convertAndSend(queue.getName(), id);
    }
}
