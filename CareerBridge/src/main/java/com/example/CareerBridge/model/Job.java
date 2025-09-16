package com.example.CareerBridge.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "jobs")
public class Job {

    @Id
    private String id;
    private String desc;
    private int exp;
    private String profile;
    private List<String> techs;
    
}
