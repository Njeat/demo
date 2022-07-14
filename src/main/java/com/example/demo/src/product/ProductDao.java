package com.example.demo.src.product;

import com.example.demo.src.location.model.GetLocationRes;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetProductLocationRes;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.productImg.ProductImgDao;
import com.example.demo.src.productImg.model.GetProductImgRes;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class ProductDao {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JdbcTemplate jdbcTemplate;
    private ProductImgDao productImgDao;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetProductRes> getProducts(int page, int amount) {

        String getProductsQuery =
                "select product.productId, location.city, user.phoneNum, user.profileImgUrl, user.userName, product.title, product.content, product.price, category.category " +
                "from product " +
                "left join user on user.userId = product.userId " +
                "left join category on category.categoryId = product.categoryId " +
                "left join location on user.userId = location.userId " +
                "limit 3 offset ?";

//        int limitValue = amount;
        int offsetValueParams = page * amount;
//        int[] pageParams = new int[]{ limitValue, offsetValue };

//        int limitParams = amount;
        String getProductImgQuery = "select productImg.productImgId, productImg.productImgUrl from productImg left join product on productImg.productId =product.productId where product.productId = 0";
        List<GetProductImgRes> getProductImgRes = this.jdbcTemplate.query(getProductImgQuery,
                (rs, rowNum) -> new GetProductImgRes(
                        rs.getInt("productImgId"),
                        rs.getString("productImgUrl")
                ));

        String getLocationQuery = "select distinct location.city from location left join user on user.userId = location.userId left join product on product.userId = user.userId where location.city=''";

        List<GetLocationRes> getLocationRes = this.jdbcTemplate.query(getLocationQuery,
                (rs, rowNum) -> new GetLocationRes(
                        rs.getString("city")
                ));

        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductRes(
                        getProductImgRes,
                        rs.getInt("productId"),
                        getLocationRes,
                        rs.getString("profileImgUrl"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("price"),
                        rs.getString("category")),
                offsetValueParams);
//        );

    }

    public GetProductRes getProductById(int productId) {
        String getProductByIdQuery =
                "select product.productId, location.city, user.profileImgUrl, user.userName, product.title, product.content, product.price, category.category from product left join user on user.userId = product.userId left join category on category.categoryId = product.categoryId left join location on location.userId = user.userId where product.productId=?";
        int getProductByIdParams = productId;

        String getProductImgQuery =
                "select productImg.productImgId, productImg.productImgUrl " +
                        "from productImg " +
                        "left join product on productImg.productId = product.productId " +
                        "where productImg.productId = ?";
        List<GetProductImgRes> getProductImgRes = this.jdbcTemplate.query(getProductImgQuery,
                (rs, rowNum) -> new GetProductImgRes(
                        rs.getInt("productImgId"),
                        rs.getString("productImgUrl")),
                productId);

        String getLocationQuery =
                "select distinct location.city " +
                "from location" +
                        " left join user on user.userId = location.userId " +
                        "left join product on product.userId = user.userId " +
                        "where location.city=''";

        List<GetLocationRes> getLocationRes = this.jdbcTemplate.query(getLocationQuery,
                (rs, rowNum) -> new GetLocationRes(
                        rs.getString("city")
                ));

        return this.jdbcTemplate.queryForObject(getProductByIdQuery,
                (rs, rowNum) -> new GetProductRes(
                        getProductImgRes,
                        rs.getInt("productId"),
                        getLocationRes,
                        rs.getString("profileImgUrl"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("price"),
                        rs.getString("category")),
                getProductByIdParams);

    }

    public List<GetProductLocationRes> getProductByCity(String city) {
        String getProductByCityQuery = "select product.productId, location.city, user.profileImgUrl, user.userName, product.title, product.content, product.price, category.category from product left join user on user.userId = product.userId left join category on category.categoryId = product.categoryId left join location on user.userId = location.userId where location.city = ?";
        String getProductImgQuery = "select productImg.productImgId, productImg.productImgUrl from productImg left join product on productImg.productId = product.productId where productImg.productId = 0";
        String getProductByCityParams = city;


        List<GetProductImgRes> getProductImgRes = this.jdbcTemplate.query(getProductImgQuery,
                (rs, rowNum) -> new GetProductImgRes(
                        rs.getInt("productImgId"),
                        rs.getString("productImgUrl")
                ));

        String getLocationQuery = "select DISTINCT location.city from location left join user on user.userId = location.userId left join product on product.userId = user.userId where location.city=?";

        GetLocationRes getLocationRes = this.jdbcTemplate.queryForObject(getLocationQuery,
                (rs, rowNum) -> new GetLocationRes(
                        rs.getString("city")
                ), getProductByCityParams);
        log.info(getLocationRes.toString());

        return this.jdbcTemplate.query(getProductByCityQuery,
                (rs, rowNum) -> new GetProductLocationRes(
                        getProductImgRes,
                        rs.getInt("productId"),
                        getLocationRes,
                        rs.getString("profileImgUrl"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("price"),
                        rs.getString("category")),
                getProductByCityParams);

    }

    public int createProduct(PostProductReq postProductReq) {
        String createProductQuery = "insert into product (title, content, price, userId, categoryId) VALUES (?,?,?,?,?)";
        Object[] createProductParams = new Object[]{postProductReq.getTitle(), postProductReq.getContent(), postProductReq.getPrice(), postProductReq.getUserId(), postProductReq.getCategoryId()};
        this.jdbcTemplate.update(createProductQuery, createProductParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public int getProductLastId(){
        String getLastProductIdQuery = "select max(productId) from product";
        return this.jdbcTemplate.queryForObject(getLastProductIdQuery, int.class);
    }
}
