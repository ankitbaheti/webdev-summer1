package com.example.webdev.Services;

import com.example.webdev.Models.Course;
import com.example.webdev.Models.Module;
import com.example.webdev.Repositories.CourseRepository;
import com.example.webdev.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @PostMapping("/api/course/{courseId}/module")
    Module createModule(@PathVariable("courseId") int courseId,
                        @RequestBody Module module){
        if(courseRepository.findById(courseId).isPresent()){
            Course course = courseRepository.findById(courseId).get();
            module.setCourse(course);
            return moduleRepository.save(module);
        }
        return null;
    }

    @GetMapping("/api/course/{courseId}/module")
    List<Module> findAllModuleForCourse(@PathVariable("courseId") int courseId){
        if(courseRepository.findById(courseId).isPresent()){
            Course course = courseRepository.findById(courseId).get();
            return course.getModules();
        }
        return null;
    }

    @GetMapping("/api/module")
    List<Module>  findAllModule(){
        return (List<Module>) moduleRepository.findAll();
    }

    @GetMapping("/api/module/{moduleId}")
    Module findModuleById(@PathVariable("moduleId") int moduleId){
        return moduleRepository.findById(moduleId).get();
    }

    @DeleteMapping("/api/module/{moduleId}")
    void deleteModule(@PathVariable("moduleId") int moduleId){
        moduleRepository.deleteById(moduleId);
    }
}
