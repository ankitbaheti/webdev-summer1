package com.example.webdev.Services;

import com.example.webdev.Models.Assignment;
import com.example.webdev.Models.Lesson;
import com.example.webdev.Models.Widget;
import com.example.webdev.Repositories.AssignmentRepository;
import com.example.webdev.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    LessonRepository lessonRepository;

    @PostMapping("/api/lesson/{lessonId}/assignment")
    Assignment createAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment){
        System.out.println("here");
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            assignment.setWidgetType("Assignment");
            assignment.setLesson(lesson);
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    @GetMapping("/api/lesson/{lessonId}/assignment")
    List<Assignment> getAssignmentForLesson(@PathVariable("lessonId") int lessonId){
        List<Assignment> assignmentList = new ArrayList<>();
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            List<Widget> widgets = lesson.getWidgets();
            for(Widget widget: widgets){
                if(widget.getWidgetType().equals("Assignment"))
                    assignmentList.add((Assignment) widget);
            }
            return assignmentList;
        }
        return null;
    }

    @GetMapping("/api/assignment")
    List<Assignment> findAllAssignment(){
        return (List<Assignment>) assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{assignmentId}")
    Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId){
        if(assignmentRepository.findById(assignmentId).isPresent())
            return assignmentRepository.findById(assignmentId).get();
        return null;
    }

    @PutMapping("/api/assignment/{assignmentId}")
    Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId, @RequestBody Assignment newAssignment){
        if(assignmentRepository.findById(assignmentId).isPresent()){
            Assignment assignment = assignmentRepository.findById(assignmentId).get();
            assignment.updateAssignment(newAssignment);
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    @DeleteMapping("/api/assignment/{assignmentId}")
    void deleteAssignmentById(@PathVariable("assignmentId") int assignmentId){
        if(assignmentRepository.findById(assignmentId).isPresent())
            assignmentRepository.deleteById(assignmentId);
    }
}
