package com.example.webdev.Services;

import com.example.webdev.Models.Course;
import com.example.webdev.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    List<Course> findAllCourse(){
        return (List<Course>) courseRepository.findAll();
    }

    @PostMapping("/api/course")
    Course createCourse(@RequestBody Course course){

        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    void deleteCourse(@PathVariable("courseId") int courseId){
        if(courseRepository.findById(courseId).isPresent()){
            courseRepository.deleteById(courseId);
        }
    }

    @GetMapping("/api/course/{courseId}")
    Course findCourseById(@PathVariable("courseId") int courseId){
        return courseRepository.findById(courseId).get();
    }
}
