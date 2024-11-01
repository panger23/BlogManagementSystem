package com.panger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Article;
import com.panger.domain.entity.Category;
import com.panger.domain.vo.ArticleListVo;
import com.panger.domain.vo.HotArtivleVo;
import com.panger.domain.vo.PageVo;
import com.panger.mapper.ArticleMapper;
import com.panger.service.ArticleService;
import com.panger.service.CategoryService;
import com.panger.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.panger.constants.SystemCanstants.*;

/**
 * @author 35238
 * @date 2023/7/18 0018 21:19
 */
@Service
//ServiceImpl是mybatisPlus官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Autowired
    CategoryService categoryService;
    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章，封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        // 安装浏览量降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多查询10条消息
        Page<Article> page = new Page<>(ARTICLE_STATUS_CURRENT,ARTICLE_STATUS_SIZE);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        // bean拷贝
//        List<HotArtivleVo> artivleVos = new ArrayList<>();
//        for(Article article : articles){
//            HotArtivleVo vo = new HotArtivleVo();
//            BeanUtils.copyProperties(article,vo);
//            artivleVos.add(vo);
//        }
        // 一行搞定
        List<HotArtivleVo> artivleVos = BeanCopyUtils.copyBeanList(articles, HotArtivleVo.class);

        return ResponseResult.okResult(artivleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有categoryId，就查询指定分类下的文章, 如果没有categoryId，就查询所有文章
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        // 对istop进行降序排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        // 查询分类名称categoryName
        List<Article> articles = page.getRecords();
        // 方法1:articleID去查询artivleName进行设置
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
        // 方法2 ： stream流
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
