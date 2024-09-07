package com.example.Controller;

import com.example.Model.Answer;
import com.example.Model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database")
public class QuestionController {
    private final QuestionService questionService;


    @RequestMapping(value = "/addQuestion", method = {RequestMethod.POST})
    public boolean addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @RequestMapping(value = "/getQuestion", method = {RequestMethod.GET})
    public Question getQuestion(@RequestParam(value = "questionId", required = false)String questionId,
                                @RequestParam(value = "surveyId", required = false)String surveyId) {
        return questionService.getQuestion(questionId, surveyId);
    }

    @RequestMapping(value = "/updateQuestion", method = {RequestMethod.PUT})
    public boolean updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @RequestMapping(value = "/deleteQuestion", method = {RequestMethod.DELETE})
    public boolean deleteQuestion(@RequestBody Question question) {
        return questionService.deleteQuestion(question);
    }
}
