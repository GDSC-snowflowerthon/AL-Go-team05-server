package com.ALGo.ALGo_server.shelter.service;

import com.ALGo.ALGo_server.entity.Shelter;
import com.ALGo.ALGo_server.repository.ShelterRepository;
import com.ALGo.ALGo_server.shelter.dto.ShelterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public List<ShelterResponse> searchSheltersByRegion(String region) {
        List<Shelter> shelters = shelterRepository.findByRegion(region);
        return convertToShelterResponseList(shelters);
    }

    private List<ShelterResponse> convertToShelterResponseList(List<Shelter> shelters) {
        List<ShelterResponse> shelterResponses = new ArrayList<>();
        for (Shelter shelter : shelters) {
            ShelterResponse shelterResponse = new ShelterResponse(
                    shelter.getShelter_id(),
                    shelter.getRegion(),
                    shelter.getAddress(),
                    shelter.getDescription()
            );
            shelterResponses.add(shelterResponse);
        }
        return shelterResponses;
    }


}
