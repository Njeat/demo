package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userId;
//    private String ipAddress;
//    private String city;
    private String userName;
    private String phoneNum;
    private String profileImgUrl;
    private double manner;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
