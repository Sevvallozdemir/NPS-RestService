package com.example.Repository;

import com.example.Model.Response;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;
public interface ResponseRepository extends MongoRepository<Response, String> {
    Optional<Response> findByUserIdAndSurveyId(String userId, String surveyId);
    boolean existsByResponseId(String responseId);
    void deleteByResponseId(String responseId);
    List<Response> findByUserId(String userId);

}
