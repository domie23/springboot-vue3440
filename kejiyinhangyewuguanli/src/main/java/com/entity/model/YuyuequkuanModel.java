package com.entity.model;

import com.entity.YuyuequkuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 预约取款
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YuyuequkuanModel implements Serializable {
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
     * 预约编号
     */
    private String yuyuequkuanUuidNumber;


    /**
     * 取款金额
     */
    private Double yuyuequkuanMoney;


    /**
     * 取款详情
     */
    private String yuyuequkuanContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyuequkuanTime;


    /**
     * 审核状态
     */
    private Integer yuyuequkuanYesnoTypes;


    /**
     * 审核意见
     */
    private String yuyuequkuanYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyuequkuanShenheTime;


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
	 * 获取：预约编号
	 */
    public String getYuyuequkuanUuidNumber() {
        return yuyuequkuanUuidNumber;
    }


    /**
	 * 设置：预约编号
	 */
    public void setYuyuequkuanUuidNumber(String yuyuequkuanUuidNumber) {
        this.yuyuequkuanUuidNumber = yuyuequkuanUuidNumber;
    }
    /**
	 * 获取：取款金额
	 */
    public Double getYuyuequkuanMoney() {
        return yuyuequkuanMoney;
    }


    /**
	 * 设置：取款金额
	 */
    public void setYuyuequkuanMoney(Double yuyuequkuanMoney) {
        this.yuyuequkuanMoney = yuyuequkuanMoney;
    }
    /**
	 * 获取：取款详情
	 */
    public String getYuyuequkuanContent() {
        return yuyuequkuanContent;
    }


    /**
	 * 设置：取款详情
	 */
    public void setYuyuequkuanContent(String yuyuequkuanContent) {
        this.yuyuequkuanContent = yuyuequkuanContent;
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
	 * 获取：预约日期
	 */
    public Date getYuyuequkuanTime() {
        return yuyuequkuanTime;
    }


    /**
	 * 设置：预约日期
	 */
    public void setYuyuequkuanTime(Date yuyuequkuanTime) {
        this.yuyuequkuanTime = yuyuequkuanTime;
    }
    /**
	 * 获取：审核状态
	 */
    public Integer getYuyuequkuanYesnoTypes() {
        return yuyuequkuanYesnoTypes;
    }


    /**
	 * 设置：审核状态
	 */
    public void setYuyuequkuanYesnoTypes(Integer yuyuequkuanYesnoTypes) {
        this.yuyuequkuanYesnoTypes = yuyuequkuanYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getYuyuequkuanYesnoText() {
        return yuyuequkuanYesnoText;
    }


    /**
	 * 设置：审核意见
	 */
    public void setYuyuequkuanYesnoText(String yuyuequkuanYesnoText) {
        this.yuyuequkuanYesnoText = yuyuequkuanYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getYuyuequkuanShenheTime() {
        return yuyuequkuanShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setYuyuequkuanShenheTime(Date yuyuequkuanShenheTime) {
        this.yuyuequkuanShenheTime = yuyuequkuanShenheTime;
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
