<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
    >  
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户唯一编号' prop="yonghuUuidNumber">
               <el-input v-model="ruleForm.yonghuUuidNumber"  placeholder='用户唯一编号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户姓名' prop="yonghuName">
               <el-input v-model="ruleForm.yonghuName"  placeholder='用户姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户手机号' prop="yonghuPhone">
               <el-input v-model="ruleForm.yonghuPhone"  placeholder='用户手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户身份证号' prop="yonghuIdNumber">
               <el-input v-model="ruleForm.yonghuIdNumber"  placeholder='用户身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yonghu'" label='用户头像' prop="yonghuPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.yonghuPhoto?ruleForm.yonghuPhoto:''"
                         @change="yonghuPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='yonghu'"  label='是否注销' prop="zhuxiaoTypes">
                 <el-select v-model="ruleForm.zhuxiaoTypes" disabled  placeholder='请选择是否注销'>
                     <el-option
                             v-for="(item,index) in zhuxiaoTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='电子邮箱' prop="yonghuEmail">
               <el-input v-model="ruleForm.yonghuEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='shenherenyuan'"  label='审核人员工号' prop="shenherenyuanUuidNumber">
               <el-input v-model="ruleForm.shenherenyuanUuidNumber"  placeholder='审核人员工号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='shenherenyuan'"  label='审核人员姓名' prop="shenherenyuanName">
               <el-input v-model="ruleForm.shenherenyuanName"  placeholder='审核人员姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='shenherenyuan'"  label='审核人员手机号' prop="shenherenyuanPhone">
               <el-input v-model="ruleForm.shenherenyuanPhone"  placeholder='审核人员手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='shenherenyuan'"  label='审核人员身份证号' prop="shenherenyuanIdNumber">
               <el-input v-model="ruleForm.shenherenyuanIdNumber"  placeholder='审核人员身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='shenherenyuan'" label='审核人员头像' prop="shenherenyuanPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.shenherenyuanPhoto?ruleForm.shenherenyuanPhoto:''"
                         @change="shenherenyuanPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='shenherenyuan'"  label='电子邮箱' prop="shenherenyuanEmail">
               <el-input v-model="ruleForm.shenherenyuanEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yewurenyuan'"  label='业务员工号' prop="yewurenyuanUuidNumber">
               <el-input v-model="ruleForm.yewurenyuanUuidNumber"  placeholder='业务员工号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yewurenyuan'"  label='业务人员姓名' prop="yewurenyuanName">
               <el-input v-model="ruleForm.yewurenyuanName"  placeholder='业务人员姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yewurenyuan'"  label='业务人员手机号' prop="yewurenyuanPhone">
               <el-input v-model="ruleForm.yewurenyuanPhone"  placeholder='业务人员手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yewurenyuan'"  label='业务人员身份证号' prop="yewurenyuanIdNumber">
               <el-input v-model="ruleForm.yewurenyuanIdNumber"  placeholder='业务人员身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yewurenyuan'" label='业务人员头像' prop="yewurenyuanPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.yewurenyuanPhoto?ruleForm.yewurenyuanPhoto:''"
                         @change="yewurenyuanPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yewurenyuan'"  label='电子邮箱' prop="yewurenyuanEmail">
               <el-input v-model="ruleForm.yewurenyuanEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-form-item v-if="flag=='users'" label="用户名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="用户名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='users'"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
        ruleForm: {},
        flag: '',
        usersFlag: false,
        // sexTypesOptions : [],
// 注册表 用户
    // 注册的类型字段 用户
        // 性别
        sexTypesOptions : [],
        // 是否注销
        zhuxiaoTypesOptions : [],
// 注册表 审核人员
    // 注册的类型字段 审核人员
        // 性别
        sexTypesOptions : [],
// 注册表 业务人员
    // 注册的类型字段 业务人员
        // 性别
        sexTypesOptions : [],
    };
  },
  mounted() {
    //获取当前登录用户的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
// 注册表 用户
// 注册表 审核人员
// 注册表 业务人员
      } else {
        this.$message.error(data.msg);
      }
    });

