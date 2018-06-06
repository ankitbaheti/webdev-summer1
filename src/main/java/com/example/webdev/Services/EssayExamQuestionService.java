package com.example.webdev.Services;

import com.example.webdev.Models.EssayExamQuestion;
import com.example.webdev.Models.Exam;
import com.example.webdev.Repositories.EssayExamQuestionRepository;
import com.example.webdev.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class EssayExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    EssayExamQuestionRepository essayExamQuestionRepository;

    @PostMapping("/api/exam/{examId}/essay")
    EssayExamQuestion createEssayQuestion(@PathVariable("examId") int examId,
                                          @RequestBody EssayExamQuestion essayExamQuestion){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            essayExamQuestion.setType("Essay");
            essayExamQuestion.setExam(exam);
            return essayExamQuestionRepository.save(essayExamQuestion);
        }
        return null;
    }
}
