package com.example.Model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class Content {
    private List<Part> parts;
    private String role;

    // Getters and Setters
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
