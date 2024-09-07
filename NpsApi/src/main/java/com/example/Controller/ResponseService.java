package com.example.Controller;


import com.example.Model.Answer;
import com.example.Model.Response;
import com.example.Repository.AnswerRepository;
import com.example.Repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public boolean addResponse(Response response) {
        log.info("Adding response:{}",response);
        try {
            responseRepository.insert(response);
            log.info("Response added successfully.");
            return true;
        } catch (Exception e) {
            log.error("Error occurred while adding response: {}", response, e);
            return false;
        }
    }

    public Response getResponse(String userId, String surveyId) {
        log.info("Fetching response for userId:{} and surveyId:{}",userId,surveyId);
        return responseRepository.findByUserIdAndSurveyId(userId, surveyId)
                .orElseGet(() -> {
                    log.warn("No response found for user ID: {} and survey ID: {}", userId, surveyId); return null;
                });
    }

    public boolean updateResponse(Response response) {
        log.info("Updating response:{}",response);
        if (responseRepository.existsByResponseId(response.getResponseId())) {
            try {
                responseRepository.save(response);
                log.info("Response updated successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while updating response: {}", response, e);
                return false;
            }
        } else {
            log.warn("No existing response found for response ID: {}", response.getResponseId());
            return false;
        }
    }

    public boolean deleteResponse(Response response) {
        log.info("Deleting response:{}",response);
        if (responseRepository.existsByResponseId(response.getResponseId())) {
            try {
                responseRepository.deleteByResponseId(response.getResponseId());
                log.info("Response deleted successfully."); // Yanıt başarıyla silindiğinde bilgi verir
                return true;
            } catch (Exception e) {
                log.error("Error occurred while deleting response, response ID: {}", response.getResponseId(), e); // Silme sırasında hata oluşursa detaylarıyla birlikte loglar
                return false;
            }
        } else {
            log.warn("No existing response found for response ID: {}", response.getResponseId()); // Silinmeye çalışılan yanıt bulunamazsa uyarı logu
            return false;
        }
    }




}
