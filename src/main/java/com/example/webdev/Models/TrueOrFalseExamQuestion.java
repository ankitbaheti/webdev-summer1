package com.example.webdev.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {

    @JsonProperty
    boolean isTrue;

    public TrueOrFalseExamQuestion() {
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
