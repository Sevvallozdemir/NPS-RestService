package com.example.Controller;

import com.example.Model.Answer;
import com.example.Model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database")
public class ResponseController {
    private final ResponseService responseService;


    @RequestMapping(value = "/addResponse", method = {RequestMethod.POST})
    public boolean addResponse(@RequestBody Response response) {
        return responseService.addResponse(response);
    }

    @RequestMapping(value = "/getResponse", method = {RequestMethod.GET})
    public Response getResponse(@RequestParam(value = "userId", required = false) String userId,
                                @RequestParam(value = "surveyId", required = false) String surveyId) {
        return responseService.getResponse(userId, surveyId);
    }

    @RequestMapping(value = "/updateResponse", method = {RequestMethod.PUT})
    public boolean updateResponse(@RequestBody Response response) {
        return responseService.updateResponse(response);
    }

    @RequestMapping(value = "/deleteResponse", method = {RequestMethod.DELETE})
    public boolean deleteResponse(@RequestBody Response response) {
        return responseService.deleteResponse(response);
    }


}
