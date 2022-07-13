package com.example.demo.src.geolite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetGeoIp {
    private String state;
    private String city;
    private String latitude;
    private String longitude;
}
