package com.example.commentsapi.mq;

import com.example.commentsapi.service.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class Receiver {

    @Autowired
    CommentService commentService;

    @RabbitHandler
    public void receive(String id){
        commentService.deleteCommentsByPostId(Integer.valueOf(id));
    }
}