package com.example.webdev.Services;

import com.example.webdev.Models.BaseExamQuestion;
import com.example.webdev.Models.Exam;
import com.example.webdev.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @GetMapping("/api/exam/{examId}/questions")
    List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            return exam.getQuestions();
        }
        return null;
    }
}
