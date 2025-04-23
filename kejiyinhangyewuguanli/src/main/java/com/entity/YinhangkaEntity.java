package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 银行卡
 *
 * @author 
 * @email
 */
@TableName("yinhangka")
public class YinhangkaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YinhangkaEntity() {

	}

	public YinhangkaEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 银行卡卡号
     */
    @TableField(value = "yinhangka_uuid_number")

    private String yinhangkaUuidNumber;


    /**
     * 开户地
     */
    @TableField(value = "kaihudi_types")

    private Integer kaihudiTypes;


    /**
     * 卡余额
     */
    @TableField(value = "yinghangka_money")

    private Double yinghangkaMoney;


    /**
     * 绑定手机号
     */
    @TableField(value = "yinhangka_phone")

    private String yinhangkaPhone;


    /**
     * 绑定邮箱
     */
    @TableField(value = "yinhangka_email")

    private String yinhangkaEmail;


    /**
     * 开户时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "kaihu_time")

    private Date kaihuTime;


    /**
     * 银行卡备注
     */
    @TableField(value = "yinhangka_content")

    private String yinhangkaContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：银行卡卡号
	 */
    public String getYinhangkaUuidNumber() {
        return yinhangkaUuidNumber;
    }
    /**
	 * 获取：银行卡卡号
	 */

    public void setYinhangkaUuidNumber(String yinhangkaUuidNumber) {
        this.yinhangkaUuidNumber = yinhangkaUuidNumber;
    }
    /**
	 * 设置：开户地
	 */
    public Integer getKaihudiTypes() {
        return kaihudiTypes;
    }
    /**
	 * 获取：开户地
	 */

    public void setKaihudiTypes(Integer kaihudiTypes) {
        this.kaihudiTypes = kaihudiTypes;
    }
    /**
	 * 设置：卡余额
	 */
    public Double getYinghangkaMoney() {
        return yinghangkaMoney;
    }
    /**
	 * 获取：卡余额
	 */

    public void setYinghangkaMoney(Double yinghangkaMoney) {
        this.yinghangkaMoney = yinghangkaMoney;
    }
    /**
	 * 设置：绑定手机号
	 */
    public String getYinhangkaPhone() {
        return yinhangkaPhone;
    }
    /**
	 * 获取：绑定手机号
	 */

    public void setYinhangkaPhone(String yinhangkaPhone) {
        this.yinhangkaPhone = yinhangkaPhone;
    }
    /**
	 * 设置：绑定邮箱
	 */
    public String getYinhangkaEmail() {
        return yinhangkaEmail;
    }
    /**
	 * 获取：绑定邮箱
	 */

    public void setYinhangkaEmail(String yinhangkaEmail) {
        this.yinhangkaEmail = yinhangkaEmail;
    }
    /**
	 * 设置：开户时间
	 */
    public Date getKaihuTime() {
        return kaihuTime;
    }
    /**
	 * 获取：开户时间
	 */

    public void setKaihuTime(Date kaihuTime) {
        this.kaihuTime = kaihuTime;
    }
    /**
	 * 设置：银行卡备注
	 */
    public String getYinhangkaContent() {
        return yinhangkaContent;
    }
    /**
	 * 获取：银行卡备注
	 */

    public void setYinhangkaContent(String yinhangkaContent) {
        this.yinhangkaContent = yinhangkaContent;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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

    @Override
    public String toString() {
        return "Yinhangka{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yinhangkaUuidNumber=" + yinhangkaUuidNumber +
            ", kaihudiTypes=" + kaihudiTypes +
            ", yinghangkaMoney=" + yinghangkaMoney +
            ", yinhangkaPhone=" + yinhangkaPhone +
            ", yinhangkaEmail=" + yinhangkaEmail +
            ", kaihuTime=" + kaihuTime +
            ", yinhangkaContent=" + yinhangkaContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
