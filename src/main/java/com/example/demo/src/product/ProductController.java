package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetProductLocationRes;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.product.model.PostProductRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.demo.config.BaseResponseStatus.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductProvider productProvider;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductProvider productProvider, ProductService productService){
        this.productProvider = productProvider;
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetProductRes>> getProducts(){
        try{
            List<GetProductRes> getProductRes = productProvider.getProducts();
            return new BaseResponse<>(GET_ALL_PRODUCT_SUCCESS,getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @GetMapping("/get")
//    public String getProducts(Model model){
////        List<GetProductRes> getProductRes = productProvider.getProducts();
////        model.addAttribute("productList",getProductRes);
//
//        return "product/product";
//    }


    @ResponseBody
    @GetMapping(value = "/{productId}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public BaseResponse<GetProductRes> getProductById(@PathVariable int productId){
        if(productId < 0 || productId > productProvider.getProductLastId()){
            return new BaseResponse<>(UNREGISTER_PRODUCT_ID);
        }
        try{
            GetProductRes getProductRes = productProvider.getProductById(productId);
            return new BaseResponse<>(GET_PRODUCT_SUCCESS,getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/city")
    public BaseResponse<List<GetProductLocationRes>> getProductByCity(@RequestParam("city") String city){
        try{
            List<GetProductLocationRes> getProductResLocation = productProvider.getProductByCity(city);
            return new BaseResponse<>(GET_PRODUCT_SUCCESS,getProductResLocation);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostProductRes> createProduct(@RequestBody PostProductReq postProductReq){
        try{
            PostProductRes postProductRes = productService.createProduct(postProductReq);
            return new BaseResponse<>(POST_PRODUCT_SUCCESS, postProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @PostMapping("")
//    public String createProduct(Model model, @RequestBody PostProductReq postProductReq){
//
//        PostProductRes postProductRes = productService.createProduct(postProductReq);
//
//    }

}
