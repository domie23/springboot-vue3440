package com.entity.view;

import com.entity.YuyuecunkuanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 预约存款
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("yuyuecunkuan")
public class YuyuecunkuanView extends YuyuecunkuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 审核状态的值
		*/
		private String yuyuecunkuanYesnoValue;



		//级联表 yonghu
			/**
			* 用户唯一编号
			*/
			private String yonghuUuidNumber;
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 是否注销
			*/
			private Integer zhuxiaoTypes;
				/**
				* 是否注销的值
				*/
				private String zhuxiaoValue;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

		//级联表 shenherenyuan
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
			* 电子邮箱
			*/
			private String shenherenyuanEmail;

		//级联表 yinhangka
			/**
			* 银行卡 的 用户
			*/
			private Integer yinhangkaYonghuId;
			/**
			* 银行卡卡号
			*/
			private String yinhangkaUuidNumber;
			/**
			* 开户地
			*/
			private Integer kaihudiTypes;
				/**
				* 开户地的值
				*/
				private String kaihudiValue;
			/**
			* 卡余额
			*/
			private Double yinghangkaMoney;
			/**
			* 绑定手机号
			*/
			private String yinhangkaPhone;
			/**
			* 绑定邮箱
			*/
			private String yinhangkaEmail;
			/**
			* 开户时间
			*/
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			private Date kaihuTime;
			/**
			* 银行卡备注
			*/
			private String yinhangkaContent;

	public YuyuecunkuanView() {

	}

	public YuyuecunkuanView(YuyuecunkuanEntity yuyuecunkuanEntity) {
		try {
			BeanUtils.copyProperties(this, yuyuecunkuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 审核状态的值
			*/
			public String getYuyuecunkuanYesnoValue() {
				return yuyuecunkuanYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setYuyuecunkuanYesnoValue(String yuyuecunkuanYesnoValue) {
				this.yuyuecunkuanYesnoValue = yuyuecunkuanYesnoValue;
			}








				//级联表的get和set yonghu

					/**
					* 获取： 用户唯一编号
					*/
					public String getYonghuUuidNumber() {
						return yonghuUuidNumber;
					}
					/**
					* 设置： 用户唯一编号
					*/
					public void setYonghuUuidNumber(String yonghuUuidNumber) {
						this.yonghuUuidNumber = yonghuUuidNumber;
					}

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 是否注销
					*/
					public Integer getZhuxiaoTypes() {
						return zhuxiaoTypes;
					}
					/**
					* 设置： 是否注销
					*/
					public void setZhuxiaoTypes(Integer zhuxiaoTypes) {
						this.zhuxiaoTypes = zhuxiaoTypes;
					}


						/**
						* 获取： 是否注销的值
						*/
						public String getZhuxiaoValue() {
							return zhuxiaoValue;
						}
						/**
						* 设置： 是否注销的值
						*/
						public void setZhuxiaoValue(String zhuxiaoValue) {
							this.zhuxiaoValue = zhuxiaoValue;
						}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}






















				//级联表的get和set shenherenyuan

					/**
					* 获取： 审核人员工号
					*/
					public String getShenherenyuanUuidNumber() {
						return shenherenyuanUuidNumber;
					}
					/**
					* 设置： 审核人员工号
					*/
					public void setShenherenyuanUuidNumber(String shenherenyuanUuidNumber) {
						this.shenherenyuanUuidNumber = shenherenyuanUuidNumber;
					}

					/**
					* 获取： 审核人员姓名
					*/
					public String getShenherenyuanName() {
						return shenherenyuanName;
					}
					/**
					* 设置： 审核人员姓名
					*/
					public void setShenherenyuanName(String shenherenyuanName) {
						this.shenherenyuanName = shenherenyuanName;
					}

					/**
					* 获取： 审核人员手机号
					*/
					public String getShenherenyuanPhone() {
						return shenherenyuanPhone;
					}
					/**
					* 设置： 审核人员手机号
					*/
					public void setShenherenyuanPhone(String shenherenyuanPhone) {
						this.shenherenyuanPhone = shenherenyuanPhone;
					}

					/**
					* 获取： 审核人员身份证号
					*/
					public String getShenherenyuanIdNumber() {
						return shenherenyuanIdNumber;
					}
					/**
					* 设置： 审核人员身份证号
					*/
					public void setShenherenyuanIdNumber(String shenherenyuanIdNumber) {
						this.shenherenyuanIdNumber = shenherenyuanIdNumber;
					}

					/**
					* 获取： 审核人员头像
					*/
					public String getShenherenyuanPhoto() {
						return shenherenyuanPhoto;
					}
					/**
					* 设置： 审核人员头像
					*/
					public void setShenherenyuanPhoto(String shenherenyuanPhoto) {
						this.shenherenyuanPhoto = shenherenyuanPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getShenherenyuanEmail() {
						return shenherenyuanEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setShenherenyuanEmail(String shenherenyuanEmail) {
						this.shenherenyuanEmail = shenherenyuanEmail;
					}







				//级联表的get和set yinhangka

					/**
					* 获取：银行卡 的 用户
					*/
					public Integer getYinhangkaYonghuId() {
						return yinhangkaYonghuId;
					}
					/**
					* 设置：银行卡 的 用户
					*/
					public void setYinhangkaYonghuId(Integer yinhangkaYonghuId) {
						this.yinhangkaYonghuId = yinhangkaYonghuId;
					}


					/**
					* 获取： 银行卡卡号
					*/
					public String getYinhangkaUuidNumber() {
						return yinhangkaUuidNumber;
					}
					/**
					* 设置： 银行卡卡号
					*/
					public void setYinhangkaUuidNumber(String yinhangkaUuidNumber) {
						this.yinhangkaUuidNumber = yinhangkaUuidNumber;
					}

					/**
					* 获取： 开户地
					*/
					public Integer getKaihudiTypes() {
						return kaihudiTypes;
					}
					/**
					* 设置： 开户地
					*/
					public void setKaihudiTypes(Integer kaihudiTypes) {
						this.kaihudiTypes = kaihudiTypes;
					}


						/**
						* 获取： 开户地的值
						*/
						public String getKaihudiValue() {
							return kaihudiValue;
						}
						/**
						* 设置： 开户地的值
						*/
						public void setKaihudiValue(String kaihudiValue) {
							this.kaihudiValue = kaihudiValue;
						}

					/**
					* 获取： 卡余额
					*/
					public Double getYinghangkaMoney() {
						return yinghangkaMoney;
					}
					/**
					* 设置： 卡余额
					*/
					public void setYinghangkaMoney(Double yinghangkaMoney) {
						this.yinghangkaMoney = yinghangkaMoney;
					}

					/**
					* 获取： 绑定手机号
					*/
					public String getYinhangkaPhone() {
						return yinhangkaPhone;
					}
					/**
					* 设置： 绑定手机号
					*/
					public void setYinhangkaPhone(String yinhangkaPhone) {
						this.yinhangkaPhone = yinhangkaPhone;
					}

					/**
					* 获取： 绑定邮箱
					*/
					public String getYinhangkaEmail() {
						return yinhangkaEmail;
					}
					/**
					* 设置： 绑定邮箱
					*/
					public void setYinhangkaEmail(String yinhangkaEmail) {
						this.yinhangkaEmail = yinhangkaEmail;
					}

					/**
					* 获取： 开户时间
					*/
					public Date getKaihuTime() {
						return kaihuTime;
					}
					/**
					* 设置： 开户时间
					*/
					public void setKaihuTime(Date kaihuTime) {
						this.kaihuTime = kaihuTime;
					}

					/**
					* 获取： 银行卡备注
					*/
					public String getYinhangkaContent() {
						return yinhangkaContent;
					}
					/**
					* 设置： 银行卡备注
					*/
					public void setYinhangkaContent(String yinhangkaContent) {
						this.yinhangkaContent = yinhangkaContent;
					}





























}
