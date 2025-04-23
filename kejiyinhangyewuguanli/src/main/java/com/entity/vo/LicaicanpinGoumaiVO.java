package com.entity.vo;

import com.entity.LicaicanpinGoumaiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 理财产品购买
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("licaicanpin_goumai")
public class LicaicanpinGoumaiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 理财产品
     */

    @TableField(value = "licaicanpin_id")
    private Integer licaicanpinId;


    /**
     * 理财产品购买编号
     */

    @TableField(value = "licaicanpin_goumai_uuid_number")
    private String licaicanpinGoumaiUuidNumber;


    /**
     * 购买份数
     */

    @TableField(value = "licaicanpin_goumai_fenshu")
    private Double licaicanpinGoumaiFenshu;


    /**
     * 花费总额
     */

    @TableField(value = "licaicanpin_goumai_huafei")
    private Double licaicanpinGoumaiHuafei;


    /**
     * 购买时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 审核状态
     */

    @TableField(value = "licaicanpin_goumai_yesno_types")
    private Integer licaicanpinGoumaiYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "licaicanpin_goumai_yesno_text")
    private String licaicanpinGoumaiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "licaicanpin_goumai_shenhe_time")
    private Date licaicanpinGoumaiShenheTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：理财产品
	 */
    public Integer getLicaicanpinId() {
        return licaicanpinId;
    }


    /**
	 * 获取：理财产品
	 */

    public void setLicaicanpinId(Integer licaicanpinId) {
        this.licaicanpinId = licaicanpinId;
    }
    /**
	 * 设置：理财产品购买编号
	 */
    public String getLicaicanpinGoumaiUuidNumber() {
        return licaicanpinGoumaiUuidNumber;
    }


    /**
	 * 获取：理财产品购买编号
	 */

    public void setLicaicanpinGoumaiUuidNumber(String licaicanpinGoumaiUuidNumber) {
        this.licaicanpinGoumaiUuidNumber = licaicanpinGoumaiUuidNumber;
    }
    /**
	 * 设置：购买份数
	 */
    public Double getLicaicanpinGoumaiFenshu() {
        return licaicanpinGoumaiFenshu;
    }


    /**
	 * 获取：购买份数
	 */

    public void setLicaicanpinGoumaiFenshu(Double licaicanpinGoumaiFenshu) {
        this.licaicanpinGoumaiFenshu = licaicanpinGoumaiFenshu;
    }
    /**
	 * 设置：花费总额
	 */
    public Double getLicaicanpinGoumaiHuafei() {
        return licaicanpinGoumaiHuafei;
    }


    /**
	 * 获取：花费总额
	 */

    public void setLicaicanpinGoumaiHuafei(Double licaicanpinGoumaiHuafei) {
        this.licaicanpinGoumaiHuafei = licaicanpinGoumaiHuafei;
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
    public Integer getLicaicanpinGoumaiYesnoTypes() {
        return licaicanpinGoumaiYesnoTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setLicaicanpinGoumaiYesnoTypes(Integer licaicanpinGoumaiYesnoTypes) {
        this.licaicanpinGoumaiYesnoTypes = licaicanpinGoumaiYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getLicaicanpinGoumaiYesnoText() {
        return licaicanpinGoumaiYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setLicaicanpinGoumaiYesnoText(String licaicanpinGoumaiYesnoText) {
        this.licaicanpinGoumaiYesnoText = licaicanpinGoumaiYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getLicaicanpinGoumaiShenheTime() {
        return licaicanpinGoumaiShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setLicaicanpinGoumaiShenheTime(Date licaicanpinGoumaiShenheTime) {
        this.licaicanpinGoumaiShenheTime = licaicanpinGoumaiShenheTime;
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

}
