package com.example.demo.src.user.model;


import com.example.demo.src.location.model.GetLocationRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int userId;
    private String city;
    private String userName;
    private String phoneNum;
    private String profileImgUrl;
    private double manner;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
