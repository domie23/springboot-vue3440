package com.entity.view;

import com.entity.DaikuanGoumaiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 贷款购买
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("daikuan_goumai")
public class DaikuanGoumaiView extends DaikuanGoumaiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 审核状态的值
		*/
		private String daikuanGoumaiYesnoValue;



		//级联表 daikuan
			/**
			* 贷款 的 业务人员
			*/
			private Integer daikuanYewurenyuanId;
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
				* 贷款类型的值
				*/
				private String daikuanValue;
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

	public DaikuanGoumaiView() {

	}

	public DaikuanGoumaiView(DaikuanGoumaiEntity daikuanGoumaiEntity) {
		try {
			BeanUtils.copyProperties(this, daikuanGoumaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 审核状态的值
			*/
			public String getDaikuanGoumaiYesnoValue() {
				return daikuanGoumaiYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setDaikuanGoumaiYesnoValue(String daikuanGoumaiYesnoValue) {
				this.daikuanGoumaiYesnoValue = daikuanGoumaiYesnoValue;
			}






				//级联表的get和set daikuan

					/**
					* 获取：贷款 的 业务人员
					*/
					public Integer getDaikuanYewurenyuanId() {
						return daikuanYewurenyuanId;
					}
					/**
					* 设置：贷款 的 业务人员
					*/
					public void setDaikuanYewurenyuanId(Integer daikuanYewurenyuanId) {
						this.daikuanYewurenyuanId = daikuanYewurenyuanId;
					}


					/**
					* 获取： 贷款编号
					*/
					public String getDaikuanUuidNumber() {
						return daikuanUuidNumber;
					}
					/**
					* 设置： 贷款编号
					*/
					public void setDaikuanUuidNumber(String daikuanUuidNumber) {
						this.daikuanUuidNumber = daikuanUuidNumber;
					}

					/**
					* 获取： 贷款名称
					*/
					public String getDaikuanName() {
						return daikuanName;
					}
					/**
					* 设置： 贷款名称
					*/
					public void setDaikuanName(String daikuanName) {
						this.daikuanName = daikuanName;
					}

					/**
					* 获取： 还款日期
					*/
					public String getDaikuanHuankuan() {
						return daikuanHuankuan;
					}
					/**
					* 设置： 还款日期
					*/
					public void setDaikuanHuankuan(String daikuanHuankuan) {
						this.daikuanHuankuan = daikuanHuankuan;
					}

					/**
					* 获取： 贷款类型
					*/
					public Integer getDaikuanTypes() {
						return daikuanTypes;
					}
					/**
					* 设置： 贷款类型
					*/
					public void setDaikuanTypes(Integer daikuanTypes) {
						this.daikuanTypes = daikuanTypes;
					}


						/**
						* 获取： 贷款类型的值
						*/
						public String getDaikuanValue() {
							return daikuanValue;
						}
						/**
						* 设置： 贷款类型的值
						*/
						public void setDaikuanValue(String daikuanValue) {
							this.daikuanValue = daikuanValue;
						}

					/**
					* 获取： 贷款总额
					*/
					public Double getDaikuanJine() {
						return daikuanJine;
					}
					/**
					* 设置： 贷款总额
					*/
					public void setDaikuanJine(Double daikuanJine) {
						this.daikuanJine = daikuanJine;
					}

					/**
					* 获取： 贷款月份
					*/
					public Integer getDaikuanYue() {
						return daikuanYue;
					}
					/**
					* 设置： 贷款月份
					*/
					public void setDaikuanYue(Integer daikuanYue) {
						this.daikuanYue = daikuanYue;
					}

					/**
					* 获取： 每月还款
					*/
					public Double getDaikuanMeiyueJine() {
						return daikuanMeiyueJine;
					}
					/**
					* 设置： 每月还款
					*/
					public void setDaikuanMeiyueJine(Double daikuanMeiyueJine) {
						this.daikuanMeiyueJine = daikuanMeiyueJine;
					}

					/**
					* 获取： 利率
					*/
					public String getDaikuanLilv() {
						return daikuanLilv;
					}
					/**
					* 设置： 利率
					*/
					public void setDaikuanLilv(String daikuanLilv) {
						this.daikuanLilv = daikuanLilv;
					}

					/**
					* 获取： 贷款介绍
					*/
					public String getDaikuanContent() {
						return daikuanContent;
					}
					/**
					* 设置： 贷款介绍
					*/
					public void setDaikuanContent(String daikuanContent) {
						this.daikuanContent = daikuanContent;
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
