package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.product.model.PostProductRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ProductService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public PostProductRes createProduct(PostProductReq postProductReq) throws BaseException {
        try{
            int productId = productDao.createProduct(postProductReq);
            return new PostProductRes(productId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
