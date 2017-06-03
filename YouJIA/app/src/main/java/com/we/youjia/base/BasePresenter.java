package com.we.youjia.base;

import java.lang.ref.WeakReference;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 18:50
 * Description:抽象层的Presenter，内部不做任何方法实现，持有view层的弱引用防止内存泄漏，同时持有model层的对象方便
 * 进行数据传递
 */
public abstract class BasePresenter<T> {

    //view层的引用（activity，fragment等需要实现的view接口）
    protected WeakReference<T> viewRef;

    protected IModel model;


    public void attachView(T view) {
        this.viewRef = new WeakReference<T>(view);
    }


    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
        }
    }


}
