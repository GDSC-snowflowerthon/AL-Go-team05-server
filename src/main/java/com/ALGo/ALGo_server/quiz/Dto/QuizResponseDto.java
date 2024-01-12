package com.ALGo.ALGo_server.quiz.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class QuizResponseDto {
    List<QuizDto> quizList;

    public QuizResponseDto(List<QuizDto> quizList){
        this.quizList = quizList;
    }
}
