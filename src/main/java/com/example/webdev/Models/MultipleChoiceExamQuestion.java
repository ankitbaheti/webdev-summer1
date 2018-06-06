package com.example.webdev.Models;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion{

    private String[] choices;
    private int correct;

    public MultipleChoiceExamQuestion() {
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
