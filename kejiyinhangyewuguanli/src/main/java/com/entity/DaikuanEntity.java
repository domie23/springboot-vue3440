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
 * 贷款
 *
 * @author 
 * @email
 */
@TableName("daikuan")
public class DaikuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaikuanEntity() {

	}

	public DaikuanEntity(T t) {
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
     * 贷款编号
     */
    @TableField(value = "daikuan_uuid_number")

    private String daikuanUuidNumber;


    /**
     * 贷款名称
     */
    @TableField(value = "daikuan_name")

    private String daikuanName;


    /**
     * 还款日期
     */
    @TableField(value = "daikuan_huankuan")

    private String daikuanHuankuan;


    /**
     * 贷款类型
     */
    @TableField(value = "daikuan_types")

    private Integer daikuanTypes;


    /**
     * 贷款总额
     */
    @TableField(value = "daikuan_jine")

    private Double daikuanJine;


    /**
     * 贷款月份
     */
    @TableField(value = "daikuan_yue")

    private Integer daikuanYue;


    /**
     * 每月还款
     */
    @TableField(value = "daikuan_meiyue_jine")

    private Double daikuanMeiyueJine;


    /**
     * 利率
     */
    @TableField(value = "daikuan_lilv")

    private String daikuanLilv;


    /**
     * 贷款介绍
     */
    @TableField(value = "daikuan_content")

    private String daikuanContent;


    /**
     * 发布时间
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
	 * 设置：贷款编号
	 */
    public String getDaikuanUuidNumber() {
        return daikuanUuidNumber;
    }
    /**
	 * 获取：贷款编号
	 */

    public void setDaikuanUuidNumber(String daikuanUuidNumber) {
        this.daikuanUuidNumber = daikuanUuidNumber;
    }
    /**
	 * 设置：贷款名称
	 */
    public String getDaikuanName() {
        return daikuanName;
    }
    /**
	 * 获取：贷款名称
	 */

    public void setDaikuanName(String daikuanName) {
        this.daikuanName = daikuanName;
    }
    /**
	 * 设置：还款日期
	 */
    public String getDaikuanHuankuan() {
        return daikuanHuankuan;
    }
    /**
	 * 获取：还款日期
	 */

    public void setDaikuanHuankuan(String daikuanHuankuan) {
        this.daikuanHuankuan = daikuanHuankuan;
    }
    /**
	 * 设置：贷款类型
	 */
    public Integer getDaikuanTypes() {
        return daikuanTypes;
    }
    /**
	 * 获取：贷款类型
	 */

    public void setDaikuanTypes(Integer daikuanTypes) {
        this.daikuanTypes = daikuanTypes;
    }
    /**
	 * 设置：贷款总额
	 */
    public Double getDaikuanJine() {
        return daikuanJine;
    }
    /**
	 * 获取：贷款总额
	 */

    public void setDaikuanJine(Double daikuanJine) {
        this.daikuanJine = daikuanJine;
    }
    /**
	 * 设置：贷款月份
	 */
    public Integer getDaikuanYue() {
        return daikuanYue;
    }
    /**
	 * 获取：贷款月份
	 */

    public void setDaikuanYue(Integer daikuanYue) {
        this.daikuanYue = daikuanYue;
    }
    /**
	 * 设置：每月还款
	 */
    public Double getDaikuanMeiyueJine() {
        return daikuanMeiyueJine;
    }
    /**
	 * 获取：每月还款
	 */

    public void setDaikuanMeiyueJine(Double daikuanMeiyueJine) {
        this.daikuanMeiyueJine = daikuanMeiyueJine;
    }
    /**
	 * 设置：利率
	 */
    public String getDaikuanLilv() {
        return daikuanLilv;
    }
    /**
	 * 获取：利率
	 */

    public void setDaikuanLilv(String daikuanLilv) {
        this.daikuanLilv = daikuanLilv;
    }
    /**
	 * 设置：贷款介绍
	 */
    public String getDaikuanContent() {
        return daikuanContent;
    }
    /**
	 * 获取：贷款介绍
	 */

    public void setDaikuanContent(String daikuanContent) {
        this.daikuanContent = daikuanContent;
    }
    /**
	 * 设置：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：发布时间
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
        return "Daikuan{" +
            "id=" + id +
            ", yewurenyuanId=" + yewurenyuanId +
            ", daikuanUuidNumber=" + daikuanUuidNumber +
            ", daikuanName=" + daikuanName +
            ", daikuanHuankuan=" + daikuanHuankuan +
            ", daikuanTypes=" + daikuanTypes +
            ", daikuanJine=" + daikuanJine +
            ", daikuanYue=" + daikuanYue +
            ", daikuanMeiyueJine=" + daikuanMeiyueJine +
            ", daikuanLilv=" + daikuanLilv +
            ", daikuanContent=" + daikuanContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
