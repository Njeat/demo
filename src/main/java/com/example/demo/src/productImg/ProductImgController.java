package com.example.demo.src.productImg;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.productImg.model.GetProductImgRes;
import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/product/img")
public class ProductImgController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProductImgProvider productImgProvider;
    private ProductImgService productImgService;

    public ProductImgController(ProductImgProvider productImgProvider, ProductImgService productImgService){
        this.productImgProvider = productImgProvider;
        this.productImgService = productImgService;
    }

    @ResponseBody
    @GetMapping("/{productId}")
    public BaseResponse<List<GetProductImgRes>> getProductImg(@PathVariable("productId") int productId) {
        try{
            List<GetProductImgRes> getProductImgRes = productImgProvider.getProductImg(productId);
            return new BaseResponse<>(GET_PRODUCT_IMG,getProductImgRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
