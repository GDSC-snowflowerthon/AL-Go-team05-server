package com.ALGo.ALGo_server.repository;

import com.ALGo.ALGo_server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findByQuiz_id(Long quiz_id);

}
