package com.panger.controller;

import com.panger.domain.ResponseResult;
import com.panger.service.ArticleService;
import com.panger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    //注入公共模块的CategoryService接口
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        // 查询热门文章，封装成ResponseResult返回
        ResponseResult result = categoryService.getCategoryList();
        return result;
    }
}
