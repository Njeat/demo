package com.example.demo.src.productImg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductImg {
    private int productImgId;
    private int productId;
    private String productImgUrl;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
