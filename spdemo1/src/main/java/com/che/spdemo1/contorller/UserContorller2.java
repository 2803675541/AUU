package com.che.spdemo1.contorller;

import com.che.spdemo1.Util.SaltUtil;
import com.che.spdemo1.domain.User;
import com.che.spdemo1.mapper.UserMapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
//请求路径
@Api( "登录注册接口")
@RequestMapping("user2")
@RestController
public class UserContorller2 {

    @Autowired
    private UserMapper userMapper;

    @PostMapping   ("login")

    public String logip(String username ,String password){

        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);

        if(StringUtils.isEmpty(username)){
            return "用户名不可以为空";

        }
        if(StringUtils.isEmpty(password)){
            return "密码不可以为空";
        }
        if (user!=null) {
            password = user.getPasswordSalt().concat(password);
            if (Objects.equals(user.getPassword(), password)) {
                return "登录成功";
            }
            return "登录失败密码错误";
        }else {
            return "用户名不存在";
        }
    }

    @PostMapping  ("register")
    public String register(String username ,String password){
        log.info("username:{}",username);
        log.info("password:{}",password);

        if(StringUtils.isEmpty(username)){
            return "用户名不可以为空";
        }
        if(StringUtils.isEmpty(password)){
            return "密码不可以为空";
        }
        User record = new User();
        record.setUsername(username);

        User user = userMapper.selectOne(record);
        if (user != null){
            return "用户已存在";
        }

        String sa = SaltUtil.salt();
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(sa.concat(password));
        user1.setPasswordSalt(sa);
        System.out.println(user1);
        int resultcount = userMapper.insertSelective(user1);


        if(resultcount !=0){
            return "注册成功";
        }else {
            return "注册失败";
        }

    }
}
