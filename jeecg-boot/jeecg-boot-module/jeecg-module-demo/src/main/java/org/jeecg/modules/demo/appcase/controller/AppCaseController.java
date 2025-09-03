package org.jeecg.modules.demo.appcase.controller;

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
import org.jeecg.modules.demo.appcase.entity.AppCase;
import org.jeecg.modules.demo.appcase.service.IAppCaseService;

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
 * @Description: 应用案例
 * @Author: jeecg-boot
 * @Date:   2025-09-02
 * @Version: V1.0
 */
@Tag(name="应用案例")
@RestController
@RequestMapping("/appcase/appCase")
@Slf4j
public class AppCaseController extends JeecgController<AppCase, IAppCaseService> {
	@Autowired
	private IAppCaseService appCaseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appCase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "应用案例-分页列表查询")
	@Operation(summary="应用案例-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AppCase>> queryPageList(AppCase appCase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        // 自定义查询规则
        Map<String, QueryRuleEnum> customeRuleMap = new HashMap<>();
        // 自定义多选的查询规则为：LIKE_WITH_OR
        customeRuleMap.put("caseType", QueryRuleEnum.LIKE_WITH_OR);
        QueryWrapper<AppCase> queryWrapper = QueryGenerator.initQueryWrapper(appCase, req.getParameterMap(),customeRuleMap);
		Page<AppCase> page = new Page<AppCase>(pageNo, pageSize);
		IPage<AppCase> pageList = appCaseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param appCase
	 * @return
	 */
	@AutoLog(value = "应用案例-添加")
	@Operation(summary="应用案例-添加")
	@RequiresPermissions("appcase:app_case:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AppCase appCase) {
		appCaseService.save(appCase);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param appCase
	 * @return
	 */
	@AutoLog(value = "应用案例-编辑")
	@Operation(summary="应用案例-编辑")
	@RequiresPermissions("appcase:app_case:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AppCase appCase) {
		appCaseService.updateById(appCase);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "应用案例-通过id删除")
	@Operation(summary="应用案例-通过id删除")
	@RequiresPermissions("appcase:app_case:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		appCaseService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "应用案例-批量删除")
	@Operation(summary="应用案例-批量删除")
	@RequiresPermissions("appcase:app_case:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appCaseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "应用案例-通过id查询")
	@Operation(summary="应用案例-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AppCase> queryById(@RequestParam(name="id",required=true) String id) {
		AppCase appCase = appCaseService.getById(id);
		if(appCase==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(appCase);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param appCase
    */
    @RequiresPermissions("appcase:app_case:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AppCase appCase) {
        return super.exportXls(request, appCase, AppCase.class, "应用案例");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("appcase:app_case:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AppCase.class);
    }

}
