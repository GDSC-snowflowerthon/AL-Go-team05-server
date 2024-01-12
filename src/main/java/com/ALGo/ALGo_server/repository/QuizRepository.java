package com.ALGo.ALGo_server.repository;

import com.ALGo.ALGo_server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
