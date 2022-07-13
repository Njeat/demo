package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.location.model.PostLocationRes;
import com.example.demo.src.product.model.PostProductRes;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class LocationService {

    private final LocationDao locationDao;

    public LocationService(LocationDao locationDao){
        this.locationDao = locationDao;
    }

    public PostLocationRes pickLocation(int userId, String city) throws BaseException {
        try{
            String getCity = locationDao.pickLocation(userId, city);
            return new PostLocationRes(getCity);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
