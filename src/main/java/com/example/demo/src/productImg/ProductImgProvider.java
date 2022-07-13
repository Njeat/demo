package com.example.demo.src.productImg;

import com.example.demo.config.BaseException;
import com.example.demo.src.productImg.model.GetProductImgRes;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ProductImgProvider {

    private final ProductImgDao productImgDao;

    public ProductImgProvider(ProductImgDao productImgDao){
        this.productImgDao = productImgDao;
    }

    public List<GetProductImgRes> getProductImg(int productId) throws BaseException{
        try{
            List<GetProductImgRes> getProductImgRes = productImgDao.getProductImg(productId);
            return getProductImgRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
