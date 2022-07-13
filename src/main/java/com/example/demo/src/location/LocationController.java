package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.geolite.RawDBDemoGeoIPLocationService;
import com.example.demo.src.geolite.model.GetGeoIp;
import com.example.demo.src.location.model.Location;
import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.location.model.PostLocationRes;
import com.example.demo.src.product.model.PostProductRes;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationProvider locationProvider;
    private final RawDBDemoGeoIPLocationService geoIPLocationService;

    public LocationController(LocationService locationService, LocationProvider locationProvider,RawDBDemoGeoIPLocationService geoIPLocationService){
        this.locationProvider = locationProvider;
        this.locationService = locationService;
        this.geoIPLocationService = geoIPLocationService;
    }

    @PostMapping("")
    public BaseResponse<PostLocationRes> pickLocation(@RequestBody PostLocationReq postLocationReq) throws IOException, GeoIp2Exception {
        try{
            GetGeoIp getGeoIp = geoIPLocationService.getLocation(postLocationReq.getIpAddress());
            PostLocationRes postLocationRes = locationService.pickLocation(postLocationReq.getUserId(),getGeoIp.getCity());
            return new BaseResponse<>(postLocationRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
