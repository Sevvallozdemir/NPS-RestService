package com.example.Repository;

import com.example.Model.Response;
import com.example.Model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface SurveyRepository extends MongoRepository<Survey, String> {
    Optional<Survey> findBySurveyIdAndScore(String surveyId, String score);
    Optional<Survey> findByScore(String score);
    boolean existsBySurveyId(String surveyId);
    void deleteBySurveyId(String surveyId);
}
