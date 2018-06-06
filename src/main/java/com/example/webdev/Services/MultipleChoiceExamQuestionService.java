package com.example.webdev.Services;

import com.example.webdev.Models.Exam;
import com.example.webdev.Models.MultipleChoiceExamQuestion;
import com.example.webdev.Repositories.ExamRepository;
import com.example.webdev.Repositories.MultipleChoiceExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository;

    @PostMapping("/api/exam/{examId}/choice")
    MultipleChoiceExamQuestion createMCQ(@PathVariable("examId") int examId,
                                         @RequestBody MultipleChoiceExamQuestion multipleChoiceExamQuestion){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            multipleChoiceExamQuestion.setType("MCQ");
            multipleChoiceExamQuestion.setExam(exam);
            return multipleChoiceExamQuestionRepository.save(multipleChoiceExamQuestion);
        }
        return null;
    }
}
