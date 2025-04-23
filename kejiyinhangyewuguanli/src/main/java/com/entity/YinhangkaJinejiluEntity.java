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
 * 银行卡金额记录
 *
 * @author 
 * @email
 */
@TableName("yinhangka_jinejilu")
public class YinhangkaJinejiluEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YinhangkaJinejiluEntity() {

	}

	public YinhangkaJinejiluEntity(T t) {
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
     * 银行卡
     */
    @TableField(value = "yinhangka_id")

    private Integer yinhangkaId;


    /**
     * 记录编号
     */
    @TableField(value = "yinhangka_jinejilu_uuid_number")

    private String yinhangkaJinejiluUuidNumber;


    /**
     * 记录类型
     */
    @TableField(value = "jilu_types")

    private Integer jiluTypes;


    /**
     * 记录金额
     */
    @TableField(value = "yinhangka_jinejilu_money")

    private Double yinhangkaJinejiluMoney;


    /**
     * 记录备注
     */
    @TableField(value = "yinhangka_jinejilu_content")

    private String yinhangkaJinejiluContent;


    /**
     * 记录时间
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
	 * 设置：银行卡
	 */
    public Integer getYinhangkaId() {
        return yinhangkaId;
    }
    /**
	 * 获取：银行卡
	 */

    public void setYinhangkaId(Integer yinhangkaId) {
        this.yinhangkaId = yinhangkaId;
    }
    /**
	 * 设置：记录编号
	 */
    public String getYinhangkaJinejiluUuidNumber() {
        return yinhangkaJinejiluUuidNumber;
    }
    /**
	 * 获取：记录编号
	 */

    public void setYinhangkaJinejiluUuidNumber(String yinhangkaJinejiluUuidNumber) {
        this.yinhangkaJinejiluUuidNumber = yinhangkaJinejiluUuidNumber;
    }
    /**
	 * 设置：记录类型
	 */
    public Integer getJiluTypes() {
        return jiluTypes;
    }
    /**
	 * 获取：记录类型
	 */

    public void setJiluTypes(Integer jiluTypes) {
        this.jiluTypes = jiluTypes;
    }
    /**
	 * 设置：记录金额
	 */
    public Double getYinhangkaJinejiluMoney() {
        return yinhangkaJinejiluMoney;
    }
    /**
	 * 获取：记录金额
	 */

    public void setYinhangkaJinejiluMoney(Double yinhangkaJinejiluMoney) {
        this.yinhangkaJinejiluMoney = yinhangkaJinejiluMoney;
    }
    /**
	 * 设置：记录备注
	 */
    public String getYinhangkaJinejiluContent() {
        return yinhangkaJinejiluContent;
    }
    /**
	 * 获取：记录备注
	 */

    public void setYinhangkaJinejiluContent(String yinhangkaJinejiluContent) {
        this.yinhangkaJinejiluContent = yinhangkaJinejiluContent;
    }
    /**
	 * 设置：记录时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：记录时间
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
        return "YinhangkaJinejilu{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yinhangkaId=" + yinhangkaId +
            ", yinhangkaJinejiluUuidNumber=" + yinhangkaJinejiluUuidNumber +
            ", jiluTypes=" + jiluTypes +
            ", yinhangkaJinejiluMoney=" + yinhangkaJinejiluMoney +
            ", yinhangkaJinejiluContent=" + yinhangkaJinejiluContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
