package com.example.dell.week3_4.presenter;

import com.example.dell.week3_4.callback.MyCallBack;
import com.example.dell.week3_4.model.IModelImpl;
import com.example.dell.week3_4.view.IView;

import java.util.Map;

public class IPresrnterImpl implements IPresentere {
    private IView miView;
    private IModelImpl miModel;
    public IPresrnterImpl(IView iView){
        miView = iView;
        miModel = new IModelImpl();
    }
    @Override
    public void requestData(String url, Map<String, String> params, Class clazz) {
        miModel.requestData(url, params, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                miView.success(data);
            }
        });
    }
    public  void  des(){
        if (miModel!=null){
            miModel=null;
        }
        if (miView!=null){
            miView=null;
        }
    }
}
