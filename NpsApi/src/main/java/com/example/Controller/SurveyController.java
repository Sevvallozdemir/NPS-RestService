package com.example.Controller;

import com.example.Model.Answer;
import com.example.Model.Response;
import com.example.Model.Survey;
import com.example.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database")
public class SurveyController {
    private final SurveyService surveyService;
    private final ResponseService responseService;
    private final UserService userService;



    @RequestMapping(value = "/addSurvey", method = {RequestMethod.POST})
    public boolean addSurvey(@RequestBody Survey survey) {
        return surveyService.addSurvey(survey);
    }

    @RequestMapping(value = "/getSurvey", method = {RequestMethod.GET})
    public Survey getSurvey(@RequestParam(value = "surveyId", required = false) String surveyId,
                            @RequestParam(value = "score", required = false) String score) { //score ekle
        return surveyService.getSurvey(surveyId,score);
    }

    @RequestMapping(value = "/getSurveyByScore", method = {RequestMethod.GET})  //cash uygula
    public Survey getSurveyByScore(@RequestParam(value = "score", required = false) String score) {
        return surveyService.getSurveyByScore(score);
    }

    @RequestMapping(value = "/updateSurvey", method = {RequestMethod.PUT})
    public boolean updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }

    @RequestMapping(value = "/deleteSurvey", method = {RequestMethod.DELETE})
    public boolean deleteSurvey(@RequestBody Survey survey) {
        return surveyService.deleteSurvey(survey);
    }


    @PostMapping("/sendSurvey")
    public ResponseEntity<?> sendSurvey(@RequestParam String userId) {
        try {
            // Kullanıcıyı bul ve userPoint'ini al
            User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
            int userPoint = user.getUserPoint();

            // Gönderilecek anketi belirle
            String surveyId = userPoint < 5 ? "1" : "2";

            // Anketi getir
            Survey survey = surveyService.getSurveyById(surveyId).orElseThrow(() -> new RuntimeException("Anket bulunamadı"));

            // Anketi kullanıcıya gönder
            return ResponseEntity.ok(survey);

        } catch (Exception e) {
            // Hata durumunda HTTP 500 döndür
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
