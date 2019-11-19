package com.example.commentsapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PostsClient", url="http://posts-api:8082")
public interface PostsClient {
    @GetMapping(value = "/{postId}")
    public boolean postWithPostIdExists(@PathVariable long postId);
}