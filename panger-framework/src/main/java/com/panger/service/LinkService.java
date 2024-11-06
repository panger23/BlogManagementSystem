package com.panger.service;

import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author panger
 * @since 2024-11-04
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
