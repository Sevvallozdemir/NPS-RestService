package com.example.Controller;

import com.example.Model.Answer;
import com.example.Repository.AnswerRepository;
import com.example.Repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;


    public boolean addQuestion(Question question){
        log.info("Adding question:{}",question);
        try{
            questionRepository.insert(question);
            log.info("Question added successfully");
            return true;
        }
        catch (Exception e){
            log.error("Error occured while adding question:{}",question, e);
            return false;
        }
    }

    @Cacheable("question")
    public Question getQuestion(String questionId, String surveyId){
        log.info("Fetching question, questionId:{} and surveyId:{}",questionId, surveyId);  //soru alma süreci
        return (Question) questionRepository.findByQuestionIdAndSurveyId(questionId, surveyId)
                .orElseGet(() -> {
                    log.warn("No question found for question ID: {} and survey ID: {}", questionId, surveyId); //soru bulunamazsa uyarı
                    return null;
                });

    }

    public boolean updateQuestion(Question question) {
        log.info("Updating question:{}",question);
        if (questionRepository.existsByQuestionId(question.getQuestionId())) {
            try {
                questionRepository.save(question);
                log.info("Question updated successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while updating question: {}", question, e); //Güncellemede oluşan hatalarr
                return false;
            }
        } else {
            log.warn("No existing question found for question ID: {}", question.getQuestionId()); //soru bulunamadiginda
            return false;
        }
    }

    public boolean deleteQuestion(Question question) {
        log.info("Deleting question:{}", question);
        if (questionRepository.existsByQuestionId(question.getQuestionId())) {
            try {
                questionRepository.deleteByQuestionId(question.getQuestionId());
                log.info("Question deleted successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while deleting question, question ID: {}", question.getQuestionId(), e);
                return false;
            }
        } else {
            log.warn("No existing question found for question ID: {}", question.getQuestionId());
            return false;
        }
    }

}
