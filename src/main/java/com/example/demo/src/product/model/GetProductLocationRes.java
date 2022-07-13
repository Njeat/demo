package com.example.demo.src.product.model;

import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.productImg.model.GetProductImgRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetProductLocationRes {
    private List<GetProductImgRes> productImgUrl;
    private int productId;
    private GetLocationRes city;
    private String profileImgUrl;
    private String userName;
    private String title;
    private String content;
    private int price;
    private String category;
}
