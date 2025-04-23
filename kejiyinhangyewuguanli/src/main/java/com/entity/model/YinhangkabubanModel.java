package com.entity.model;

import com.entity.YinhangkabubanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 银行卡补办
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YinhangkabubanModel implements Serializable {
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
     * 银行卡
     */
    private Integer yinhangkaId;


    /**
     * 补办编号
     */
    private String yinhangkabubanUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 补办原因
     */
    private String yinhangkabubanContent;


    /**
     * 审核状态
     */
    private Integer yinhangkabubanYesnoTypes;


    /**
     * 审核意见
     */
    private String yinhangkabubanYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yinhangkabubanShenheTime;


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
	 * 获取：银行卡
	 */
    public Integer getYinhangkaId() {
        return yinhangkaId;
    }


    /**
	 * 设置：银行卡
	 */
    public void setYinhangkaId(Integer yinhangkaId) {
        this.yinhangkaId = yinhangkaId;
    }
    /**
	 * 获取：补办编号
	 */
    public String getYinhangkabubanUuidNumber() {
        return yinhangkabubanUuidNumber;
    }


    /**
	 * 设置：补办编号
	 */
    public void setYinhangkabubanUuidNumber(String yinhangkabubanUuidNumber) {
        this.yinhangkabubanUuidNumber = yinhangkabubanUuidNumber;
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
	 * 获取：补办原因
	 */
    public String getYinhangkabubanContent() {
        return yinhangkabubanContent;
    }


    /**
	 * 设置：补办原因
	 */
    public void setYinhangkabubanContent(String yinhangkabubanContent) {
        this.yinhangkabubanContent = yinhangkabubanContent;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getYinhangkabubanYesnoTypes() {
        return yinhangkabubanYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setYinhangkabubanYesnoTypes(Integer yinhangkabubanYesnoTypes) {
        this.yinhangkabubanYesnoTypes = yinhangkabubanYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getYinhangkabubanYesnoText() {
        return yinhangkabubanYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setYinhangkabubanYesnoText(String yinhangkabubanYesnoText) {
        this.yinhangkabubanYesnoText = yinhangkabubanYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getYinhangkabubanShenheTime() {
        return yinhangkabubanShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setYinhangkabubanShenheTime(Date yinhangkabubanShenheTime) {
        this.yinhangkabubanShenheTime = yinhangkabubanShenheTime;
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
