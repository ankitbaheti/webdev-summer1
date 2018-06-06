package com.example.webdev.Services;

import com.example.webdev.Models.Exam;
import com.example.webdev.Models.TrueOrFalseExamQuestion;
import com.example.webdev.Repositories.ExamRepository;
import com.example.webdev.Repositories.TrueOrFalseExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {

    @Autowired
    TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;

    @Autowired
    ExamRepository examRepository;

    @PostMapping("/api/exam/{examId}/truefalse")
    TrueOrFalseExamQuestion createTrueFalseQuestion(@PathVariable("examId") int examId,
                                                    @RequestBody TrueOrFalseExamQuestion trueOrFalseExamQuestion){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            trueOrFalseExamQuestion.setType("TrueFalse");
            trueOrFalseExamQuestion.setExam(exam);
            return trueOrFalseExamQuestionRepository.save(trueOrFalseExamQuestion);
        }
        return null;
    }
}
