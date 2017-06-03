package com.we.youjia.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.SoftReference;

import butterknife.ButterKnife;

/**
 * Created by 86119 on 2017/3/30.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T myPresenter;//presenter层的引用

    protected SoftReference<View> rootRef;//软引用的缓存，用来缓存fragment
    // 同时内存过高时可以移除不必要的fragment缓存

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPresenter = createPresenter();
        myPresenter.attachView((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = initRes();
        View view;
        if (rootRef == null) {
            view = inflater.inflate(resId, null);
            //软引用存住该view
            rootRef = new SoftReference<>(view);
            ButterKnife.inject(this, view);
            init();
        } else {
            view = rootRef.get();
            if (view != null) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
            }

        }
        return view;
    }

    /**
     * 返回资源的id
     *
     * @return 资源id
     */
    protected abstract int initRes();

    protected abstract void init();


    @Override
    public void onDestroy() {
        super.onDestroy();
        myPresenter.detachView();
        rootRef.clear();



    }

    /**
     * 创建presenter对象
     *
     * @return 子类中的实例presenter对象
     */
    public abstract T createPresenter();



}
