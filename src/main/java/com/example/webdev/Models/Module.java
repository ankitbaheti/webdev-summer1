package com.example.webdev.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    @JsonIgnore
    private Course course;
    @OneToMany(mappedBy="module", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lesson> lessons = new ArrayList<>();

    public Module() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
//        if(!course.getModules().contains(this))
//            course.getModules().add(this);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}

