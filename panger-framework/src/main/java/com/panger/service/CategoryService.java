package com.panger.service;

import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author panger
 * @since 2024-11-01
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
