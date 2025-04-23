package com.entity.model;

import com.entity.ZhanghuzhuxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 账户注销
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhanghuzhuxiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 注销编号
     */
    private String zhanghuzhuxiaoUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 注销原因
     */
    private String zhanghuzhuxiaoContent;


    /**
     * 审核状态
     */
    private Integer zhanghuzhuxiaoYesnoTypes;


    /**
     * 审核意见
     */
    private String zhanghuzhuxiaoYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhanghuzhuxiaoShenheTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：注销编号
	 */
    public String getZhanghuzhuxiaoUuidNumber() {
        return zhanghuzhuxiaoUuidNumber;
    }


    /**
	 * 设置：注销编号
	 */
    public void setZhanghuzhuxiaoUuidNumber(String zhanghuzhuxiaoUuidNumber) {
        this.zhanghuzhuxiaoUuidNumber = zhanghuzhuxiaoUuidNumber;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：注销原因
	 */
    public String getZhanghuzhuxiaoContent() {
        return zhanghuzhuxiaoContent;
    }


    /**
	 * 设置：注销原因
	 */
    public void setZhanghuzhuxiaoContent(String zhanghuzhuxiaoContent) {
        this.zhanghuzhuxiaoContent = zhanghuzhuxiaoContent;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getZhanghuzhuxiaoYesnoTypes() {
        return zhanghuzhuxiaoYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setZhanghuzhuxiaoYesnoTypes(Integer zhanghuzhuxiaoYesnoTypes) {
        this.zhanghuzhuxiaoYesnoTypes = zhanghuzhuxiaoYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getZhanghuzhuxiaoYesnoText() {
        return zhanghuzhuxiaoYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setZhanghuzhuxiaoYesnoText(String zhanghuzhuxiaoYesnoText) {
        this.zhanghuzhuxiaoYesnoText = zhanghuzhuxiaoYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getZhanghuzhuxiaoShenheTime() {
        return zhanghuzhuxiaoShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setZhanghuzhuxiaoShenheTime(Date zhanghuzhuxiaoShenheTime) {
        this.zhanghuzhuxiaoShenheTime = zhanghuzhuxiaoShenheTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
