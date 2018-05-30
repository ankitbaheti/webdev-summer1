package com.example.webdev.Services;

import com.example.webdev.Models.Lesson;
import com.example.webdev.Models.Widget;
import com.example.webdev.Repositories.LessonRepository;
import com.example.webdev.Repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    WidgetRepository widgetRepository;

    @GetMapping("/api/widget")
    List<Widget> findAllWidget(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    Widget findWidgetById(@PathVariable("widgetId") int widgetId){
        if(widgetRepository.findById(widgetId).isPresent()){
            return widgetRepository.findById(widgetId).get();
        }
        return null;
    }

    @DeleteMapping("/api/widget/{widgetId}")
    void deleteWidgetById(@PathVariable("widgetId") int widgetId){
        if(widgetRepository.findById(widgetId).isPresent())
            widgetRepository.deleteById(widgetId);
    }

    @PutMapping("/api/widget/{widgetId}")
    Widget updateWidget(@PathVariable("widgetId") int widgetId,
                        @RequestBody Widget newWidget){
        if(widgetRepository.findById(widgetId).isPresent()){
            Widget widget = widgetRepository.findById(widgetId).get();
            widget.updateWidget(newWidget);
            return widgetRepository.save(widget);
        }
        return null;
    }

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
