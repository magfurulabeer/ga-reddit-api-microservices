package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.mq.DummyReceiver;
import com.example.postsapi.service.PostService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletePostIntegrationTest {
    @Before
    public void setUpRabbitMQ() {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build();
        EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
        rabbitMq.start();
    }

    @Autowired
    PostService postService;

    @Autowired
    DummyReceiver dummyReceiver;

    public long createPost() {
        Post post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        Post savedPost = postService.createPost(post, "username");
        return savedPost.getId();
    }

    @Test
    public void setUpRabbitMQ_RabbitMQ_Success() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();
        assertEquals(connection.isOpen(), true);
        Channel channel = connection.createChannel();
        assertEquals(channel.isOpen(), true);

//        channel.close();
//        connection.close();
    }

//    @Test
//    public void deletePost_HttpStatusOK_Success() throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//
//        Connection connection = connectionFactory.newConnection();
//        assertEquals(connection.isOpen(), true);
//        Channel channel = connection.createChannel();
//        assertEquals(channel.isOpen(), true);
//
//        long id = createPost();
//        try {
//            postService.deletePost(id);
//            assertEquals(dummyReceiver.getId(), String.valueOf(id));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        channel.close();
//        connection.close();
//    }
}
