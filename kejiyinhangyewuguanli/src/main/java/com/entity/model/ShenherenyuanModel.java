package com.entity.model;

import com.entity.ShenherenyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 审核人员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShenherenyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 审核人员工号
     */
    private String shenherenyuanUuidNumber;


    /**
     * 审核人员姓名
     */
    private String shenherenyuanName;


    /**
     * 审核人员手机号
     */
    private String shenherenyuanPhone;


    /**
     * 审核人员身份证号
     */
    private String shenherenyuanIdNumber;


    /**
     * 审核人员头像
     */
    private String shenherenyuanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String shenherenyuanEmail;


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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：审核人员工号
	 */
    public String getShenherenyuanUuidNumber() {
        return shenherenyuanUuidNumber;
    }


    /**
	 * 设置：审核人员工号
	 */
    public void setShenherenyuanUuidNumber(String shenherenyuanUuidNumber) {
        this.shenherenyuanUuidNumber = shenherenyuanUuidNumber;
    }
    /**
	 * 获取：审核人员姓名
	 */
    public String getShenherenyuanName() {
        return shenherenyuanName;
    }


    /**
	 * 设置：审核人员姓名
	 */
    public void setShenherenyuanName(String shenherenyuanName) {
        this.shenherenyuanName = shenherenyuanName;
    }
    /**
	 * 获取：审核人员手机号
	 */
    public String getShenherenyuanPhone() {
        return shenherenyuanPhone;
    }


    /**
	 * 设置：审核人员手机号
	 */
    public void setShenherenyuanPhone(String shenherenyuanPhone) {
        this.shenherenyuanPhone = shenherenyuanPhone;
    }
    /**
	 * 获取：审核人员身份证号
	 */
    public String getShenherenyuanIdNumber() {
        return shenherenyuanIdNumber;
    }


    /**
	 * 设置：审核人员身份证号
	 */
    public void setShenherenyuanIdNumber(String shenherenyuanIdNumber) {
        this.shenherenyuanIdNumber = shenherenyuanIdNumber;
    }
    /**
	 * 获取：审核人员头像
	 */
    public String getShenherenyuanPhoto() {
        return shenherenyuanPhoto;
    }


    /**
	 * 设置：审核人员头像
	 */
    public void setShenherenyuanPhoto(String shenherenyuanPhoto) {
        this.shenherenyuanPhoto = shenherenyuanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getShenherenyuanEmail() {
        return shenherenyuanEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setShenherenyuanEmail(String shenherenyuanEmail) {
        this.shenherenyuanEmail = shenherenyuanEmail;
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
