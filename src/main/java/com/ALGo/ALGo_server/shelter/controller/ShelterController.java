package com.ALGo.ALGo_server.shelter.controller;

import com.ALGo.ALGo_server.entity.Shelter;
import com.ALGo.ALGo_server.repository.ShelterRepository;
import com.ALGo.ALGo_server.shelter.dto.ShelterResponse;
import com.ALGo.ALGo_server.shelter.service.ShelterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterRepository shelterRepository;
    private final ShelterService shelterService;

    public ShelterController(ShelterRepository shelterRepository, ShelterService shelterService) {
        this.shelterRepository = shelterRepository;
        this.shelterService = shelterService;
    }

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @PostMapping("/search/{region}")
    public List<ShelterResponse> searchSheltersByRegion(@PathVariable String region) {
        return shelterService.searchSheltersByRegion(region);
    }
}
