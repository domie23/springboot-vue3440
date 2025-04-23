package com.entity.model;

import com.entity.DaikuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 贷款
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaikuanModel implements Serializable {
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
     * 贷款编号
     */
    private String daikuanUuidNumber;


    /**
     * 贷款名称
     */
    private String daikuanName;


    /**
     * 还款日期
     */
    private String daikuanHuankuan;


    /**
     * 贷款类型
     */
    private Integer daikuanTypes;


    /**
     * 贷款总额
     */
    private Double daikuanJine;


    /**
     * 贷款月份
     */
    private Integer daikuanYue;


    /**
     * 每月还款
     */
    private Double daikuanMeiyueJine;


    /**
     * 利率
     */
    private String daikuanLilv;


    /**
     * 贷款介绍
     */
    private String daikuanContent;


    /**
     * 发布时间
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
	 * 获取：贷款编号
	 */
    public String getDaikuanUuidNumber() {
        return daikuanUuidNumber;
    }


    /**
	 * 设置：贷款编号
	 */
    public void setDaikuanUuidNumber(String daikuanUuidNumber) {
        this.daikuanUuidNumber = daikuanUuidNumber;
    }
    /**
	 * 获取：贷款名称
	 */
    public String getDaikuanName() {
        return daikuanName;
    }


    /**
	 * 设置：贷款名称
	 */
    public void setDaikuanName(String daikuanName) {
        this.daikuanName = daikuanName;
    }
    /**
	 * 获取：还款日期
	 */
    public String getDaikuanHuankuan() {
        return daikuanHuankuan;
    }


    /**
	 * 设置：还款日期
	 */
    public void setDaikuanHuankuan(String daikuanHuankuan) {
        this.daikuanHuankuan = daikuanHuankuan;
    }
    /**
	 * 获取：贷款类型
	 */
    public Integer getDaikuanTypes() {
        return daikuanTypes;
    }


    /**
	 * 设置：贷款类型
	 */
    public void setDaikuanTypes(Integer daikuanTypes) {
        this.daikuanTypes = daikuanTypes;
    }
    /**
	 * 获取：贷款总额
	 */
    public Double getDaikuanJine() {
        return daikuanJine;
    }


    /**
	 * 设置：贷款总额
	 */
    public void setDaikuanJine(Double daikuanJine) {
        this.daikuanJine = daikuanJine;
    }
    /**
	 * 获取：贷款月份
	 */
    public Integer getDaikuanYue() {
        return daikuanYue;
    }


    /**
	 * 设置：贷款月份
	 */
    public void setDaikuanYue(Integer daikuanYue) {
        this.daikuanYue = daikuanYue;
    }
    /**
	 * 获取：每月还款
	 */
    public Double getDaikuanMeiyueJine() {
        return daikuanMeiyueJine;
    }


    /**
	 * 设置：每月还款
	 */
    public void setDaikuanMeiyueJine(Double daikuanMeiyueJine) {
        this.daikuanMeiyueJine = daikuanMeiyueJine;
    }
    /**
	 * 获取：利率
	 */
    public String getDaikuanLilv() {
        return daikuanLilv;
    }


    /**
	 * 设置：利率
	 */
    public void setDaikuanLilv(String daikuanLilv) {
        this.daikuanLilv = daikuanLilv;
    }
    /**
	 * 获取：贷款介绍
	 */
    public String getDaikuanContent() {
        return daikuanContent;
    }


    /**
	 * 设置：贷款介绍
	 */
    public void setDaikuanContent(String daikuanContent) {
        this.daikuanContent = daikuanContent;
    }
    /**
	 * 获取：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发布时间
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
