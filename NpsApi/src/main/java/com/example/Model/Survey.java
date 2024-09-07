package com.example.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "Survey")
public class Survey {
    @Id
    private String surveyId;
    private String description;
    private String score; //1-10  //sil
    private List<Question> questions;
}
