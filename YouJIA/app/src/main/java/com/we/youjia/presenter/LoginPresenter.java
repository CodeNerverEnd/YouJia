package com.we.youjia.presenter;

import com.we.youjia.base.BasePresenter;
import com.we.youjia.base.IModel;
import com.we.youjia.model.LoginModel;
import com.we.youjia.view.ILoginView;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 19:26
 * Description:
 */
public class LoginPresenter extends BasePresenter<ILoginView> {


    public void login(String userName, String pwd) {

        model=new LoginModel(userName,pwd);

        final ILoginView iLoginView = viewRef.get();

        //因为那边传回来的是String类型，所以这里和onCompleted里都要用这种类型
        model.load(new IModel.OnCompleteListener<String>() {

            @Override
            public void onCompleted(String data) {
                //因为是弱引用一定要加上非空判断
                if (iLoginView != null) {
                    iLoginView.showMsg(data);
                }
            }

            @Override
            public void onFail(String msg) {
                if (iLoginView != null) {
                    iLoginView.showMsg(msg);
                }
            }
        });

    }
}
