package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.config.BaseResponseStatus.POST_PRODUCT_SUCCESS;

@Controller
public class PostController {

    private final PostService postService;
    private final PostProvider postProvider;

    public PostController(PostService postService, PostProvider postProvider){
        this.postProvider = postProvider;
        this.postService = postService;
    }
//    @ResponseBody
//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    public

    @RequestMapping(value = "/reqAjax1", method = RequestMethod.GET)
    public @ResponseBody String reqAjax1() {
        System.out.println("ajax 요청 도착!");
        return "cool";
    }

    @RequestMapping("/board")
    public String main(){
        return "test";
    }

    @ResponseBody
    @RequestMapping(value="/reqAjax2", method=RequestMethod.POST)
    public Map<String,String> reqAjax2(@RequestBody Map<String,String> map ) {
        System.out.println(map);
        return map;
    }

//    @ResponseBody
//    @RequestMapping("/reqAjax3")
//    public Reply reqAjax3(String name, String phone) {
//        Reply reply = new Reply();
//        reply.setBoardseq(1);
//        reply.setRegdate(new Date().toString());
//        reply.setReplytext("댓글입니다.");
//        reply.setUserid("hong");
//        return reply;
//    }
}
