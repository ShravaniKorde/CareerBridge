package com.example.CareerBridge.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type;
    private String username;
    private List<String> roles;

    public JwtResponse(String token, String type, String username, List<String> roles) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
    }
}

