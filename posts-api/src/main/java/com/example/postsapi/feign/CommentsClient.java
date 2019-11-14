package com.example.postsapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CommentsClient", url="http://comments-api:8083")
public interface CommentsClient {
    @DeleteMapping(value = "/posts/{postId}")
    public HttpStatus deleteCommentsByPostId(@PathVariable long postId);
}
