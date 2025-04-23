package com.entity.view;

import com.entity.LicaicanpinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 理财产品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("licaicanpin")
public class LicaicanpinView extends LicaicanpinEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 理财产品类型的值
		*/
		private String licaicanpinValue;



		//级联表 yewurenyuan
			/**
			* 业务员工号
			*/
			private String yewurenyuanUuidNumber;
			/**
			* 业务人员姓名
			*/
			private String yewurenyuanName;
			/**
			* 业务人员手机号
			*/
			private String yewurenyuanPhone;
			/**
			* 业务人员身份证号
			*/
			private String yewurenyuanIdNumber;
			/**
			* 业务人员头像
			*/
			private String yewurenyuanPhoto;
			/**
			* 电子邮箱
			*/
			private String yewurenyuanEmail;

	public LicaicanpinView() {

	}

	public LicaicanpinView(LicaicanpinEntity licaicanpinEntity) {
		try {
			BeanUtils.copyProperties(this, licaicanpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


















				//级联表的get和set yewurenyuan

					/**
					* 获取： 业务员工号
					*/
					public String getYewurenyuanUuidNumber() {
						return yewurenyuanUuidNumber;
					}
					/**
					* 设置： 业务员工号
					*/
					public void setYewurenyuanUuidNumber(String yewurenyuanUuidNumber) {
						this.yewurenyuanUuidNumber = yewurenyuanUuidNumber;
					}

					/**
					* 获取： 业务人员姓名
					*/
					public String getYewurenyuanName() {
						return yewurenyuanName;
					}
					/**
					* 设置： 业务人员姓名
					*/
					public void setYewurenyuanName(String yewurenyuanName) {
						this.yewurenyuanName = yewurenyuanName;
					}

					/**
					* 获取： 业务人员手机号
					*/
					public String getYewurenyuanPhone() {
						return yewurenyuanPhone;
					}
					/**
					* 设置： 业务人员手机号
					*/
					public void setYewurenyuanPhone(String yewurenyuanPhone) {
						this.yewurenyuanPhone = yewurenyuanPhone;
					}

					/**
					* 获取： 业务人员身份证号
					*/
					public String getYewurenyuanIdNumber() {
						return yewurenyuanIdNumber;
					}
					/**
					* 设置： 业务人员身份证号
					*/
					public void setYewurenyuanIdNumber(String yewurenyuanIdNumber) {
						this.yewurenyuanIdNumber = yewurenyuanIdNumber;
					}

					/**
					* 获取： 业务人员头像
					*/
					public String getYewurenyuanPhoto() {
						return yewurenyuanPhoto;
					}
					/**
					* 设置： 业务人员头像
					*/
					public void setYewurenyuanPhoto(String yewurenyuanPhoto) {
						this.yewurenyuanPhoto = yewurenyuanPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYewurenyuanEmail() {
						return yewurenyuanEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYewurenyuanEmail(String yewurenyuanEmail) {
						this.yewurenyuanEmail = yewurenyuanEmail;
					}
















}
