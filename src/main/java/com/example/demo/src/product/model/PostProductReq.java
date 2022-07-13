package com.example.demo.src.product.model;

import com.example.demo.src.category.model.Category;
import com.example.demo.src.productImg.model.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostProductReq {
    private String title;
    private String content;
    private int price;
    private int userId;
    private int categoryId;
}
