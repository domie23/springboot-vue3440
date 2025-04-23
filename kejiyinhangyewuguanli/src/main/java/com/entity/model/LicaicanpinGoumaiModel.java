package com.entity.model;

import com.entity.LicaicanpinGoumaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 理财产品购买
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LicaicanpinGoumaiModel implements Serializable {
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
     * 理财产品
     */
    private Integer licaicanpinId;


    /**
     * 理财产品购买编号
     */
    private String licaicanpinGoumaiUuidNumber;


    /**
     * 购买份数
     */
    private Double licaicanpinGoumaiFenshu;


    /**
     * 花费总额
     */
    private Double licaicanpinGoumaiHuafei;


    /**
     * 购买时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 审核状态
     */
    private Integer licaicanpinGoumaiYesnoTypes;


    /**
     * 审核意见
     */
    private String licaicanpinGoumaiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date licaicanpinGoumaiShenheTime;


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
	 * 获取：理财产品
	 */
    public Integer getLicaicanpinId() {
        return licaicanpinId;
    }


    /**
	 * 设置：理财产品
	 */
    public void setLicaicanpinId(Integer licaicanpinId) {
        this.licaicanpinId = licaicanpinId;
    }
    /**
	 * 获取：理财产品购买编号
	 */
    public String getLicaicanpinGoumaiUuidNumber() {
        return licaicanpinGoumaiUuidNumber;
    }


    /**
	 * 设置：理财产品购买编号
	 */
    public void setLicaicanpinGoumaiUuidNumber(String licaicanpinGoumaiUuidNumber) {
        this.licaicanpinGoumaiUuidNumber = licaicanpinGoumaiUuidNumber;
    }
    /**
	 * 获取：购买份数
	 */
    public Double getLicaicanpinGoumaiFenshu() {
        return licaicanpinGoumaiFenshu;
    }


    /**
	 * 设置：购买份数
	 */
    public void setLicaicanpinGoumaiFenshu(Double licaicanpinGoumaiFenshu) {
        this.licaicanpinGoumaiFenshu = licaicanpinGoumaiFenshu;
    }
    /**
	 * 获取：花费总额
	 */
    public Double getLicaicanpinGoumaiHuafei() {
        return licaicanpinGoumaiHuafei;
    }


    /**
	 * 设置：花费总额
	 */
    public void setLicaicanpinGoumaiHuafei(Double licaicanpinGoumaiHuafei) {
        this.licaicanpinGoumaiHuafei = licaicanpinGoumaiHuafei;
    }
    /**
	 * 获取：购买时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：购买时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getLicaicanpinGoumaiYesnoTypes() {
        return licaicanpinGoumaiYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setLicaicanpinGoumaiYesnoTypes(Integer licaicanpinGoumaiYesnoTypes) {
        this.licaicanpinGoumaiYesnoTypes = licaicanpinGoumaiYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getLicaicanpinGoumaiYesnoText() {
        return licaicanpinGoumaiYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setLicaicanpinGoumaiYesnoText(String licaicanpinGoumaiYesnoText) {
        this.licaicanpinGoumaiYesnoText = licaicanpinGoumaiYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getLicaicanpinGoumaiShenheTime() {
        return licaicanpinGoumaiShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setLicaicanpinGoumaiShenheTime(Date licaicanpinGoumaiShenheTime) {
        this.licaicanpinGoumaiShenheTime = licaicanpinGoumaiShenheTime;
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
