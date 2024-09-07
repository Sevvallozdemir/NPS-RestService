package com.example.Model;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@ToString
@Data
public class ChatResponse {

    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;

    // Getters and Setters
    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public UsageMetadata getUsageMetadata() {
        return usageMetadata;
    }

    public void setUsageMetadata(UsageMetadata usageMetadata) {
        this.usageMetadata = usageMetadata;
    }
}

