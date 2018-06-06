package com.example.webdev.Services;

import com.example.webdev.Models.Exam;
import com.example.webdev.Models.Lesson;
import com.example.webdev.Models.Widget;
import com.example.webdev.Repositories.ExamRepository;
import com.example.webdev.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    LessonRepository lessonRepository;

    @PostMapping("/api/lesson/{lessonId}/exam")
    Exam createExam(@PathVariable("lessonId") int lessonId, @RequestBody Exam exam){
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            exam.setWidgetType("Exam");
            exam.setLesson(lesson);
            return examRepository.save(exam);
        }
        return null;
    }

    @GetMapping("/api/lesson/{lessonId}/exam")
    List<Exam> getExamForLesson(@PathVariable("lessonId") int lessonId){
        List<Exam> examList = new ArrayList<>();
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            List<Widget> widgets = lesson.getWidgets();
            for(Widget widget: widgets){
                if(widget.getWidgetType().equals("Exam"))
                    examList.add((Exam) widget);
            }
            return examList;
        }
        return null;
    }
}
