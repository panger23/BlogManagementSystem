package com.panger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panger.domain.ResponseResult;
import com.panger.domain.entity.User;

public interface  BlogLoginService {

    ResponseResult login(User user);
}
