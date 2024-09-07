package com.example.Controller;

import com.example.FeignClient.GeminiClient;
import com.example.Model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatService {
    @Autowired
    private GeminiClient geminiClient;

    public String getChatText() {
        ChatResponse chatResponse = geminiClient.getChatResponse("Merhaba nasılsın?");
        // İlk adayı alıp text kısmını döndürüyoruz.
        return chatResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
    }


}
