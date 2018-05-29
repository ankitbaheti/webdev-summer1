package com.example.webdev.Services;

import com.example.webdev.Models.Lesson;
import com.example.webdev.Models.Module;
import com.example.webdev.Repositories.CourseRepository;
import com.example.webdev.Repositories.LessonRepository;
import com.example.webdev.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/api/module/{moduleId}/lesson")
    Lesson createLesson(@PathVariable("moduleId") int moduleId,
                        @RequestBody Lesson lesson){
        if(moduleRepository.findById(moduleId).isPresent()){
            Module module = moduleRepository.findById(moduleId).get();
            lesson.setModule(module);
            return lessonRepository.save(lesson);
        }
        return null;
    }

    @GetMapping("/api/module/{moduleId}/lesson")
    List<Lesson> findAllLessonForModule(@PathVariable("moduleId") int moduleId){
        if(moduleRepository.findById(moduleId).isPresent()){
            Module module = moduleRepository.findById(moduleId).get();
            return module.getLessons();
        }
        return null;
    }

    @GetMapping("/api/lesson")
    List<Lesson> findAllLesson(){
        return (List<Lesson>) lessonRepository.findAll();
    }

    @DeleteMapping("/api/lesson/{lessonId}")
    void deleteLessonById(@PathVariable("lessonId") int lessonId){
        lessonRepository.deleteById(lessonId);
    }

    @PutMapping("/api/lesson/{lessonId}")
    Lesson updateLesson(@PathVariable("lessonId") int lessonId,
                        @RequestBody Lesson newLesson){
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            lesson.updateLesson(newLesson);
            return lessonRepository.save(lesson);
        }
        return null;
    }
}
