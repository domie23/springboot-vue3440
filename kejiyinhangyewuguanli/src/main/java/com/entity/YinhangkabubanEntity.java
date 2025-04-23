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
 * 银行卡补办
 *
 * @author 
 * @email
 */
@TableName("yinhangkabuban")
public class YinhangkabubanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YinhangkabubanEntity() {

	}

	public YinhangkabubanEntity(T t) {
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
     * 补办编号
     */
    @TableField(value = "yinhangkabuban_uuid_number")

    private String yinhangkabubanUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 补办原因
     */
    @TableField(value = "yinhangkabuban_content")

    private String yinhangkabubanContent;


    /**
     * 审核状态
     */
    @TableField(value = "yinhangkabuban_yesno_types")

    private Integer yinhangkabubanYesnoTypes;


    /**
     * 审核意见
     */
    @TableField(value = "yinhangkabuban_yesno_text")

    private String yinhangkabubanYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "yinhangkabuban_shenhe_time")

    private Date yinhangkabubanShenheTime;


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
	 * 设置：补办编号
	 */
    public String getYinhangkabubanUuidNumber() {
        return yinhangkabubanUuidNumber;
    }
    /**
	 * 获取：补办编号
	 */

    public void setYinhangkabubanUuidNumber(String yinhangkabubanUuidNumber) {
        this.yinhangkabubanUuidNumber = yinhangkabubanUuidNumber;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：补办原因
	 */
    public String getYinhangkabubanContent() {
        return yinhangkabubanContent;
    }
    /**
	 * 获取：补办原因
	 */

    public void setYinhangkabubanContent(String yinhangkabubanContent) {
        this.yinhangkabubanContent = yinhangkabubanContent;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getYinhangkabubanYesnoTypes() {
        return yinhangkabubanYesnoTypes;
    }
    /**
	 * 获取：审核状态
	 */

    public void setYinhangkabubanYesnoTypes(Integer yinhangkabubanYesnoTypes) {
        this.yinhangkabubanYesnoTypes = yinhangkabubanYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getYinhangkabubanYesnoText() {
        return yinhangkabubanYesnoText;
    }
    /**
	 * 获取：审核意见
	 */

    public void setYinhangkabubanYesnoText(String yinhangkabubanYesnoText) {
        this.yinhangkabubanYesnoText = yinhangkabubanYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getYinhangkabubanShenheTime() {
        return yinhangkabubanShenheTime;
    }
    /**
	 * 获取：审核时间
	 */

    public void setYinhangkabubanShenheTime(Date yinhangkabubanShenheTime) {
        this.yinhangkabubanShenheTime = yinhangkabubanShenheTime;
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
        return "Yinhangkabuban{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yinhangkaId=" + yinhangkaId +
            ", yinhangkabubanUuidNumber=" + yinhangkabubanUuidNumber +
            ", insertTime=" + insertTime +
            ", yinhangkabubanContent=" + yinhangkabubanContent +
            ", yinhangkabubanYesnoTypes=" + yinhangkabubanYesnoTypes +
            ", yinhangkabubanYesnoText=" + yinhangkabubanYesnoText +
            ", yinhangkabubanShenheTime=" + yinhangkabubanShenheTime +
            ", createTime=" + createTime +
        "}";
    }
}
