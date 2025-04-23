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
 * 贷款购买
 *
 * @author 
 * @email
 */
@TableName("daikuan_goumai")
public class DaikuanGoumaiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaikuanGoumaiEntity() {

	}

	public DaikuanGoumaiEntity(T t) {
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
     * 贷款
     */
    @TableField(value = "daikuan_id")

    private Integer daikuanId;


    /**
     * 贷款购买编号
     */
    @TableField(value = "daikuan_goumai_uuid_number")

    private String daikuanGoumaiUuidNumber;


    /**
     * 购买时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 审核状态
     */
    @TableField(value = "daikuan_goumai_yesno_types")

    private Integer daikuanGoumaiYesnoTypes;


    /**
     * 审核意见
     */
    @TableField(value = "daikuan_goumai_yesno_text")

    private String daikuanGoumaiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "daikuan_goumai_shenhe_time")

    private Date daikuanGoumaiShenheTime;


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
	 * 设置：贷款
	 */
    public Integer getDaikuanId() {
        return daikuanId;
    }
    /**
	 * 获取：贷款
	 */

    public void setDaikuanId(Integer daikuanId) {
        this.daikuanId = daikuanId;
    }
    /**
	 * 设置：贷款购买编号
	 */
    public String getDaikuanGoumaiUuidNumber() {
        return daikuanGoumaiUuidNumber;
    }
    /**
	 * 获取：贷款购买编号
	 */

    public void setDaikuanGoumaiUuidNumber(String daikuanGoumaiUuidNumber) {
        this.daikuanGoumaiUuidNumber = daikuanGoumaiUuidNumber;
    }
    /**
	 * 设置：购买时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：购买时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getDaikuanGoumaiYesnoTypes() {
        return daikuanGoumaiYesnoTypes;
    }
    /**
	 * 获取：审核状态
	 */

    public void setDaikuanGoumaiYesnoTypes(Integer daikuanGoumaiYesnoTypes) {
        this.daikuanGoumaiYesnoTypes = daikuanGoumaiYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getDaikuanGoumaiYesnoText() {
        return daikuanGoumaiYesnoText;
    }
    /**
	 * 获取：审核意见
	 */

    public void setDaikuanGoumaiYesnoText(String daikuanGoumaiYesnoText) {
        this.daikuanGoumaiYesnoText = daikuanGoumaiYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getDaikuanGoumaiShenheTime() {
        return daikuanGoumaiShenheTime;
    }
    /**
	 * 获取：审核时间
	 */

    public void setDaikuanGoumaiShenheTime(Date daikuanGoumaiShenheTime) {
        this.daikuanGoumaiShenheTime = daikuanGoumaiShenheTime;
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
        return "DaikuanGoumai{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yinhangkaId=" + yinhangkaId +
            ", daikuanId=" + daikuanId +
            ", daikuanGoumaiUuidNumber=" + daikuanGoumaiUuidNumber +
            ", insertTime=" + insertTime +
            ", daikuanGoumaiYesnoTypes=" + daikuanGoumaiYesnoTypes +
            ", daikuanGoumaiYesnoText=" + daikuanGoumaiYesnoText +
            ", daikuanGoumaiShenheTime=" + daikuanGoumaiShenheTime +
            ", createTime=" + createTime +
        "}";
    }
}
