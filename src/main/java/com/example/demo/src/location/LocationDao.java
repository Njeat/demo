package com.example.demo.src.location;

import com.example.demo.src.location.model.PostLocationReq;
import com.example.demo.src.productImg.ProductImgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LocationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String pickLocation(int userId, String city){
        String pickLocationQuery = "insert into location (userId, city) VALUES (?,?)";
        Object[] createProductParams = new Object[]{userId, city};
        this.jdbcTemplate.update(pickLocationQuery, createProductParams);

        return city;
//        String lastInsertIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
}
