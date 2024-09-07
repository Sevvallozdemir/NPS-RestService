package com.example.FeignClient;

import com.example.Model.ChatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "jplaceholder", url = "http://localhost:8080")
    public interface GeminiClient {

        @RequestMapping(method = RequestMethod.GET, value = "/chat")
        public ChatResponse getChatResponse(@RequestParam String prompt);
    }
