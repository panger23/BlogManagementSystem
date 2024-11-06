package com.panger.controller;

import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Article;
import com.panger.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;

//    @GetMapping("/list")
//    //Article是公共模块的实体类
//    public List<Article> test(){
//        //查询数据库的所有数据
//        return articleService.list();
//    }

    @GetMapping("/hotArticleList")
    @ApiOperation("查询热门文章")
    public ResponseResult hotArticleList(){
        // 查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }
    @GetMapping("/articleList")
    @ApiOperation("分页查询文章")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }
    @GetMapping("/{id}")
    @ApiOperation("查询文章细节")
    public ResponseResult GetArticleById(@PathVariable("id") Long id){
        return articleService.GetArticleById(id);
    }
}
