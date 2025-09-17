package com.example.CareerBridge.model;

import java.util.List;

import lombok.Data;

@Data
public class JobRequestDTO {

    private String profile;
    private int exp;
    private String desc;
    private List<String> techs;

}
