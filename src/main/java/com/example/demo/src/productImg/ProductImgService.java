package com.example.demo.src.productImg;

import com.example.demo.src.product.ProductDao;
import com.example.demo.src.productImg.model.ProductImg;
import org.springframework.stereotype.Service;

@Service
public class ProductImgService {

    private final ProductImgDao productImgDao;

    public ProductImgService(ProductImgDao productImgDao){
        this.productImgDao = productImgDao;
    }
}
