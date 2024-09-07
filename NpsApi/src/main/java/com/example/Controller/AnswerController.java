package com.example.Controller;

import com.example.Model.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database")
public class AnswerController {
    private final AnswerService answerService;

    @RequestMapping(value = "/addAnswer", method = {RequestMethod.POST})
    public boolean addAnswer(@RequestBody Answer answer) {
        return answerService.addAnswer(answer);
    }

    @RequestMapping(value = "/getAnswer", method = {RequestMethod.GET})
    public Answer getAnswer(@RequestParam(value = "questionId", required = false)String questionId,
                            @RequestParam(value = "userPoint", required = false)String userPoint) {
        return answerService.getAnswer(questionId, userPoint);
    }

    @RequestMapping(value = "/updateAnswer", method = {RequestMethod.PUT})
    public boolean updateAnswer(@RequestBody Answer answer) {
        return answerService.updateAnswer(answer);
    }

    @RequestMapping(value = "/deleteAnswer", method = {RequestMethod.DELETE})
    public boolean deleteAnswer(@RequestBody Answer answer) {
        return answerService.deleteAnswer(answer);
    }
}
