package com.example.demo.src.category;

import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCategoryRes> getCategories(){
        String getCategoriesQuery = "select * from category";
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs,rowNum) -> new GetCategoryRes(
                        rs.getString("categoryImgUrl"),
                        rs.getString("category"))
        );
    }

    public GetCategoryRes getCategory(int categoryId){
        String getCategoryQuery = "select * from category where categoryId=?";
        int getCategoryParams = categoryId;
        return this.jdbcTemplate.queryForObject(getCategoryQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getString("categoryImgUrl"),
                        rs.getString("category")),
                getCategoryParams);
    }
}
