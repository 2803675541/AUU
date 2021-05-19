package com.che.spdemo1.mapper;

import com.che.spdemo1.domain.User;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface UserMapper extends Mapper<User> {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Insert("insert into user(username ,password) values(#{username},#{password})")
    int saveUser(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where username = #{username}")
    User selectUser(@Param("username") String username);
}