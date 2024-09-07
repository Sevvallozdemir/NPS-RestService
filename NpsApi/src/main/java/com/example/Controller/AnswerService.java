package com.example.Controller;


import com.example.Repository.AnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.Model.Answer;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


@Slf4j
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ResponseService responseService;


    public boolean addAnswer(Answer answer){
        log.info("Adding answer: {}", answer);
        try {
            answerRepository.insert(answer);
            log.info("Answer added successfully.");
            return true;
        } catch (Exception e) {
            log.error("Error adding answer: {}", answer, e);
            return false;
        }
    }


    @Cacheable("answer")
    public Answer getAnswer(String questionId, String userPoint){
        log.info("Receiving an answer.. QuestionId:{}, Score:{}", questionId, userPoint);  //cevabın alınma süreci
        return (Answer) answerRepository.findByQuestionIdAndUserPoint(questionId, userPoint).orElse(null);
    }

    public boolean updateAnswer(Answer answer) {
        log.info("Updating answer: {}",answer);
        if (answerRepository.existsByQuestionId(answer.getQuestionId())) {
            try {
                answerRepository.save(answer);
                log.info("Answer updated successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error updating answer: {}", answer, e); //Güncelleme sırasında bir hata oluşursa loglar
                return false;
            }
        } else {
            log.warn("QuestionId: No existing answers found for {}.", answer.getQuestionId()); //Güncellenmeye çalışılan cevap bulunamazsa uyarı logu
            return false;
        }
    }

    public boolean deleteAnswer(Answer answer) {
        log.info("Answers are being deleted.. QuestionId:{}", answer.getQuestionId());
        if (answerRepository.existsByQuestionId(answer.getQuestionId())) {
            answerRepository.deleteByQuestionId(answer.getQuestionId());
            return true;
        }
        return false;
    }

}
