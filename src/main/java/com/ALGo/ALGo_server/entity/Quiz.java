package com.ALGo.ALGo_server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private boolean answer;

    @Column(nullable = false)
    private String description;

}
