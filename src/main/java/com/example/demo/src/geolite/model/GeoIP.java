package com.example.demo.src.geolite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeoIP {
    private String ipAddress;
    private String state;
    private String city;
    private String postal;
    private String latitude;
    private String longitude;
}
