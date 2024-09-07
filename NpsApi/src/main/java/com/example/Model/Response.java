package com.example.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Response")
public class Response {
    @Id
    private String responseId;
    private String userId;
    private String surveyId;
    private List<Answer> answers;

}


