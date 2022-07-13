package com.example.demo.src.productImg;

import com.example.demo.src.productImg.model.GetProductImgRes;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductImgDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetProductImgRes> getProductImg(int productId){
        String getProductImgQuery = "select * from productImg where productId = ?";
        int getProductImgParams = productId;
        List<GetProductImgRes> getProductImgRes = jdbcTemplate.query(getProductImgQuery,
                (rs,rowNum) -> new GetProductImgRes(
                        rs.getInt("productImgId"),
                        rs.getString("productImgUrl")),
                getProductImgParams
        );

        return getProductImgRes;
    }
}
