package com.entity.model;

import com.entity.YinhangkaJinejiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 银行卡金额记录
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YinhangkaJinejiluModel implements Serializable {
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
     * 记录编号
     */
    private String yinhangkaJinejiluUuidNumber;


    /**
     * 记录类型
     */
    private Integer jiluTypes;


    /**
     * 记录金额
     */
    private Double yinhangkaJinejiluMoney;


    /**
     * 记录备注
     */
    private String yinhangkaJinejiluContent;


    /**
     * 记录时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：记录编号
	 */
    public String getYinhangkaJinejiluUuidNumber() {
        return yinhangkaJinejiluUuidNumber;
    }


    /**
	 * 设置：记录编号
	 */
    public void setYinhangkaJinejiluUuidNumber(String yinhangkaJinejiluUuidNumber) {
        this.yinhangkaJinejiluUuidNumber = yinhangkaJinejiluUuidNumber;
    }
    /**
	 * 获取：记录类型
	 */
    public Integer getJiluTypes() {
        return jiluTypes;
    }


    /**
	 * 设置：记录类型
	 */
    public void setJiluTypes(Integer jiluTypes) {
        this.jiluTypes = jiluTypes;
    }
    /**
	 * 获取：记录金额
	 */
    public Double getYinhangkaJinejiluMoney() {
        return yinhangkaJinejiluMoney;
    }


    /**
	 * 设置：记录金额
	 */
    public void setYinhangkaJinejiluMoney(Double yinhangkaJinejiluMoney) {
        this.yinhangkaJinejiluMoney = yinhangkaJinejiluMoney;
    }
    /**
	 * 获取：记录备注
	 */
    public String getYinhangkaJinejiluContent() {
        return yinhangkaJinejiluContent;
    }


    /**
	 * 设置：记录备注
	 */
    public void setYinhangkaJinejiluContent(String yinhangkaJinejiluContent) {
        this.yinhangkaJinejiluContent = yinhangkaJinejiluContent;
    }
    /**
	 * 获取：记录时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：记录时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
