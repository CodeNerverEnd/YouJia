package com.we.youjia.view;

import android.app.Dialog;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 19:23
 * Description:这里做个简单的demo实现
 */

public interface ILoginView {


    void showMsg(String msg);

   /* */

    /**
     * 加载进度条等，具体业务根据不用Activity的需要来确认接口
     *
     * @return dialog
     */
    Dialog showLoading();

}
