package org.jeecg.modules.demo.appnews.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.appnews.entity.AppNews;
import org.jeecg.modules.demo.appnews.service.IAppNewsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
 /**
 * @Description: 新闻资讯
 * @Author: jeecg-boot
 * @Date:   2025-08-07
 * @Version: V1.0
 */
@Tag(name="新闻资讯")
@RestController
@RequestMapping("/appnews/appNews")
@Slf4j
public class AppNewsController extends JeecgController<AppNews, IAppNewsService> {
	@Autowired
	private IAppNewsService appNewsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appNews
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "新闻资讯-分页列表查询")
	@Operation(summary="新闻资讯-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppNews>> queryPageList(AppNews appNews,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<AppNews> queryWrapper = QueryGenerator.initQueryWrapper(appNews, req.getParameterMap());
		Page<AppNews> page = new Page<AppNews>(pageNo, pageSize);
		IPage<AppNews> pageList = appNewsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param appNews
	 * @return
	 */
	@AutoLog(value = "新闻资讯-添加")
	@Operation(summary="新闻资讯-添加")
	@RequiresPermissions("appnews:app_news:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppNews appNews) {
		appNewsService.save(appNews);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param appNews
	 * @return
	 */
	@AutoLog(value = "新闻资讯-编辑")
	@Operation(summary="新闻资讯-编辑")
	@RequiresPermissions("appnews:app_news:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppNews appNews) {
		appNewsService.updateById(appNews);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新闻资讯-通过id删除")
	@Operation(summary="新闻资讯-通过id删除")
	@RequiresPermissions("appnews:app_news:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appNewsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "新闻资讯-批量删除")
	@Operation(summary="新闻资讯-批量删除")
	@RequiresPermissions("appnews:app_news:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appNewsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "新闻资讯-通过id查询")
	@Operation(summary="新闻资讯-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppNews> queryById(@RequestParam(name="id",required=true) String id) {
		AppNews appNews = appNewsService.getById(id);
		if(appNews==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appNews);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appNews
    */
    @RequiresPermissions("appnews:app_news:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppNews appNews) {
        return super.exportXls(request, appNews, AppNews.class, "新闻资讯");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("appnews:app_news:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppNews.class);
    }

}
