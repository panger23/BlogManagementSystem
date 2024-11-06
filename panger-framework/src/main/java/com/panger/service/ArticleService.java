package com.panger.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Article;

/**
 * @author 35238
 * @date 2023/7/18 0018 21:16
 */
public interface ArticleService extends IService<Article>{
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult GetArticleById(Long id);
}
