package com.entity.vo;

import com.entity.ShenherenyuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 审核人员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shenherenyuan")
public class ShenherenyuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 审核人员工号
     */

    @TableField(value = "shenherenyuan_uuid_number")
    private String shenherenyuanUuidNumber;


    /**
     * 审核人员姓名
     */

    @TableField(value = "shenherenyuan_name")
    private String shenherenyuanName;


    /**
     * 审核人员手机号
     */

    @TableField(value = "shenherenyuan_phone")
    private String shenherenyuanPhone;


    /**
     * 审核人员身份证号
     */

    @TableField(value = "shenherenyuan_id_number")
    private String shenherenyuanIdNumber;


    /**
     * 审核人员头像
     */

    @TableField(value = "shenherenyuan_photo")
    private String shenherenyuanPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "shenherenyuan_email")
    private String shenherenyuanEmail;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：审核人员工号
	 */
    public String getShenherenyuanUuidNumber() {
        return shenherenyuanUuidNumber;
    }


    /**
	 * 获取：审核人员工号
	 */

    public void setShenherenyuanUuidNumber(String shenherenyuanUuidNumber) {
        this.shenherenyuanUuidNumber = shenherenyuanUuidNumber;
    }
    /**
	 * 设置：审核人员姓名
	 */
    public String getShenherenyuanName() {
        return shenherenyuanName;
    }


    /**
	 * 获取：审核人员姓名
	 */

    public void setShenherenyuanName(String shenherenyuanName) {
        this.shenherenyuanName = shenherenyuanName;
    }
    /**
	 * 设置：审核人员手机号
	 */
    public String getShenherenyuanPhone() {
        return shenherenyuanPhone;
    }


    /**
	 * 获取：审核人员手机号
	 */

    public void setShenherenyuanPhone(String shenherenyuanPhone) {
        this.shenherenyuanPhone = shenherenyuanPhone;
    }
    /**
	 * 设置：审核人员身份证号
	 */
    public String getShenherenyuanIdNumber() {
        return shenherenyuanIdNumber;
    }


    /**
	 * 获取：审核人员身份证号
	 */

    public void setShenherenyuanIdNumber(String shenherenyuanIdNumber) {
        this.shenherenyuanIdNumber = shenherenyuanIdNumber;
    }
    /**
	 * 设置：审核人员头像
	 */
    public String getShenherenyuanPhoto() {
        return shenherenyuanPhoto;
    }


    /**
	 * 获取：审核人员头像
	 */

    public void setShenherenyuanPhoto(String shenherenyuanPhoto) {
        this.shenherenyuanPhoto = shenherenyuanPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getShenherenyuanEmail() {
        return shenherenyuanEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setShenherenyuanEmail(String shenherenyuanEmail) {
        this.shenherenyuanEmail = shenherenyuanEmail;
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
