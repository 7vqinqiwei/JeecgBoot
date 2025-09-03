package org.jeecg.modules.demo.appbook.controller;

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

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.appbook.entity.AppBook;
import org.jeecg.modules.demo.appbook.service.IAppBookService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.service.ISysAnnouncementService;
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
 * @Description: 预约表
 * @Author: jeecg-boot
 * @Date:   2025-09-01
 * @Version: V1.0
 */
@Tag(name="预约表")
@RestController
@RequestMapping("/appbook/appBook")
@Slf4j
public class AppBookController extends JeecgController<AppBook, IAppBookService> {
	@Autowired
	private IAppBookService appBookService;

	 @Autowired
	 private ISysAnnouncementService sysAnnouncementService;

	/**
	 * 分页列表查询
	 *
	 * @param appBook
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "预约表-分页列表查询")
	@Operation(summary="预约表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppBook>> queryPageList(AppBook appBook,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<AppBook> queryWrapper = QueryGenerator.initQueryWrapper(appBook, req.getParameterMap());
		Page<AppBook> page = new Page<AppBook>(pageNo, pageSize);
		IPage<AppBook> pageList = appBookService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param appBook
	 * @return
	 */
	@AutoLog(value = "预约表-添加")
	@Operation(summary="预约表-添加")
	@RequiresPermissions("appbook:app_book:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppBook appBook) {
		// 查询是否已存在
		String phone = appBook.getPhone();
		if (StrUtil.isNotBlank(phone)) {
			// 是否是手机格式
			if (!PhoneUtil.isPhone(phone)) {
				return Result.error("手机号格式不正确！");
			}
			QueryWrapper<AppBook> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("phone", phone);
			Long count = appBookService.count(queryWrapper);
			if (count != null && count > 0) {
				return Result.error("该手机号已预约，请勿重复预约！");
			}
		}else {
			return Result.error("手机号不能为空！");
		}
		appBookService.save(appBook);
		// 添加系统公共
		sysAnnouncementService.saveSysAnnouncement("预约通知", "用户"+ ReUtil.replaceAll(phone, "(\\d{3})\\d{4}(\\d{4})", "$1****$2")+"提交了预约信息，请及时处理！");
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param appBook
	 * @return
	 */
	@AutoLog(value = "预约表-编辑")
	@Operation(summary="预约表-编辑")
	@RequiresPermissions("appbook:app_book:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppBook appBook) {
		appBookService.updateById(appBook);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "预约表-通过id删除")
	@Operation(summary="预约表-通过id删除")
	@RequiresPermissions("appbook:app_book:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appBookService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "预约表-批量删除")
	@Operation(summary="预约表-批量删除")
	@RequiresPermissions("appbook:app_book:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appBookService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "预约表-通过id查询")
	@Operation(summary="预约表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppBook> queryById(@RequestParam(name="id",required=true) String id) {
		AppBook appBook = appBookService.getById(id);
		if(appBook==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appBook);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appBook
    */
    @RequiresPermissions("appbook:app_book:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppBook appBook) {
        return super.exportXls(request, appBook, AppBook.class, "预约表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("appbook:app_book:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppBook.class);
    }

}
