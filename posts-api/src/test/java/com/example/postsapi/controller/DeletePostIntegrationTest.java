package com.example.postsapi.controller;

import com.example.postsapi.model.Post;
import com.example.postsapi.mq.DummyReceiver;
import com.example.postsapi.service.PostService;
import com.google.common.io.Files;
import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeletePostIntegrationTest {
    @Value("${spring.rabbitmq.port}")
    private String rabbitmqPort;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DummyReceiver receiver;

    @Autowired
    PostService postService;

    @ClassRule
    public static final ExternalResource resource = new ExternalResource() {
        private Broker broker = new Broker();

        @Override
        protected void before() throws Throwable {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("src/test/resources/application.properties")));
            String amqpPort = properties.getProperty("spring.rabbitmq.port");
            File tmpFolder = Files.createTempDir();
            String userDir = System.getProperty("user.dir").toString();
            File file = new File(userDir);
            String homePath = file.getAbsolutePath();
            BrokerOptions brokerOptions = new BrokerOptions();
            brokerOptions.setConfigProperty("qpid.work_dir", tmpFolder.getAbsolutePath());
            brokerOptions.setConfigProperty("qpid.amqp_port", amqpPort);
            brokerOptions.setConfigProperty("qpid.home_dir", homePath);
            brokerOptions.setInitialConfigurationLocation(homePath + "/src/test/resources/qpid-config.json");
            broker.startup(brokerOptions);
        }

        @Override
        protected void after() {
            broker.shutdown();
        }
    };


    @Test
    public void simpleReceive_Message_Success() throws Exception {
        rabbitTemplate.convertAndSend("queue1", "Test 1");
        Thread.sleep(5000);
        assertThat(receiver.getId()).isEqualTo("Test 1");
    }

    @Test(expected = NoSuchElementException.class)
    public void deletePost_HttpStatus_Success() throws IOException, InterruptedException {
        Post p = new Post();
        p.setTitle("title");
        p.setDescription("description");
        postService.createPost(p, "username");
        postService.deletePost(1L);
        Thread.sleep(5000);
        assertThat(receiver.getId()).isEqualTo("1");
        postService.searchById(1L);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deletePost_NoPostWithId_Failure() throws IOException, InterruptedException {
        postService.deletePost(1L);
    }

}
