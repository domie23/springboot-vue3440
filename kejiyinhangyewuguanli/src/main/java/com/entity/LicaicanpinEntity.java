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
 * 理财产品
 *
 * @author 
 * @email
 */
@TableName("licaicanpin")
public class LicaicanpinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LicaicanpinEntity() {

	}

	public LicaicanpinEntity(T t) {
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
     * 业务人员
     */
    @TableField(value = "yewurenyuan_id")

    private Integer yewurenyuanId;


    /**
     * 理财产品编号
     */
    @TableField(value = "licaicanpin_uuid_number")

    private String licaicanpinUuidNumber;


    /**
     * 理财产品名称
     */
    @TableField(value = "licaicanpin_name")

    private String licaicanpinName;


    /**
     * 理财产品类型
     */
    @TableField(value = "licaicanpin_types")

    private Integer licaicanpinTypes;


    /**
     * 每份/元
     */
    @TableField(value = "licaicanpin_jine")

    private Double licaicanpinJine;


    /**
     * 剩余份数
     */
    @TableField(value = "licaicanpin_fenshu")

    private Integer licaicanpinFenshu;


    /**
     * 理财产品介绍
     */
    @TableField(value = "licaicanpin_content")

    private String licaicanpinContent;


    /**
     * 发布理财产品时间
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
	 * 设置：业务人员
	 */
    public Integer getYewurenyuanId() {
        return yewurenyuanId;
    }
    /**
	 * 获取：业务人员
	 */

    public void setYewurenyuanId(Integer yewurenyuanId) {
        this.yewurenyuanId = yewurenyuanId;
    }
    /**
	 * 设置：理财产品编号
	 */
    public String getLicaicanpinUuidNumber() {
        return licaicanpinUuidNumber;
    }
    /**
	 * 获取：理财产品编号
	 */

    public void setLicaicanpinUuidNumber(String licaicanpinUuidNumber) {
        this.licaicanpinUuidNumber = licaicanpinUuidNumber;
    }
    /**
	 * 设置：理财产品名称
	 */
    public String getLicaicanpinName() {
        return licaicanpinName;
    }
    /**
	 * 获取：理财产品名称
	 */

    public void setLicaicanpinName(String licaicanpinName) {
        this.licaicanpinName = licaicanpinName;
    }
    /**
	 * 设置：理财产品类型
	 */
    public Integer getLicaicanpinTypes() {
        return licaicanpinTypes;
    }
    /**
	 * 获取：理财产品类型
	 */

    public void setLicaicanpinTypes(Integer licaicanpinTypes) {
        this.licaicanpinTypes = licaicanpinTypes;
    }
    /**
	 * 设置：每份/元
	 */
    public Double getLicaicanpinJine() {
        return licaicanpinJine;
    }
    /**
	 * 获取：每份/元
	 */

    public void setLicaicanpinJine(Double licaicanpinJine) {
        this.licaicanpinJine = licaicanpinJine;
    }
    /**
	 * 设置：剩余份数
	 */
    public Integer getLicaicanpinFenshu() {
        return licaicanpinFenshu;
    }
    /**
	 * 获取：剩余份数
	 */

    public void setLicaicanpinFenshu(Integer licaicanpinFenshu) {
        this.licaicanpinFenshu = licaicanpinFenshu;
    }
    /**
	 * 设置：理财产品介绍
	 */
    public String getLicaicanpinContent() {
        return licaicanpinContent;
    }
    /**
	 * 获取：理财产品介绍
	 */

    public void setLicaicanpinContent(String licaicanpinContent) {
        this.licaicanpinContent = licaicanpinContent;
    }
    /**
	 * 设置：发布理财产品时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：发布理财产品时间
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
        return "Licaicanpin{" +
            "id=" + id +
            ", yewurenyuanId=" + yewurenyuanId +
            ", licaicanpinUuidNumber=" + licaicanpinUuidNumber +
            ", licaicanpinName=" + licaicanpinName +
            ", licaicanpinTypes=" + licaicanpinTypes +
            ", licaicanpinJine=" + licaicanpinJine +
            ", licaicanpinFenshu=" + licaicanpinFenshu +
            ", licaicanpinContent=" + licaicanpinContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
