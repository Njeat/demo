package com.example.demo.src.product.model;

import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private int productId;
    private int userId;
    private String title;
    private String content;
    private int price;
    private int categoryId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
