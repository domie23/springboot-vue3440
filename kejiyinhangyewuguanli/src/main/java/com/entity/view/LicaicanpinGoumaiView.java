package com.entity.view;

import com.entity.LicaicanpinGoumaiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 理财产品购买
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("licaicanpin_goumai")
public class LicaicanpinGoumaiView extends LicaicanpinGoumaiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 审核状态的值
		*/
		private String licaicanpinGoumaiYesnoValue;



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

		//级联表 licaicanpin
			/**
			* 理财产品 的 业务人员
			*/
			private Integer licaicanpinYewurenyuanId;
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
				* 理财产品类型的值
				*/
				private String licaicanpinValue;
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

	public LicaicanpinGoumaiView() {

	}

	public LicaicanpinGoumaiView(LicaicanpinGoumaiEntity licaicanpinGoumaiEntity) {
		try {
			BeanUtils.copyProperties(this, licaicanpinGoumaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 审核状态的值
			*/
			public String getLicaicanpinGoumaiYesnoValue() {
				return licaicanpinGoumaiYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setLicaicanpinGoumaiYesnoValue(String licaicanpinGoumaiYesnoValue) {
				this.licaicanpinGoumaiYesnoValue = licaicanpinGoumaiYesnoValue;
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










				//级联表的get和set licaicanpin

					/**
					* 获取：理财产品 的 业务人员
					*/
					public Integer getLicaicanpinYewurenyuanId() {
						return licaicanpinYewurenyuanId;
					}
					/**
					* 设置：理财产品 的 业务人员
					*/
					public void setLicaicanpinYewurenyuanId(Integer licaicanpinYewurenyuanId) {
						this.licaicanpinYewurenyuanId = licaicanpinYewurenyuanId;
					}


					/**
					* 获取： 理财产品编号
					*/
					public String getLicaicanpinUuidNumber() {
						return licaicanpinUuidNumber;
					}
					/**
					* 设置： 理财产品编号
					*/
					public void setLicaicanpinUuidNumber(String licaicanpinUuidNumber) {
						this.licaicanpinUuidNumber = licaicanpinUuidNumber;
					}

					/**
					* 获取： 理财产品名称
					*/
					public String getLicaicanpinName() {
						return licaicanpinName;
					}
					/**
					* 设置： 理财产品名称
					*/
					public void setLicaicanpinName(String licaicanpinName) {
						this.licaicanpinName = licaicanpinName;
					}

					/**
					* 获取： 理财产品类型
					*/
					public Integer getLicaicanpinTypes() {
						return licaicanpinTypes;
					}
					/**
					* 设置： 理财产品类型
					*/
					public void setLicaicanpinTypes(Integer licaicanpinTypes) {
						this.licaicanpinTypes = licaicanpinTypes;
					}


						/**
						* 获取： 理财产品类型的值
						*/
						public String getLicaicanpinValue() {
							return licaicanpinValue;
						}
						/**
						* 设置： 理财产品类型的值
						*/
						public void setLicaicanpinValue(String licaicanpinValue) {
							this.licaicanpinValue = licaicanpinValue;
						}

					/**
					* 获取： 每份/元
					*/
					public Double getLicaicanpinJine() {
						return licaicanpinJine;
					}
					/**
					* 设置： 每份/元
					*/
					public void setLicaicanpinJine(Double licaicanpinJine) {
						this.licaicanpinJine = licaicanpinJine;
					}

					/**
					* 获取： 剩余份数
					*/
					public Integer getLicaicanpinFenshu() {
						return licaicanpinFenshu;
					}
					/**
					* 设置： 剩余份数
					*/
					public void setLicaicanpinFenshu(Integer licaicanpinFenshu) {
						this.licaicanpinFenshu = licaicanpinFenshu;
					}

					/**
					* 获取： 理财产品介绍
					*/
					public String getLicaicanpinContent() {
						return licaicanpinContent;
					}
					/**
					* 设置： 理财产品介绍
					*/
					public void setLicaicanpinContent(String licaicanpinContent) {
						this.licaicanpinContent = licaicanpinContent;
					}

































}
