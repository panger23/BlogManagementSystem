package com.panger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.panger.constants.SystemCanstants;
import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Article;
import com.panger.domain.entity.Category;
import com.panger.domain.vo.CategoryVo;
import com.panger.domain.vo.PageVo;
import com.panger.mapper.CategoryMapper;
import com.panger.service.ArticleService;
import com.panger.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panger.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author panger
 * @since 2024-11-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    //ArticleService是我们在huanf-framework写的接口
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //要求查的是getStatus字段的值为1，注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        articleWrapper.eq(Article::getStatus, SystemCanstants.ARTICLE_STATUS_NORMAL);
        //查询文章列表，条件就是上一行的articleWrapper
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重。使用stream流来处理上一行得到的文章表集合
//        Set<Long> categoryIds = articleList.stream()
//                //下面那行可以优化为Lambda表达式+方法引用
//                .map(new Function<Article, Long>() {
//                    @Override
//                    public Long apply(Article article) {
//                        return article.getCategoryId();
//                    }
//                })
//                //把文章的分类id转换成Set集合
//                .collect(Collectors.toSet());
        Set<Long> categoryIds = articleList.stream()
                // map对元素进行加工而已
                .map(Article::getCategoryId) // 使用方法引用直接获取分类 ID
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        //注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        categories = categories.stream()
                .filter(category -> category.getStatus().equals(SystemCanstants.STATUS_NORMAL))
                .collect(Collectors.toList());

        //封装成CategoryVo实体类后返回给前端，CategoryVo的作用是只返回前端需要的字段。BeanCopyUtils是我们写的工具类
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
