package com.example.demo.src.geolite;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.geolite.model.GetGeoIp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeoIPTestController {
    private final RawDBDemoGeoIPLocationService locationService;

    public GeoIPTestController() throws IOException {
        this.locationService = new RawDBDemoGeoIPLocationService();
    }

    @PostMapping("/GeoIPTest")
    public BaseResponse<GetGeoIp> getLocation(
            @RequestParam(value="ipAddress", required=true) String ipAddress
    ) throws Exception {
        GetGeoIp getGeoIp = locationService.getLocation(ipAddress);
        return new BaseResponse<>(getGeoIp);

//        RawDBDemoGeoIPLocationService locationService
//                = new RawDBDemoGeoIPLocationService();
//        return locationService.getLocation(ipAddress);
    }
}
