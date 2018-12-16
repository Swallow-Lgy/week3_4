package com.example.dell.week3_4.model;

import com.example.dell.week3_4.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String url, Map<String,String>pramas, Class clazz, MyCallBack callBack);
}
