package com.example.webdev.Services;

import com.example.webdev.Models.Lesson;
import com.example.webdev.Models.Widget;
import com.example.webdev.Repositories.LessonRepository;
import com.example.webdev.Repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    WidgetRepository widgetRepository;

    @GetMapping("/api/lesson/{lessonId}/widget")
    List<Widget> findAllWidgetForLesson(@PathVariable("lessonId") int lessonId){
        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            return lesson.getWidgets();
        }
        return null;
    }

    @PostMapping("/api/lesson/{lessonId}/widget")
    public void createWidget(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> widgets){

        if(lessonRepository.findById(lessonId).isPresent()){
            Lesson lesson = lessonRepository.findById(lessonId).get();
            List<Widget> oldWidget = lesson.getWidgets();

            for(Widget widget: oldWidget)
                widgetRepository.deleteById(widget.getId());

            for(Widget widget: widgets){
                widget.setLesson(lesson);
                widgetRepository.save(widget);
            }
            lesson.setWidgets(widgets);
        }
    }
}
