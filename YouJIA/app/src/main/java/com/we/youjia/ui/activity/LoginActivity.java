package com.we.youjia.ui.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.we.youjia.R;
import com.we.youjia.base.BaseActivity;
import com.we.youjia.presenter.LoginPresenter;
import com.we.youjia.view.ILoginView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 19:26
 * Description:这里除了要补全泛型声明同时也要实现声明对应的泛型接口
 */
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {


    @InjectView(R.id.sample_text)
    public TextView tv;

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Dialog showLoading() {
        return null;
    }

    @OnClick(R.id.sample_text)
    public void onClick(View v) {
        Log.i("ph", "点击了====");

        //这边只是个案例，实际情况就是从文本框中拿，但数据是一样的
        String userName = "123";
        String pwd = "123";

        //在activity里调了这句最终会回调showMsg方法，一句话把剩下不相干的逻辑移除出去
        mPresenter.login(userName, pwd);
    }

}
