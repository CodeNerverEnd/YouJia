package com.we.youjia.model;

import com.we.youjia.base.IModel;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 19:27
 * Description:
 */
public class LoginModel implements IModel {

    private String userName;

    private String pwd;


    public LoginModel(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public void load(OnCompleteListener listener) {
        if ("123".equals(userName) && "123".equals(pwd)) {
            //这里这个方法什么类型的数据都可以传递，因为做了泛型，同时presenter要声明这种泛型
            listener.onCompleted("登录成功");
        } else {
            listener.onFail("登录失败");
        }
    }
}
