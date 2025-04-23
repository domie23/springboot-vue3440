package com.entity.model;

import com.entity.YinhangkaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 银行卡
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YinhangkaModel implements Serializable {
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
     * 银行卡卡号
     */
    private String yinhangkaUuidNumber;


    /**
     * 开户地
     */
    private Integer kaihudiTypes;


    /**
     * 卡余额
     */
    private Double yinghangkaMoney;


    /**
     * 绑定手机号
     */
    private String yinhangkaPhone;


    /**
     * 绑定邮箱
     */
    private String yinhangkaEmail;


    /**
     * 开户时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date kaihuTime;


    /**
     * 银行卡备注
     */
    private String yinhangkaContent;


    /**
     * 添加时间
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
	 * 获取：银行卡卡号
	 */
    public String getYinhangkaUuidNumber() {
        return yinhangkaUuidNumber;
    }


    /**
	 * 设置：银行卡卡号
	 */
    public void setYinhangkaUuidNumber(String yinhangkaUuidNumber) {
        this.yinhangkaUuidNumber = yinhangkaUuidNumber;
    }
    /**
	 * 获取：开户地
	 */
    public Integer getKaihudiTypes() {
        return kaihudiTypes;
    }


    /**
	 * 设置：开户地
	 */
    public void setKaihudiTypes(Integer kaihudiTypes) {
        this.kaihudiTypes = kaihudiTypes;
    }
    /**
	 * 获取：卡余额
	 */
    public Double getYinghangkaMoney() {
        return yinghangkaMoney;
    }


    /**
	 * 设置：卡余额
	 */
    public void setYinghangkaMoney(Double yinghangkaMoney) {
        this.yinghangkaMoney = yinghangkaMoney;
    }
    /**
	 * 获取：绑定手机号
	 */
    public String getYinhangkaPhone() {
        return yinhangkaPhone;
    }


    /**
	 * 设置：绑定手机号
	 */
    public void setYinhangkaPhone(String yinhangkaPhone) {
        this.yinhangkaPhone = yinhangkaPhone;
    }
    /**
	 * 获取：绑定邮箱
	 */
    public String getYinhangkaEmail() {
        return yinhangkaEmail;
    }


    /**
	 * 设置：绑定邮箱
	 */
    public void setYinhangkaEmail(String yinhangkaEmail) {
        this.yinhangkaEmail = yinhangkaEmail;
    }
    /**
	 * 获取：开户时间
	 */
    public Date getKaihuTime() {
        return kaihuTime;
    }


    /**
	 * 设置：开户时间
	 */
    public void setKaihuTime(Date kaihuTime) {
        this.kaihuTime = kaihuTime;
    }
    /**
	 * 获取：银行卡备注
	 */
    public String getYinhangkaContent() {
        return yinhangkaContent;
    }


    /**
	 * 设置：银行卡备注
	 */
    public void setYinhangkaContent(String yinhangkaContent) {
        this.yinhangkaContent = yinhangkaContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
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
