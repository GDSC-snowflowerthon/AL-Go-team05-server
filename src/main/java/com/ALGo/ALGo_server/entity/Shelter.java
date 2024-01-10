package com.ALGo.ALGo_server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shelter_id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;

}
