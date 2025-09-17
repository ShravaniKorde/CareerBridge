package com.example.CareerBridge.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobResponseDTO {

    private String id; 
    private String profile;
    private int exp;
    private String desc;
    private List<String> techs;
    
}
