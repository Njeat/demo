package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String ipAddress;
    private String userName;
    private String phoneNum;
    private String profileImgUrl;
}
