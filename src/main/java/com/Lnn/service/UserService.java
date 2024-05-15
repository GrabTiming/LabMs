package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ResetPwdDTO;
import com.Lnn.domain.dto.SelectNameDto;
import com.Lnn.domain.dto.UserLoginDTO;
import com.Lnn.domain.dto.UserRegisterDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-04-17 19:49:40
 */
public interface UserService extends IService<User> {

    Result register(UserRegisterDTO userRegisterDTO);

    Result login(UserLoginDTO userLoginDTO);


    Result modifyPwd(UserLoginDTO userLoginDTO);

    Result selectByCategory(Integer category,Integer pageNum,Integer pageSize);

    Result selectByName(SelectNameDto selectNameDto);

    Result resetPwd(ResetPwdDTO resetPwdDTO);
}

