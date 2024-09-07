package com.example.Model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SafetyRating {
    private String category;
    private String probability;

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
}
