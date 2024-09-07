package com.example.Repository;

import com.example.Model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findByQuestionIdAndSurveyId(String questionId, String surveyId);
    List<Question> findBySurveyId(String surveyId);
    boolean existsByQuestionId(String questionId);
    void deleteByQuestionId(String questionId);
}
