package com.ALGo.ALGo_server.quiz.Controller;

import com.ALGo.ALGo_server.quiz.Dto.QuizResponseDto;
import com.ALGo.ALGo_server.quiz.Service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping()
    public QuizResponseDto getQuiz(){
        return quizService.getQuiz();
    }
}
