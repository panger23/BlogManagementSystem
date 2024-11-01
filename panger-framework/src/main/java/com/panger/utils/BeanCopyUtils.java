package com.panger.utils;

import com.panger.domain.entity.Article;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象。
        V result = null;
        try {
            result = clazz.newInstance();
            // 实现属性拷贝
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回结果
        return result;
    }

    //2.集合的拷贝(在ArticleServiceImpl类里面会使用到)。第一个参数是要拷贝的集合，第二个参数是类的字节码对象
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return  list.stream()
                            .map(o -> copyBean(o, clazz))
                            .collect(Collectors.toList());
    }

}
