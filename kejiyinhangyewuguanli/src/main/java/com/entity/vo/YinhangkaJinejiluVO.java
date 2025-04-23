package com.entity.vo;

import com.entity.YinhangkaJinejiluEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 银行卡金额记录
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yinhangka_jinejilu")
public class YinhangkaJinejiluVO implements Serializable {
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

    @TableField(value = "insert_time")
    private Date insertTime;


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

}
