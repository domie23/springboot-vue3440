import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import daikuan from '@/views/modules/daikuan/list'
    import yonghu from '@/views/modules/yonghu/list'
    import daikuanGoumai from '@/views/modules/daikuanGoumai/list'
    import dictionary from '@/views/modules/dictionary/list'
    import licaicanpin from '@/views/modules/licaicanpin/list'
    import licaicanpinGoumai from '@/views/modules/licaicanpinGoumai/list'
    import shenherenyuan from '@/views/modules/shenherenyuan/list'
    import yewurenyuan from '@/views/modules/yewurenyuan/list'
    import yinhangka from '@/views/modules/yinhangka/list'
    import yinhangkaJinejilu from '@/views/modules/yinhangkaJinejilu/list'
    import yinhangkabuban from '@/views/modules/yinhangkabuban/list'
    import yuyuecunkuan from '@/views/modules/yuyuecunkuan/list'
    import yuyuequkuan from '@/views/modules/yuyuequkuan/list'
    import zhanghuzhuxiao from '@/views/modules/zhanghuzhuxiao/list'
    import zhuanzhang from '@/views/modules/zhuanzhang/list'
    import dictionaryDaikuan from '@/views/modules/dictionaryDaikuan/list'
    import dictionaryDaikuanGoumaiYesno from '@/views/modules/dictionaryDaikuanGoumaiYesno/list'
    import dictionaryJilu from '@/views/modules/dictionaryJilu/list'
    import dictionaryKaihudi from '@/views/modules/dictionaryKaihudi/list'
    import dictionaryLicaicanpin from '@/views/modules/dictionaryLicaicanpin/list'
    import dictionaryLicaicanpinGoumaiYesno from '@/views/modules/dictionaryLicaicanpinGoumaiYesno/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryYinhangkabubanYesno from '@/views/modules/dictionaryYinhangkabubanYesno/list'
    import dictionaryYuyuecunkuanYesno from '@/views/modules/dictionaryYuyuecunkuanYesno/list'
    import dictionaryYuyuequkuanYesno from '@/views/modules/dictionaryYuyuequkuanYesno/list'
    import dictionaryZhanghuzhuxiaoYesno from '@/views/modules/dictionaryZhanghuzhuxiaoYesno/list'
    import dictionaryZhuanzhang from '@/views/modules/dictionaryZhuanzhang/list'
    import dictionaryZhuanzhangYesno from '@/views/modules/dictionaryZhuanzhangYesno/list'
    import dictionaryZhuxiao from '@/views/modules/dictionaryZhuxiao/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryDaikuan',
        name: '贷款类型',
        component: dictionaryDaikuan
    }
    ,{
        path: '/dictionaryDaikuanGoumaiYesno',
        name: '审核状态',
        component: dictionaryDaikuanGoumaiYesno
    }
    ,{
        path: '/dictionaryJilu',
        name: '记录类型',
        component: dictionaryJilu
    }
    ,{
        path: '/dictionaryKaihudi',
        name: '开户地',
        component: dictionaryKaihudi
    }
    ,{
        path: '/dictionaryLicaicanpin',
        name: '理财产品类型',
        component: dictionaryLicaicanpin
    }
    ,{
        path: '/dictionaryLicaicanpinGoumaiYesno',
        name: '审核状态',
        component: dictionaryLicaicanpinGoumaiYesno
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryYinhangkabubanYesno',
        name: '审核状态',
        component: dictionaryYinhangkabubanYesno
    }
    ,{
        path: '/dictionaryYuyuecunkuanYesno',
        name: '审核状态',
        component: dictionaryYuyuecunkuanYesno
    }
    ,{
        path: '/dictionaryYuyuequkuanYesno',
        name: '审核状态',
        component: dictionaryYuyuequkuanYesno
    }
    ,{
        path: '/dictionaryZhanghuzhuxiaoYesno',
        name: '审核状态',
        component: dictionaryZhanghuzhuxiaoYesno
    }
    ,{
        path: '/dictionaryZhuanzhang',
        name: '转账状态',
        component: dictionaryZhuanzhang
    }
    ,{
        path: '/dictionaryZhuanzhangYesno',
        name: '审核状态',
        component: dictionaryZhuanzhangYesno
    }
    ,{
        path: '/dictionaryZhuxiao',
        name: '是否注销',
        component: dictionaryZhuxiao
    }


    ,{
        path: '/daikuan',
        name: '贷款',
        component: daikuan
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/daikuanGoumai',
        name: '贷款购买',
        component: daikuanGoumai
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/licaicanpin',
        name: '理财产品',
        component: licaicanpin
      }
    ,{
        path: '/licaicanpinGoumai',
        name: '理财产品购买',
        component: licaicanpinGoumai
      }
    ,{
        path: '/shenherenyuan',
        name: '审核人员',
        component: shenherenyuan
      }
    ,{
        path: '/yewurenyuan',
        name: '业务人员',
        component: yewurenyuan
      }
    ,{
        path: '/yinhangka',
        name: '银行卡',
        component: yinhangka
      }
    ,{
        path: '/yinhangkaJinejilu',
        name: '银行卡金额记录',
        component: yinhangkaJinejilu
      }
    ,{
        path: '/yinhangkabuban',
        name: '银行卡补办',
        component: yinhangkabuban
      }
    ,{
        path: '/yuyuecunkuan',
        name: '预约存款',
        component: yuyuecunkuan
      }
    ,{
        path: '/yuyuequkuan',
        name: '预约取款',
        component: yuyuequkuan
      }
    ,{
        path: '/zhanghuzhuxiao',
        name: '账户注销',
        component: zhanghuzhuxiao
      }
    ,{
        path: '/zhuanzhang',
        name: '转账',
        component: zhuanzhang
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
