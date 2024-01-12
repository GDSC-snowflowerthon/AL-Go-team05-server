package com.ALGo.ALGo_server.quiz.Service;


import com.ALGo.ALGo_server.entity.Quiz;
import com.ALGo.ALGo_server.quiz.Dto.QuizDto;
import com.ALGo.ALGo_server.quiz.Dto.QuizResponseDto;
import com.ALGo.ALGo_server.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public QuizResponseDto getQuiz(){
        Random random = new Random();

        Set<QuizDto> quizDtoList = new HashSet<>();
        while (quizDtoList.size() < 4){
            Long randomNum = Long.valueOf(random.nextInt(20) + 1);
            Quiz q = quizRepository.findById(randomNum).get();
            QuizDto quizDto = new QuizDto(q);
            quizDtoList.add(quizDto);
        }
        QuizResponseDto responseDto = new QuizResponseDto(quizDtoList.stream().toList());

        return responseDto;
    }
}
