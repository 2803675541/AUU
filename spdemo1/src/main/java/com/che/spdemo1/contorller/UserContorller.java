package com.che.spdemo1.contorller;

import com.che.spdemo1.Util.SaltUtil;
import com.che.spdemo1.domain.User;
import com.che.spdemo1.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Slf4j
//请求路径
@Api(tags = "登录注册测试接口")
@RequestMapping("user")
@RestController
@Validated
public class UserContorller {

    @Autowired
    private UserMapper userMapper;

    @PostMapping ("login")
    @ApiOperation(value = "登录模块" , notes = "此方法需要两个参数 账号和密码")
    public String login(
     @ApiParam(required = true, value = "账号") @NotNull(message = "账号不能为空")
     @Pattern(regexp = "^1[0-9]{10}", message = "账号格式不正确") @RequestParam String username,
     @ApiParam(required = true, value = "密码") @NotNull(message = "密码不能为空") @RequestParam String password){

        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);

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

    @PostMapping ("register")
    @ApiOperation(value = "注册模块" , notes = "此方法需要两个参数 账号和密码")
    public String register(
     @ApiParam(required = true, value = "账号") @NotNull(message = "账号不能为空")
     @Pattern(regexp = "^1[0-9]{10}", message = "账号格式不正确") @RequestParam String username,
     @ApiParam(required = true, value = "密码") @NotNull(message = "密码不能为空") @RequestParam String password){
        log.info("username:{}",username);
        log.info("password:{}",password);
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);
        if (user != null){
            return "用户已存在";
        }
        String sa = SaltUtil.salt();
        user = new User();
        user.setUsername(username);
        user.setPassword(sa.concat(password));
        user.setPasswordSalt(sa);
        System.out.println(user);
        int resultcount = userMapper.insertSelective(user);


        if(resultcount !=0){
            return "注册成功";
        }else {
            return "注册失败";
        }

    }
}
