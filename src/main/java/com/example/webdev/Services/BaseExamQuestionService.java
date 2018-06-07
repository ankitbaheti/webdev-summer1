package com.example.webdev.Services;

import com.example.webdev.Models.BaseExamQuestion;
import com.example.webdev.Models.Exam;
import com.example.webdev.Repositories.BaseExamQuestionRepository;
import com.example.webdev.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    BaseExamQuestionRepository baseExamQuestionRepository;

    @GetMapping("/api/exam/{examId}/questions")
    List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            return exam.getQuestions();
        }
        return null;
    }

    @DeleteMapping("/api/question/{questionId}")
    void deleteQuestion(@PathVariable("questionId") int questionId){
        if(baseExamQuestionRepository.findById(questionId).isPresent())
            baseExamQuestionRepository.deleteById(questionId);
    }
}
