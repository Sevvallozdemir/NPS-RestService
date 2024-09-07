package com.example.Repository;

import com.example.Model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;
public interface AnswerRepository extends MongoRepository<Answer, String> {
    Optional<Object> findByQuestionIdAndUserPoint(String questionId, String userPoint);
    boolean existsByQuestionId(String questionId);
    void deleteByQuestionId(String questionId);
    //List<Answer> findAllByPhoneNumber(String phoneNumber);
}
