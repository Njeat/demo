package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.src.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CategoryProvider categoryProvider;
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryProvider categoryProvider, CategoryService categoryService){
        this.categoryProvider = categoryProvider;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetCategoryRes>> getCategories(){
        try{
            List<GetCategoryRes> getCategoriesRes = categoryProvider.getCategories();
            return new BaseResponse<>(GET_ALL_CATEGORY_SUCCESS,getCategoriesRes);
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{categoryId}")
    public BaseResponse<GetCategoryRes> getCategory(@PathVariable int categoryId){
        try{
            GetCategoryRes getCategoryRes = categoryProvider.getCategory(categoryId);
            return new BaseResponse<>(GET_CATEGORY_SUCCESS,getCategoryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
