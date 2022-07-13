package com.example.demo.src.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    private int locationId;
    private int userId;
    private String ipAddress;
    private String city;
    private String status;
    private String createdAt;
    private String updatedAt;
}
