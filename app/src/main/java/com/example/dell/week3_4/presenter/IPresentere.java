package com.example.dell.week3_4.presenter;

import java.util.Map;

public interface IPresentere {
    void requestData(String url, Map<String,String>params,Class clazz);

}
