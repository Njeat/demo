package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetProductLocationRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ProductProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;

    public ProductProvider(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<GetProductRes> getProducts(int page, int amount) throws BaseException{
        System.out.println("page, amount : " + page + " " + amount);

        try{
            List<GetProductRes> getProductRes = productDao.getProducts(page, amount);
            return getProductRes;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
//    public List<GetProductRes> getProducts(){
//        List<GetProductRes> getProductRes = productDao.getProducts();
//        return getProductRes;
//    }

    public GetProductRes getProductById(int productId) throws  BaseException{
        try{
            GetProductRes getProductRes = productDao.getProductById(productId);
            return getProductRes;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductLocationRes> getProductByCity(String city) throws BaseException{
        try{
            List<GetProductLocationRes> getProductResLocation = productDao.getProductByCity(city);
            return getProductResLocation;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int getProductLastId(){
        int lastProductId = productDao.getProductLastId();
        return lastProductId;
    }


}
