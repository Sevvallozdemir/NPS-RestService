package com.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Answer {
    @Id
    private String answerId;
    private String questionId;
    private int userPoint;
    private double pointAverage;

    public Answer(String questionId, int userPoint) {
        this.questionId = questionId;
        this.userPoint = userPoint;
    }
}