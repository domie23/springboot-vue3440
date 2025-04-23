package com.entity.model;

import com.entity.DaikuanGoumaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 贷款购买
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaikuanGoumaiModel implements Serializable {
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
     * 贷款
     */
    private Integer daikuanId;


    /**
     * 贷款购买编号
     */
    private String daikuanGoumaiUuidNumber;


    /**
     * 购买时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 审核状态
     */
    private Integer daikuanGoumaiYesnoTypes;


    /**
     * 审核意见
     */
    private String daikuanGoumaiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date daikuanGoumaiShenheTime;


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
	 * 获取：贷款
	 */
    public Integer getDaikuanId() {
        return daikuanId;
    }


    /**
	 * 设置：贷款
	 */
    public void setDaikuanId(Integer daikuanId) {
        this.daikuanId = daikuanId;
    }
    /**
	 * 获取：贷款购买编号
	 */
    public String getDaikuanGoumaiUuidNumber() {
        return daikuanGoumaiUuidNumber;
    }


    /**
	 * 设置：贷款购买编号
	 */
    public void setDaikuanGoumaiUuidNumber(String daikuanGoumaiUuidNumber) {
        this.daikuanGoumaiUuidNumber = daikuanGoumaiUuidNumber;
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
    public Integer getDaikuanGoumaiYesnoTypes() {
        return daikuanGoumaiYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setDaikuanGoumaiYesnoTypes(Integer daikuanGoumaiYesnoTypes) {
        this.daikuanGoumaiYesnoTypes = daikuanGoumaiYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getDaikuanGoumaiYesnoText() {
        return daikuanGoumaiYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setDaikuanGoumaiYesnoText(String daikuanGoumaiYesnoText) {
        this.daikuanGoumaiYesnoText = daikuanGoumaiYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getDaikuanGoumaiShenheTime() {
        return daikuanGoumaiShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setDaikuanGoumaiShenheTime(Date daikuanGoumaiShenheTime) {
        this.daikuanGoumaiShenheTime = daikuanGoumaiShenheTime;
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
