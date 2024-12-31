package com.example.pizzway.models;

public class Feedback {
    private int id;
    private String feedback;

    public Feedback(int id, String feedback) {
        this.id = id;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

