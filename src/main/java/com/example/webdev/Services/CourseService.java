package com.example.webdev.Services;

import com.example.webdev.Models.Course;
import com.example.webdev.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    List<Course> findAllCourse(){
        return (List<Course>) courseRepository.findAll();
    }
}
