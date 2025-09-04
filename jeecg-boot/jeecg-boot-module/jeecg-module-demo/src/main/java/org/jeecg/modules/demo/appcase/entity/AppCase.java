package org.jeecg.modules.demo.appcase.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 应用案例
 * @Author: jeecg-boot
 * @Date:   2025-09-04
 * @Version: V1.0
 */
@Data
@TableName("app_case")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="应用案例")
public class AppCase implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;
	/**创建人*/
    @Schema(description = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private String sysOrgCode;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @Schema(description = "标题")
    private String title;
	/**案例类型*/
	@Excel(name = "案例类型", width = 15, dicCode = "caseType")
	@Dict(dicCode = "caseType")
    @Schema(description = "案例类型")
    private String caseType;
	/**项目单位*/
	@Excel(name = "项目单位", width = 15)
    @Schema(description = "项目单位")
    private String workUnit;
	/**Logo*/
	@Excel(name = "Logo", width = 15)
    @Schema(description = "Logo")
    private String logo;
	/**介绍描述*/
	@Excel(name = "介绍描述", width = 15)
    @Schema(description = "介绍描述")
    private String introduce;
	/**案例内容*/
	@Excel(name = "案例内容", width = 15)
    @Schema(description = "案例内容")
    private String content;
	/**痛点1*/
	@Excel(name = "痛点1", width = 15)
    @Schema(description = "痛点1")
    private String painPoint1;
	/**痛点2*/
	@Excel(name = "痛点2", width = 15)
    @Schema(description = "痛点2")
    private String painPoint2;
	/**痛点3*/
	@Excel(name = "痛点3", width = 15)
    @Schema(description = "痛点3")
    private String painPoint3;
	/**痛点4*/
	@Excel(name = "痛点4", width = 15)
    @Schema(description = "痛点4")
    private String painPoint4;
	/**项目图片*/
	@Excel(name = "项目图片", width = 15)
    @Schema(description = "项目图片")
    private String pic;
	/**方案一*/
	@Excel(name = "方案一", width = 15)
    @Schema(description = "方案一")
    private String scheme1;
	/**方案一描述*/
	@Excel(name = "方案一描述", width = 15)
    @Schema(description = "方案一描述")
    private String scheme1desc;
	/**方案二*/
	@Excel(name = "方案二", width = 15)
    @Schema(description = "方案二")
    private String scheme2;
	/**方案二描述*/
	@Excel(name = "方案二描述", width = 15)
    @Schema(description = "方案二描述")
    private String scheme2desc;
	/**方案三*/
	@Excel(name = "方案三", width = 15)
    @Schema(description = "方案三")
    private String scheme3;
	/**方案三描述*/
	@Excel(name = "方案三描述", width = 15)
    @Schema(description = "方案三描述")
    private String scheme3desc;
	/**方案四*/
	@Excel(name = "方案四", width = 15)
    @Schema(description = "方案四")
    private String scheme4;
	/**方案四描述*/
	@Excel(name = "方案四描述", width = 15)
    @Schema(description = "方案四描述")
    private String scheme4desc;
	/**方案配图上传*/
	@Excel(name = "方案配图上传", width = 15)
    @Schema(description = "方案配图上传")
    private String schemePic;
	/**上线效果1*/
	@Excel(name = "上线效果1", width = 15)
    @Schema(description = "上线效果1")
    private String onlineEffect1;
	/**上线效果1描述*/
	@Excel(name = "上线效果1描述", width = 15)
    @Schema(description = "上线效果1描述")
    private String onlineEffect1Desc;
	/**上线效果2*/
	@Excel(name = "上线效果2", width = 15)
    @Schema(description = "上线效果2")
    private String onlineEffect2;
	/**上校效果2描述*/
	@Excel(name = "上校效果2描述", width = 15)
    @Schema(description = "上校效果2描述")
    private String onlineEffect2Desc;
	/**上线效果3*/
	@Excel(name = "上线效果3", width = 15)
    @Schema(description = "上线效果3")
    private String onlineEffect3;
	/**上线效果3描述*/
	@Excel(name = "上线效果3描述", width = 15)
    @Schema(description = "上线效果3描述")
    private String onlineEffect3Desc;
	/**上线效果4*/
	@Excel(name = "上线效果4", width = 15)
    @Schema(description = "上线效果4")
    private String onlineEffect4;
	/**上线效果4描述*/
	@Excel(name = "上线效果4描述", width = 15)
    @Schema(description = "上线效果4描述")
    private String onlineEffect4Desc;
}
