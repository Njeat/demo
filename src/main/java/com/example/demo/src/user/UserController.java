package com.example.demo.src.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(GET_ALL_USER_SUCCESS,getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(GET_ALL_USER_SUCCESS,getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @ResponseBody
//    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
//    public String getUsers(@RequestParam(required = false) String Email, Model model) {
//        List<GetUserRes> getUsersRes = userProvider.getUsers();
//        model.addAttribute("members", getUsersRes);
//        return "userList";
//
//        // Get Users
////        List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
////        return "userList";
//    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userId
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/:userId
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        // Get Users
        try{
            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(GET_USER_SUCCESS,getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//        if(postUserReq.getEmail() == null){
//            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
//        }
        //이메일 정규표현
//        if(!isRegexEmail(postUserReq.getEmail())){
//            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
//        }
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(SIGNUP_SUCCESS, postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 로그인 API
     * [POST] /users/logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){

        try{
            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
            logger.info("ㅍㅗㄴ넘버: " + postLoginReq.getPhoneNum());
            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
            return new BaseResponse<>(SIGNIN_SUCCESS,postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userId}")
    public BaseResponse<String> modifyUserName(@PathVariable("userId") int userId, @RequestBody User user){
        logger.info("qkrxowns::: " + user.getUserName());
        try {
            //jwt에서 idx 추출.
            int userIdByJwt = jwtService.getUserId();
            System.out.println("result : " + userIdByJwt);
            //userId와 접근한 유저가 같은지 확인
            if(userId != userIdByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //같다면 유저네임 변경
            PatchUserReq patchUserReq = new PatchUserReq(userId,user.getUserName());
            userService.modifyUserName(patchUserReq);

            String result = "";
        return new BaseResponse<>(MODIFY_SUCCESS,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/test/get")
    public String testUser() throws BaseException {
        int userIdByJwt = jwtService.getUserId();
        System.out.println("qkrxowns::::" + userIdByJwt);
        return "hello";
    }


}
