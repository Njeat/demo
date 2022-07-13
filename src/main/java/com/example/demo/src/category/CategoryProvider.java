package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.src.user.model.GetUserRes;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CategoryProvider {

    @Autowired
    private final CategoryDao categoryDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CategoryProvider(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    public List<GetCategoryRes> getCategories() throws BaseException {
        try{
            List<GetCategoryRes> getCategoriesRes = categoryDao.getCategories();
            return getCategoriesRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetCategoryRes getCategory(int categoryId) throws BaseException {
        try {
            GetCategoryRes getCategoryRes = categoryDao.getCategory(categoryId);
            return getCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
