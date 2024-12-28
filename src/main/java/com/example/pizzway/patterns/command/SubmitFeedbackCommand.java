package com.example.pizzway.patterns.command;

public class SubmitFeedbackCommand implements Command {
    private String feedback;

    public SubmitFeedbackCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        System.out.println("Feedback submitted: " + feedback);
        // Code to save feedback to a file or database
    }

    @Override
    public void undo() {
        System.out.println("Feedback removed: " + feedback);
        // Code to remove feedback if undo is supported
    }
}
