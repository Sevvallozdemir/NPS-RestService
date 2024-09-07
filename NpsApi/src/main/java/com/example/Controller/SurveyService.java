package com.example.Controller;

import com.example.FeignClient.GeminiClient;
import com.example.Model.*;
import com.example.Repository.QuestionRepository;
import com.example.Repository.ResponseRepository;
import com.example.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.Repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;


    public boolean addSurvey(Survey survey){

        try {
            surveyRepository.insert(survey);
            log.info("Survey added successfully.");
            return true;
        } catch (Exception e) {
            log.error("Error occurred while adding survey: {}", survey, e);
            return false;
        }
    }
    @Cacheable("survey")
    public Survey getSurvey(String surveyId, String score){









        log.info("Fetching survey with Id:{} , description:{} and score:{}", surveyId, score);
        return surveyRepository.findBySurveyIdAndScore(surveyId, score)
                .orElseGet(() -> {
                    log.warn("No survey found with ID: {}, description: {}, and score: {}", surveyId,  score); //anket bulunamadi
                    return null;
                });
    }


    public Survey getSurveyByScore(String score){
        log.info("Fetching survey by score: {}", score);
        Survey survey;

        survey=surveyRepository.findByScore(score).orElse(null);
        if (survey != null) {
            List<Question> questionList = questionRepository.findBySurveyId(survey.getSurveyId());
            survey.setQuestions(questionList);
            log.info("Survey with score {} retrieved successfully with {} questions.", score, questionList.size());
        } else {
            log.warn("No survey found with score: {}", score);
        }
        return survey;
    }

    public boolean updateSurvey(Survey survey) {
        log.info("Updating survey: {}", survey);
        if (surveyRepository.existsBySurveyId(survey.getSurveyId())) {
            try {
                surveyRepository.save(survey);
                log.info("Survey updated successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while updating survey: {}", survey, e);
                return false;
            }
        } else {
            log.warn("No existing survey found for survey ID: {}", survey.getSurveyId());
            return false;
        }
    }

    public boolean deleteSurvey(Survey survey) {
        log.info("Deleting survey: {}", survey);
        if (surveyRepository.existsBySurveyId(survey.getSurveyId())) {
            try {
                surveyRepository.deleteBySurveyId(survey.getSurveyId());
                log.info("Survey deleted successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while deleting survey with ID: {}", survey.getSurveyId(), e);
                return false;
            }
        } else {
            log.warn("No existing survey found for survey ID: {}", survey.getSurveyId());
            return false;
        }
    }

    public Optional<Survey> getSurveyById(String surveyId) {
        return surveyRepository.findById(surveyId);
    }
}




