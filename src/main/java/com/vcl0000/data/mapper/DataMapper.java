package com.vcl0000.data.mapper;

import com.vcl0000.data.model.User;
import com.vcl0000.data.model.UserAndInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by vcl0000 on 17-7-25.
 */
@Repository
public interface DataMapper {

    public List findUserAll();

    public Map findUserById(@Param("userId") String userID);

    @Select("SELECT USER_NAME AS userName FROM test.user WHERE USER_ID=#{USER_ID}")
    public Map findUserNameByUserId(@Param("USER_ID") String userId);

    public User findOneUser();

    public User findOneUserObject();

    public UserAndInfo findUserAndInfo(User user);
}
