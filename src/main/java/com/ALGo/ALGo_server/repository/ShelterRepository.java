package com.ALGo.ALGo_server.repository;

import com.ALGo.ALGo_server.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    List<Shelter> findAll();

    List<Shelter> findByRegion(String region);
}
