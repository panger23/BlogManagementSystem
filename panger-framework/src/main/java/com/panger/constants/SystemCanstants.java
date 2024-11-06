package com.panger.constants;


//字面值(代码中的固定值)处理，把字面值都在这里定义成常量
public class SystemCanstants {

    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    
    /**
     * 文章列表当前查询页数
     */
    public static final int ARTICLE_STATUS_CURRENT = 1;

    /**
     * 文章列表每页显示的数据条数
     */
    public static final int ARTICLE_STATUS_SIZE = 10;
    /**
     * 分类表的分类状态是正常状态
     */
    public static final String STATUS_NORMAL = "0";
    /**
     * 友链状态是审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 友链状态是审核未通过
     */
    public static final String LINK_STATUS_NOT_NORMAL = "1";
    /**
     * 友链状态是未审核
     */
    public static final String LINK_STATUS_NOT_CHECK = "2";
}