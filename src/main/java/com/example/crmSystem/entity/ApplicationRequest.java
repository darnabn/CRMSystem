package com.example.crmSystem.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    private Long id;
    private String userName;
    private String courseName;
    private String commentary;
    private String phone;
    private boolean handled;
}
