package com.entity.vo;

import com.entity.YuyuecunkuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 预约存款
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yuyuecunkuan")
public class YuyuecunkuanVO implements Serializable {
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
     * 审核人员
     */

    @TableField(value = "shenherenyuan_id")
    private Integer shenherenyuanId;


    /**
     * 预约编号
     */

    @TableField(value = "yuyuecunkuan_uuid_number")
    private String yuyuecunkuanUuidNumber;


    /**
     * 存款金额
     */

    @TableField(value = "yuyuecunkuan_money")
    private Double yuyuecunkuanMoney;


    /**
     * 存款详情
     */

    @TableField(value = "yuyuecunkuan_content")
    private String yuyuecunkuanContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yuyuecunkuan_time")
    private Date yuyuecunkuanTime;


    /**
     * 审核状态
     */

    @TableField(value = "yuyuecunkuan_yesno_types")
    private Integer yuyuecunkuanYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "yuyuecunkuan_yesno_text")
    private String yuyuecunkuanYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yuyuecunkuan_shenhe_time")
    private Date yuyuecunkuanShenheTime;


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
	 * 设置：审核人员
	 */
    public Integer getShenherenyuanId() {
        return shenherenyuanId;
    }


    /**
	 * 获取：审核人员
	 */

    public void setShenherenyuanId(Integer shenherenyuanId) {
        this.shenherenyuanId = shenherenyuanId;
    }
    /**
	 * 设置：预约编号
	 */
    public String getYuyuecunkuanUuidNumber() {
        return yuyuecunkuanUuidNumber;
    }


    /**
	 * 获取：预约编号
	 */

    public void setYuyuecunkuanUuidNumber(String yuyuecunkuanUuidNumber) {
        this.yuyuecunkuanUuidNumber = yuyuecunkuanUuidNumber;
    }
    /**
	 * 设置：存款金额
	 */
    public Double getYuyuecunkuanMoney() {
        return yuyuecunkuanMoney;
    }


    /**
	 * 获取：存款金额
	 */

    public void setYuyuecunkuanMoney(Double yuyuecunkuanMoney) {
        this.yuyuecunkuanMoney = yuyuecunkuanMoney;
    }
    /**
	 * 设置：存款详情
	 */
    public String getYuyuecunkuanContent() {
        return yuyuecunkuanContent;
    }


    /**
	 * 获取：存款详情
	 */

    public void setYuyuecunkuanContent(String yuyuecunkuanContent) {
        this.yuyuecunkuanContent = yuyuecunkuanContent;
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
    public Date getYuyuecunkuanTime() {
        return yuyuecunkuanTime;
    }


    /**
	 * 获取：预约日期
	 */

    public void setYuyuecunkuanTime(Date yuyuecunkuanTime) {
        this.yuyuecunkuanTime = yuyuecunkuanTime;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getYuyuecunkuanYesnoTypes() {
        return yuyuecunkuanYesnoTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setYuyuecunkuanYesnoTypes(Integer yuyuecunkuanYesnoTypes) {
        this.yuyuecunkuanYesnoTypes = yuyuecunkuanYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getYuyuecunkuanYesnoText() {
        return yuyuecunkuanYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setYuyuecunkuanYesnoText(String yuyuecunkuanYesnoText) {
        this.yuyuecunkuanYesnoText = yuyuecunkuanYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getYuyuecunkuanShenheTime() {
        return yuyuecunkuanShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setYuyuecunkuanShenheTime(Date yuyuecunkuanShenheTime) {
        this.yuyuecunkuanShenheTime = yuyuecunkuanShenheTime;
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
