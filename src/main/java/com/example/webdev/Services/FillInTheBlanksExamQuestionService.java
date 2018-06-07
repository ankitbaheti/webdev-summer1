package com.example.webdev.Services;

import com.example.webdev.Models.Exam;
import com.example.webdev.Models.FillInTheBlanksExamQuestion;
import com.example.webdev.Repositories.ExamRepository;
import com.example.webdev.Repositories.FillInTheBlanksExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    FillInTheBlanksExamQuestionRepository fillInTheBlanksExamQuestionRepository;

    @PostMapping("/api/exam/{examId}/blanks")
    FillInTheBlanksExamQuestion createFillBlank(@PathVariable("examId") int examId,
                                                @RequestBody FillInTheBlanksExamQuestion fillInTheBlanksExamQuestion){
        if(examRepository.findById(examId).isPresent()){
            Exam exam = examRepository.findById(examId).get();
            fillInTheBlanksExamQuestion.setType("Fill In The Blank");
            fillInTheBlanksExamQuestion.setExam(exam);
            return fillInTheBlanksExamQuestionRepository.save(fillInTheBlanksExamQuestion);
        }
        return null;
    }

    @PutMapping("/api/fillblank/{questionId}")
    FillInTheBlanksExamQuestion updateFillBlank(@PathVariable("questionId") int questionId,
                                                @RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion){
        if(fillInTheBlanksExamQuestionRepository.findById(questionId).isPresent()){
            FillInTheBlanksExamQuestion fillInTheBlanksExamQuestion = fillInTheBlanksExamQuestionRepository
                    .findById(questionId)
                    .get();
            fillInTheBlanksExamQuestion.update(newFillInTheBlanksExamQuestion);
            return fillInTheBlanksExamQuestionRepository.save(fillInTheBlanksExamQuestion);
        }
        return null;
    }
}
