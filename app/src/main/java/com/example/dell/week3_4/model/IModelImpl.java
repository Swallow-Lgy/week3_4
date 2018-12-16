package com.example.dell.week3_4.model;

import com.example.dell.week3_4.callback.ICallBack;
import com.example.dell.week3_4.callback.MyCallBack;
import com.example.dell.week3_4.util.OkHttpUtil;

import java.util.Map;

public class IModelImpl implements IModel{
    @Override
    public void requestData(String url, Map<String, String> pramas, Class clazz, final MyCallBack callBack) {
       OkHttpUtil.getmIstance().postEuqeqe(url, pramas, new ICallBack() {
           @Override
           public void faile(Exception e) {
                callBack.setData(e);
           }

           @Override
           public void success(Object data) {
                callBack.setData(data);
           }
       },clazz);
    }
}
