package com.entity.view;

import com.entity.ZhanghuzhuxiaoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 账户注销
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zhanghuzhuxiao")
public class ZhanghuzhuxiaoView extends ZhanghuzhuxiaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 审核状态的值
		*/
		private String zhanghuzhuxiaoYesnoValue;



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

	public ZhanghuzhuxiaoView() {

	}

	public ZhanghuzhuxiaoView(ZhanghuzhuxiaoEntity zhanghuzhuxiaoEntity) {
		try {
			BeanUtils.copyProperties(this, zhanghuzhuxiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 审核状态的值
			*/
			public String getZhanghuzhuxiaoYesnoValue() {
				return zhanghuzhuxiaoYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setZhanghuzhuxiaoYesnoValue(String zhanghuzhuxiaoYesnoValue) {
				this.zhanghuzhuxiaoYesnoValue = zhanghuzhuxiaoYesnoValue;
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




























}
