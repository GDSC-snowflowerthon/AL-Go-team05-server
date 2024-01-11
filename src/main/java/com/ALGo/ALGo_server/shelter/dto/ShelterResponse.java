package com.ALGo.ALGo_server.shelter.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class ShelterResponse {
    private Long shelter_id;
    private String region;
    private String address;
    private String description;

    public ShelterResponse(Long shelter_id, String region, String address, String description){
        this.shelter_id = shelter_id;
        this.region = region;
        this.address = address;
        this.description = description;
    }

}
