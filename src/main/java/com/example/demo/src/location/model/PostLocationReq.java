package com.example.demo.src.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLocationReq {
    private int userId;
    private String ipAddress;
}
