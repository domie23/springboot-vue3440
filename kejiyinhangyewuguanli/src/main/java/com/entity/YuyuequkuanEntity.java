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
 * 预约取款
 *
 * @author 
 * @email
 */
@TableName("yuyuequkuan")
public class YuyuequkuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YuyuequkuanEntity() {

	}

	public YuyuequkuanEntity(T t) {
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
     * 预约编号
     */
    @TableField(value = "yuyuequkuan_uuid_number")

    private String yuyuequkuanUuidNumber;


    /**
     * 取款金额
     */
    @TableField(value = "yuyuequkuan_money")

    private Double yuyuequkuanMoney;


    /**
     * 取款详情
     */
    @TableField(value = "yuyuequkuan_content")

    private String yuyuequkuanContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "yuyuequkuan_time")

    private Date yuyuequkuanTime;


    /**
     * 审核状态
     */
    @TableField(value = "yuyuequkuan_yesno_types")

    private Integer yuyuequkuanYesnoTypes;


    /**
     * 审核意见
     */
    @TableField(value = "yuyuequkuan_yesno_text")

    private String yuyuequkuanYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "yuyuequkuan_shenhe_time")

    private Date yuyuequkuanShenheTime;


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
	 * 设置：预约编号
	 */
    public String getYuyuequkuanUuidNumber() {
        return yuyuequkuanUuidNumber;
    }
    /**
	 * 获取：预约编号
	 */

    public void setYuyuequkuanUuidNumber(String yuyuequkuanUuidNumber) {
        this.yuyuequkuanUuidNumber = yuyuequkuanUuidNumber;
    }
    /**
	 * 设置：取款金额
	 */
    public Double getYuyuequkuanMoney() {
        return yuyuequkuanMoney;
    }
    /**
	 * 获取：取款金额
	 */

    public void setYuyuequkuanMoney(Double yuyuequkuanMoney) {
        this.yuyuequkuanMoney = yuyuequkuanMoney;
    }
    /**
	 * 设置：取款详情
	 */
    public String getYuyuequkuanContent() {
        return yuyuequkuanContent;
    }
    /**
	 * 获取：取款详情
	 */

    public void setYuyuequkuanContent(String yuyuequkuanContent) {
        this.yuyuequkuanContent = yuyuequkuanContent;
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
	 * 设置：预约日期
	 */
    public Date getYuyuequkuanTime() {
        return yuyuequkuanTime;
    }
    /**
	 * 获取：预约日期
	 */

    public void setYuyuequkuanTime(Date yuyuequkuanTime) {
        this.yuyuequkuanTime = yuyuequkuanTime;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getYuyuequkuanYesnoTypes() {
        return yuyuequkuanYesnoTypes;
    }
    /**
	 * 获取：审核状态
	 */

    public void setYuyuequkuanYesnoTypes(Integer yuyuequkuanYesnoTypes) {
        this.yuyuequkuanYesnoTypes = yuyuequkuanYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getYuyuequkuanYesnoText() {
        return yuyuequkuanYesnoText;
    }
    /**
	 * 获取：审核意见
	 */

    public void setYuyuequkuanYesnoText(String yuyuequkuanYesnoText) {
        this.yuyuequkuanYesnoText = yuyuequkuanYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getYuyuequkuanShenheTime() {
        return yuyuequkuanShenheTime;
    }
    /**
	 * 获取：审核时间
	 */

    public void setYuyuequkuanShenheTime(Date yuyuequkuanShenheTime) {
        this.yuyuequkuanShenheTime = yuyuequkuanShenheTime;
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
        return "Yuyuequkuan{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", yinhangkaId=" + yinhangkaId +
            ", yuyuequkuanUuidNumber=" + yuyuequkuanUuidNumber +
            ", yuyuequkuanMoney=" + yuyuequkuanMoney +
            ", yuyuequkuanContent=" + yuyuequkuanContent +
            ", insertTime=" + insertTime +
            ", yuyuequkuanTime=" + yuyuequkuanTime +
            ", yuyuequkuanYesnoTypes=" + yuyuequkuanYesnoTypes +
            ", yuyuequkuanYesnoText=" + yuyuequkuanYesnoText +
            ", yuyuequkuanShenheTime=" + yuyuequkuanShenheTime +
            ", createTime=" + createTime +
        "}";
    }
}
