package com.we.youjia.base;

/**
 * Created with Android Studio
 * User: 潘浩
 * School 南华大学
 * Date: 2017/5/28
 * Time: 19:10
 * Description:model层接口，一般用于网络加载数据或者本地加载数据等，类似dao层
 */
public interface IModel {

    /**
     * model层加载数据的业务
     *
     * @param listener 回调接口监听器
     */
    void load(OnCompleteListener listener);

    /**
     * 回调接口
     *
     * @param <T> 数据类型
     */
    interface OnCompleteListener<T> {

        /**
         * 当业务成功时(例如网络访问得到正确结果)调用此方法，有数据回传做参数传进来
         * 无数据回传填null
         *
         * @param data 数据，没有时填null
         */
        void onCompleted(T data);


        /**
         * 当业务失败时（例如网络连接不上或者注册时用户名已存在时，一般这些只需要传递字符串告知用户）手动调用此方法
         * 无信息回显填""
         *
         * @param msg 需要展示在界面告知用户的msg消息
         */
        void onFail(String msg);

    }


}
