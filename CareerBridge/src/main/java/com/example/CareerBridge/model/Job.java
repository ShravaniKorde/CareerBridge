package com.example.CareerBridge.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "JobPost")
public class Job {

    @Id
    private String id;
    private String desc;
    private int exp;
    private String profile;
    private List<String> techs;
    
}
