package com.entity.model;

import com.entity.LicaicanpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 理财产品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LicaicanpinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 业务人员
     */
    private Integer yewurenyuanId;


    /**
     * 理财产品编号
     */
    private String licaicanpinUuidNumber;


    /**
     * 理财产品名称
     */
    private String licaicanpinName;


    /**
     * 理财产品类型
     */
    private Integer licaicanpinTypes;


    /**
     * 每份/元
     */
    private Double licaicanpinJine;


    /**
     * 剩余份数
     */
    private Integer licaicanpinFenshu;


    /**
     * 理财产品介绍
     */
    private String licaicanpinContent;


    /**
     * 发布理财产品时间
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
	 * 获取：业务人员
	 */
    public Integer getYewurenyuanId() {
        return yewurenyuanId;
    }


    /**
	 * 设置：业务人员
	 */
    public void setYewurenyuanId(Integer yewurenyuanId) {
        this.yewurenyuanId = yewurenyuanId;
    }
    /**
	 * 获取：理财产品编号
	 */
    public String getLicaicanpinUuidNumber() {
        return licaicanpinUuidNumber;
    }


    /**
	 * 设置：理财产品编号
	 */
    public void setLicaicanpinUuidNumber(String licaicanpinUuidNumber) {
        this.licaicanpinUuidNumber = licaicanpinUuidNumber;
    }
    /**
	 * 获取：理财产品名称
	 */
    public String getLicaicanpinName() {
        return licaicanpinName;
    }


    /**
	 * 设置：理财产品名称
	 */
    public void setLicaicanpinName(String licaicanpinName) {
        this.licaicanpinName = licaicanpinName;
    }
    /**
	 * 获取：理财产品类型
	 */
    public Integer getLicaicanpinTypes() {
        return licaicanpinTypes;
    }


    /**
	 * 设置：理财产品类型
	 */
    public void setLicaicanpinTypes(Integer licaicanpinTypes) {
        this.licaicanpinTypes = licaicanpinTypes;
    }
    /**
	 * 获取：每份/元
	 */
    public Double getLicaicanpinJine() {
        return licaicanpinJine;
    }


    /**
	 * 设置：每份/元
	 */
    public void setLicaicanpinJine(Double licaicanpinJine) {
        this.licaicanpinJine = licaicanpinJine;
    }
    /**
	 * 获取：剩余份数
	 */
    public Integer getLicaicanpinFenshu() {
        return licaicanpinFenshu;
    }


    /**
	 * 设置：剩余份数
	 */
    public void setLicaicanpinFenshu(Integer licaicanpinFenshu) {
        this.licaicanpinFenshu = licaicanpinFenshu;
    }
    /**
	 * 获取：理财产品介绍
	 */
    public String getLicaicanpinContent() {
        return licaicanpinContent;
    }


    /**
	 * 设置：理财产品介绍
	 */
    public void setLicaicanpinContent(String licaicanpinContent) {
        this.licaicanpinContent = licaicanpinContent;
    }
    /**
	 * 获取：发布理财产品时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发布理财产品时间
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
