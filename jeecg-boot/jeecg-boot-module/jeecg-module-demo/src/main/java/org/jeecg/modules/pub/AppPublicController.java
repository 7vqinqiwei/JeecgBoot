package org.jeecg.modules.pub;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.appbanner.entity.AppBanner;
import org.jeecg.modules.demo.appbanner.service.IAppBannerService;
import org.jeecg.modules.demo.appcase.entity.AppCase;
import org.jeecg.modules.demo.appcase.service.IAppCaseService;
import org.jeecg.modules.demo.appnews.entity.AppNews;
import org.jeecg.modules.demo.appnews.service.IAppNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述
 *
 * @author qi.wei
 * @date 2025-08-07 14:52
 */
@Tag(name="开放接口")
@RestController
@RequestMapping("/public")
@Slf4j
public class AppPublicController {

    @Autowired
    private IAppBannerService appBannerService;

    @Autowired
    private IAppCaseService appCaseService;

    @Autowired
    private IAppNewsService appNewsService;

    @Operation(summary="banner实体-分页列表查询")
    @GetMapping(value = "/appbanner/list")
    public Result<IPage<AppBanner>> queryPageList(AppBanner appBanner,
                                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                  HttpServletRequest req) {


        QueryWrapper<AppBanner> queryWrapper = QueryGenerator.initQueryWrapper(appBanner, req.getParameterMap());
        Page<AppBanner> page = new Page<>(pageNo, pageSize);
        IPage<AppBanner> pageList = appBannerService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "banner实体-通过id查询")
    @Operation(summary="banner实体-通过id查询")
    @GetMapping(value = "/appbanner/queryById")
    public Result<AppBanner> appBrannerQueryById(@RequestParam(name="id",required=true) String id) {
        AppBanner appBanner = appBannerService.getById(id);
        if(appBanner==null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(appBanner);
    }

    @Operation(summary="应用案例-分页列表查询")
    @GetMapping(value = "/appcase/list")
    public Result<IPage<AppCase>> queryPageList(AppCase appCase,
                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                HttpServletRequest req) {


        QueryWrapper<AppCase> queryWrapper = QueryGenerator.initQueryWrapper(appCase, req.getParameterMap());
        Page<AppCase> page = new Page<>(pageNo, pageSize);
        IPage<AppCase> pageList = appCaseService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "应用案例-通过id查询")
    @Operation(summary="应用案例-通过id查询")
    @GetMapping(value = "/appcase/queryById")
    public Result<AppCase> appCaseQueryById(@RequestParam(name="id",required=true) String id) {
        AppCase appCase = appCaseService.getById(id);
        if(appCase==null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(appCase);
    }
    /**
     * 分页列表查询
     *
     * @param appNews
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @Operation(summary="新闻资讯-分页列表查询")
    @GetMapping(value = "/appnews/list")
    public Result<IPage<AppNews>> queryPageList(AppNews appNews,
                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                HttpServletRequest req) {


        QueryWrapper<AppNews> queryWrapper = QueryGenerator.initQueryWrapper(appNews, req.getParameterMap());
        Page<AppNews> page = new Page<>(pageNo, pageSize);
        IPage<AppNews> pageList = appNewsService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    @Operation(summary="新闻资讯-通过id查询")
    @GetMapping(value = "appnews/queryById")
    public Result<AppNews> appNewsQueryById(@RequestParam(name="id",required=true) String id) {
        AppNews appNews = appNewsService.getById(id);
        if(appNews==null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(appNews);
    }


}
