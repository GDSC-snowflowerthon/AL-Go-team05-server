package com.ALGo.ALGo_server.quiz.Dto;

import com.ALGo.ALGo_server.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class QuizDto {
    private Long id;

    public QuizDto(Quiz quiz){
        this.id = quiz.getQuiz_id();
    }
}
