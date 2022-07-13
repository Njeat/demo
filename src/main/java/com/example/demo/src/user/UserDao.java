package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select user.userId, location.city, user.userName, user.phoneNum, user.profileImgUrl, user.manner, user.createdAt, user.updatedAt from User left join location on user.userId = location.userId";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("city"),
                        rs.getString("userName"),
                        rs.getString("phoneNum"),
                        rs.getString("profileImgUrl"),
                        rs.getDouble("manner"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime())
                );
    }

    public List<GetUserRes> getUsersByEmail(String phoneNum){
        String getUsersByPhoneNumQuery = "select * from User where phoneNum =?";
        String getUsersByPhoneNumParams = phoneNum;
        return this.jdbcTemplate.query(getUsersByPhoneNumQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("city"),
                        rs.getString("userName"),
                        rs.getString("phoneNum"),
                        rs.getString("profileImgUrl"),
                        rs.getDouble("manner"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()),
                getUsersByPhoneNumParams);
    }

    public GetUserRes getUser(int userId){
        String getUserQuery = "select user.userId, location.city, user.userName, user.phoneNum, user.profileImgUrl, user.manner, user.createdAt, user.updatedAt from User left join location on user.userId = location.userId where user.userId = ?";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userId"),
                        rs.getString("city"),
                        rs.getString("userName"),
                        rs.getString("phoneNum"),
                        rs.getString("profileImgUrl"),
                        rs.getDouble("manner"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery =
                "insert into User (userName, profileImgUrl, phoneNum) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getProfileImgUrl(), postUserReq.getPhoneNum()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkPhoneNum(String phoneNum){
        String checkPhoneNumQuery = "select exists(select phoneNum from User where phoneNum = ?)";
        String checkPhoneNumParams = phoneNum;
        return this.jdbcTemplate.queryForObject(checkPhoneNumQuery,
                int.class,
                checkPhoneNumParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set userName = ? where userId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

//    public User getPwd(PostLoginReq postLoginReq){
//        String getPwdQuery = "select userIdx, password,email,userName,ID from UserInfo where ID = ?";
//        String getPwdParams = postLoginReq.getId();
//
//        return this.jdbcTemplate.queryForObject(getPwdQuery,
//                (rs,rowNum)-> new User(
//                        rs.getInt("userIdx"),
//                        rs.getString("ID"),
//                        rs.getString("userName"),
//                        rs.getString("password"),
//                        rs.getString("email")
//                ),
//                getPwdParams
//                );
//
//    }
    public User getUserId(PostLoginReq postLoginReq){
        String getUserIdQuery = "select userId, phoneNum, userName, profileImgUrl, manner, status from User where phoneNum = ?";
        String getUserIdParams = postLoginReq.getPhoneNum();

        return this.jdbcTemplate.queryForObject(getUserIdQuery,
                (rs, rowNum)-> new User(
                        rs.getInt("userId"),
                        rs.getString("ipAddress"),
                        rs.getString("city"),
                        rs.getString("userName"),
                        rs.getString("phoneNum"),
                        rs.getString("profileImgUrl"),
                        rs.getDouble("manner"),
                        rs.getString("status"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()
                ),
                getUserIdParams
                );
    }


}