// 注册表 用户 的级联表
// 注册表 审核人员 的级联表
// 注册表 业务人员 的级联表

      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
              this.sexTypesOptions = data.data.list;
          } else {
              this.$message.error(data.msg);
          }
      });
   this.$http({
       url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhuxiao_types`,
       method: "get"
   }).then(({ data }) => {
       if (data && data.code === 0) {
          this.zhuxiaoTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
    });
  },
  methods: {
    chongzhi() {
      this.$router.replace({ path: "/pay" });
    },
    yonghuPhotoUploadChange(fileUrls) {
        this.ruleForm.yonghuPhoto = fileUrls;
    },
    shenherenyuanPhotoUploadChange(fileUrls) {
        this.ruleForm.shenherenyuanPhoto = fileUrls;
    },
    yewurenyuanPhotoUploadChange(fileUrls) {
        this.ruleForm.yewurenyuanPhoto = fileUrls;
    },


    onUpdateHandler() {
                         if((!this.ruleForm.yonghuUuidNumber)&& 'yonghu'==this.flag){
                             this.$message.error('用户唯一编号不能为空');
                             return
                         }

                         if((!this.ruleForm.yonghuName)&& 'yonghu'==this.flag){
                             this.$message.error('用户姓名不能为空');
                             return
                         }

                             if( 'yonghu' ==this.flag && this.ruleForm.yonghuPhone&&(!isMobile(this.ruleForm.yonghuPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.yonghuIdNumber)&& 'yonghu'==this.flag){
                             this.$message.error('用户身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.yonghuPhoto)&& 'yonghu'==this.flag){
                             this.$message.error('用户头像不能为空');
                             return
                         }

                         if((!this.ruleForm.zhuxiaoTypes)&& 'yonghu'==this.flag){
                             this.$message.error('是否注销不能为空');
                             return
                         }

                             if( 'yonghu' ==this.flag && this.ruleForm.yonghuEmail&&(!isEmail(this.ruleForm.yonghuEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.shenherenyuanUuidNumber)&& 'shenherenyuan'==this.flag){
                             this.$message.error('审核人员工号不能为空');
                             return
                         }

                         if((!this.ruleForm.shenherenyuanName)&& 'shenherenyuan'==this.flag){
                             this.$message.error('审核人员姓名不能为空');
                             return
                         }

                             if( 'shenherenyuan' ==this.flag && this.ruleForm.shenherenyuanPhone&&(!isMobile(this.ruleForm.shenherenyuanPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.shenherenyuanIdNumber)&& 'shenherenyuan'==this.flag){
                             this.$message.error('审核人员身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.shenherenyuanPhoto)&& 'shenherenyuan'==this.flag){
                             this.$message.error('审核人员头像不能为空');
                             return
                         }

                             if( 'shenherenyuan' ==this.flag && this.ruleForm.shenherenyuanEmail&&(!isEmail(this.ruleForm.shenherenyuanEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.yewurenyuanUuidNumber)&& 'yewurenyuan'==this.flag){
                             this.$message.error('业务员工号不能为空');
                             return
                         }

                         if((!this.ruleForm.yewurenyuanName)&& 'yewurenyuan'==this.flag){
                             this.$message.error('业务人员姓名不能为空');
                             return
                         }

                             if( 'yewurenyuan' ==this.flag && this.ruleForm.yewurenyuanPhone&&(!isMobile(this.ruleForm.yewurenyuanPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.yewurenyuanIdNumber)&& 'yewurenyuan'==this.flag){
                             this.$message.error('业务人员身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.yewurenyuanPhoto)&& 'yewurenyuan'==this.flag){
                             this.$message.error('业务人员头像不能为空');
                             return
                         }

                             if( 'yewurenyuan' ==this.flag && this.ruleForm.yewurenyuanEmail&&(!isEmail(this.ruleForm.yewurenyuanEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
        if((!this.ruleForm.sexTypes) && this.flag!='users'){
            this.$message.error('性别不能为空');
            return
        }
      if('users'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`用户名不能为空`);
        return	
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
