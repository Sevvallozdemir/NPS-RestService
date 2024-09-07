package com.example.Model;

import lombok.ToString;

@ToString
public class Part {
    private String text;

    // Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
