package com.example.Model;

import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
@Document(collection = "User")
public class User {

    @Id
    private String userId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;
    private boolean status;
    private LocalDateTime lastUpdateDate;
    private int userPoint = 0;


    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }
}


