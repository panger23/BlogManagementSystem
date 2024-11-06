package com.panger.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.panger.constants.SystemCanstants;
import com.panger.domain.ResponseResult;
import com.panger.domain.entity.Link;
import com.panger.domain.vo.LinkVo;
import com.panger.mapper.LinkMapper;
import com.panger.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panger.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author panger
 * @since 2024-11-04
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemCanstants.LINK_STATUS_NORMAL);
        // 获取友链列表
        List<Link> links = list(queryWrapper);
        // 转换为vo
        List<LinkVo> linkVos =BeanCopyUtils.copyBeanList(links, LinkVo.class);
        // 封装返回
        return ResponseResult.okResult(linkVos);
    }
}
