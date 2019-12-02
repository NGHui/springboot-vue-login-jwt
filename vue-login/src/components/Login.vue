<template>
  <div id="login">
    <div class="loginToHome">
      <el-form
        ref="form"
        :model="form"
        :rules="ruleForm"
        status-icon
        label-width="80px"
        class="loginForm"
      >
        <h3>登陆</h3>
        <el-form-item
          label="用户名"
          prop="name"
        >
          <el-input
            type="text"
            v-model="form.name"
            auto-complete="off"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
        >
          <el-input
            type="password"
            v-model="form.password"
            auto-complete="off"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="homeBut"
            type="primary"
            plain
            @click="submit"
            :loading="logining"
          >登录</el-button>
          <el-button
            class="loginBut"
            type="primary"
            plain
            @click="resetForm()"
          >重置</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>
<script>
    export default {
        name: "Login",
        data() {
            return {
                logining: false,
                form: {
                    name: 'admin',
                    password: '123456'
                },
                ruleForm: {
                    name: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                    ]
                }
            }
        },
        methods: {
            submit(event) {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.logining = true;
                        //填写登陆逻辑
                        this.$http.post('/api/login',{
                            username: this.form.name,
                            password: this.form.password
                        }).then((res)=>{
                            console.log(res.data)
                            if (res.data.code==200){
                                this.$message.success("登陆成功")
                                console.log(res.data.data)
                                //将后端传递的token放在session域中,放在客户端,减轻服务端的压力
                                window.sessionStorage.setItem("hui-token",res.data.data)
                                //跳转路由
                                this.$router.push('/success')
                            }else {
                                this.$message.error("登陆失败")
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!');
                        return false;
                    }
                })
            },
            resetForm() {
                this.$refs.form.resetFields();
            }
        }
    }
</script>
<style>
  .loginToHome {
    position: absolute;
    left: 0px;
    right: 0;
    top: 0;
    bottom: 0;
    margin: auto;
    width: 400px;
    height: 260px;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    background: #f2f2f2;
    border: 1px solid #dcdfe6;
  }
  .loginForm {
    text-align: center;
    padding-top: 15px;
    padding-right: 30px;
    top: 10px;
  }
  .homeBut {
    position: absolute;
    left: 0px;
  }
  .loginBut {
    position: absolute;
    right: 0px;
  }
</style>

