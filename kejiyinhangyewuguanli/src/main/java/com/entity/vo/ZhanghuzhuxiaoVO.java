package com.entity.vo;

import com.entity.ZhanghuzhuxiaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 账户注销
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhanghuzhuxiao")
public class ZhanghuzhuxiaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 注销编号
     */

    @TableField(value = "zhanghuzhuxiao_uuid_number")
    private String zhanghuzhuxiaoUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 注销原因
     */

    @TableField(value = "zhanghuzhuxiao_content")
    private String zhanghuzhuxiaoContent;


    /**
     * 审核状态
     */

    @TableField(value = "zhanghuzhuxiao_yesno_types")
    private Integer zhanghuzhuxiaoYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "zhanghuzhuxiao_yesno_text")
    private String zhanghuzhuxiaoYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "zhanghuzhuxiao_shenhe_time")
    private Date zhanghuzhuxiaoShenheTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：注销编号
	 */
    public String getZhanghuzhuxiaoUuidNumber() {
        return zhanghuzhuxiaoUuidNumber;
    }


    /**
	 * 获取：注销编号
	 */

    public void setZhanghuzhuxiaoUuidNumber(String zhanghuzhuxiaoUuidNumber) {
        this.zhanghuzhuxiaoUuidNumber = zhanghuzhuxiaoUuidNumber;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：注销原因
	 */
    public String getZhanghuzhuxiaoContent() {
        return zhanghuzhuxiaoContent;
    }


    /**
	 * 获取：注销原因
	 */

    public void setZhanghuzhuxiaoContent(String zhanghuzhuxiaoContent) {
        this.zhanghuzhuxiaoContent = zhanghuzhuxiaoContent;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getZhanghuzhuxiaoYesnoTypes() {
        return zhanghuzhuxiaoYesnoTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setZhanghuzhuxiaoYesnoTypes(Integer zhanghuzhuxiaoYesnoTypes) {
        this.zhanghuzhuxiaoYesnoTypes = zhanghuzhuxiaoYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getZhanghuzhuxiaoYesnoText() {
        return zhanghuzhuxiaoYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setZhanghuzhuxiaoYesnoText(String zhanghuzhuxiaoYesnoText) {
        this.zhanghuzhuxiaoYesnoText = zhanghuzhuxiaoYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getZhanghuzhuxiaoShenheTime() {
        return zhanghuzhuxiaoShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setZhanghuzhuxiaoShenheTime(Date zhanghuzhuxiaoShenheTime) {
        this.zhanghuzhuxiaoShenheTime = zhanghuzhuxiaoShenheTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
