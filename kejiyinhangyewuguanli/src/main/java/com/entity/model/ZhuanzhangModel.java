package com.entity.model;

import com.entity.ZhuanzhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 转账
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhuanzhangModel implements Serializable {
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
     * 审核人员
     */
    private Integer shenherenyuanId;


    /**
     * 转账流水号
     */
    private String zhuanzhangUuidNumber;


    /**
     * 收款方姓名
     */
    private String shoukuanfangName;


    /**
     * 收款方账户
     */
    private String shoukuanfangZhanghu;


    /**
     * 转账金额
     */
    private Double zhuanzhangMoney;


    /**
     * 转账备注
     */
    private String zhuanzhangContent;


    /**
     * 转账时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 转账状态
     */
    private Integer zhuanzhangTypes;


    /**
     * 审核状态
     */
    private Integer zhuanzhangYesnoTypes;


    /**
     * 审核意见
     */
    private String zhuanzhangYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhuanzhangShenheTime;


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
	 * 获取：审核人员
	 */
    public Integer getShenherenyuanId() {
        return shenherenyuanId;
    }


    /**
	 * 设置：审核人员
	 */
    public void setShenherenyuanId(Integer shenherenyuanId) {
        this.shenherenyuanId = shenherenyuanId;
    }
    /**
	 * 获取：转账流水号
	 */
    public String getZhuanzhangUuidNumber() {
        return zhuanzhangUuidNumber;
    }


    /**
	 * 设置：转账流水号
	 */
    public void setZhuanzhangUuidNumber(String zhuanzhangUuidNumber) {
        this.zhuanzhangUuidNumber = zhuanzhangUuidNumber;
    }
    /**
	 * 获取：收款方姓名
	 */
    public String getShoukuanfangName() {
        return shoukuanfangName;
    }


    /**
	 * 设置：收款方姓名
	 */
    public void setShoukuanfangName(String shoukuanfangName) {
        this.shoukuanfangName = shoukuanfangName;
    }
    /**
	 * 获取：收款方账户
	 */
    public String getShoukuanfangZhanghu() {
        return shoukuanfangZhanghu;
    }


    /**
	 * 设置：收款方账户
	 */
    public void setShoukuanfangZhanghu(String shoukuanfangZhanghu) {
        this.shoukuanfangZhanghu = shoukuanfangZhanghu;
    }
    /**
	 * 获取：转账金额
	 */
    public Double getZhuanzhangMoney() {
        return zhuanzhangMoney;
    }


    /**
	 * 设置：转账金额
	 */
    public void setZhuanzhangMoney(Double zhuanzhangMoney) {
        this.zhuanzhangMoney = zhuanzhangMoney;
    }
    /**
	 * 获取：转账备注
	 */
    public String getZhuanzhangContent() {
        return zhuanzhangContent;
    }


    /**
	 * 设置：转账备注
	 */
    public void setZhuanzhangContent(String zhuanzhangContent) {
        this.zhuanzhangContent = zhuanzhangContent;
    }
    /**
	 * 获取：转账时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：转账时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：转账状态
	 */
    public Integer getZhuanzhangTypes() {
        return zhuanzhangTypes;
    }


    /**
	 * 设置：转账状态
	 */
    public void setZhuanzhangTypes(Integer zhuanzhangTypes) {
        this.zhuanzhangTypes = zhuanzhangTypes;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getZhuanzhangYesnoTypes() {
        return zhuanzhangYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setZhuanzhangYesnoTypes(Integer zhuanzhangYesnoTypes) {
        this.zhuanzhangYesnoTypes = zhuanzhangYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getZhuanzhangYesnoText() {
        return zhuanzhangYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setZhuanzhangYesnoText(String zhuanzhangYesnoText) {
        this.zhuanzhangYesnoText = zhuanzhangYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getZhuanzhangShenheTime() {
        return zhuanzhangShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setZhuanzhangShenheTime(Date zhuanzhangShenheTime) {
        this.zhuanzhangShenheTime = zhuanzhangShenheTime;
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
