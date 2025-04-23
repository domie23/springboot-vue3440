package com.entity.vo;

import com.entity.DaikuanGoumaiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 贷款购买
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("daikuan_goumai")
public class DaikuanGoumaiVO implements Serializable {
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

    @TableField(value = "insert_time")
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

}
