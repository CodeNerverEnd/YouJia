package com.we.youjia.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 18:57
 * Description:
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;

    protected final MyHandler mHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定引用
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        setContentView(getLayoutId());

        //使用ButterKnife注解库来执行findview，设置监听等操作
        ButterKnife.inject(this);
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        //解除引用
        mPresenter.detachView();
    }

    /**
     * 子类需要在onCreate里执行的操作，
     * 除非业务需要，不然不要重写onCreate
     */
    protected abstract void init();


    /**
     * 在子类返回配置文件的layout
     *
     * @return layout
     */
    protected abstract int getLayoutId();


    /**
     * 在子类决定具体的presenter类
     *
     * @return 继承BasePresenter类的子类
     */
    public abstract T createPresenter();


    /**
     * 当子类需要用到handler的时候，重写该方法
     * 这里做个空实现因为不是每个view都需要handler执行方法
     *
     * @param msg 消息
     */
    protected void handMsg(Message msg) {
    }


    /**
     * 正常的启动一个activity
     *
     * @param clazz
     */
    protected void skipTo(Class<?> clazz) {
        Intent intent = new Intent();
        skip(intent);
    }


    /**
     * 正常的启动activity
     *
     * @param intent
     */
    protected void skip(Intent intent) {
        startActivity(intent);
    }


    /**
     * 需要带有传递参数的startActivity
     *
     * @param clazz
     * @param bundle
     */
    protected void skip(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     * 内部handler类，避免每个类重复写相同的代码
     */
    private static class MyHandler extends Handler {

        //弱引用activity避免内存泄漏
        private WeakReference<BaseActivity> viewRef;

        public MyHandler(BaseActivity activity) {
            this.viewRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (viewRef != null && viewRef.get() != null) {
                BaseActivity activity = viewRef.get();
                activity.handMsg(msg);
            }
        }
    }


}
