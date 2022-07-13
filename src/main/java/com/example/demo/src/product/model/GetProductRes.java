package com.example.demo.src.product.model;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.Category;
import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.productImg.model.GetProductImgRes;
import com.example.demo.src.user.model.GetUserRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetProductRes {
    private List<GetProductImgRes> productImgUrl;
    private int productId;
    private List<GetLocationRes> city;
    private String profileImgUrl;
    private String userName;
    private String title;
    private String content;
    private int price;
    private String category;
}
