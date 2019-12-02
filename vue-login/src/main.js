// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueCookies from 'vue-cookies'
//导入element组件
import './plugins/element.js'
import axios from 'axios'
//Vue.use(VueAxios, axios)
axios.interceptors.request.use(config=>{
  console.log(config)
  /*在每次发起请求时都会发送自己的请求头*/
  /*后端通过获取请求头进行解密,验证求头是否正确
  * 后端在放行*/
  config.headers.authorization=window.sessionStorage.getItem("hui-token")
  return config;
})

axios.defaults.withCredentials = true

Vue.prototype.$http=axios

Vue.use(VueCookies)
Vue.config.productionTip = false
Vue.use(ElementUI)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
