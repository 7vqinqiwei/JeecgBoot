package org.jeecg.modules.demo.appnews.service.impl;

import org.jeecg.modules.demo.appnews.entity.AppNews;
import org.jeecg.modules.demo.appnews.mapper.AppNewsMapper;
import org.jeecg.modules.demo.appnews.service.IAppNewsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 新闻资讯
 * @Author: jeecg-boot
 * @Date:   2025-08-12
 * @Version: V1.0
 */
@Service
public class AppNewsServiceImpl extends ServiceImpl<AppNewsMapper, AppNews> implements IAppNewsService {

}
