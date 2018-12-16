package com.example.dell.week3_4.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Button;

import com.example.dell.week3_4.R;
import com.example.dell.week3_4.adpter.Myadpter;
import com.example.dell.week3_4.bean.GoodsBean;
import com.example.dell.week3_4.presenter.IPresrnterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView{
    private IPresrnterImpl miPresrnter;
    private String url="http://www.zhaoapi.cn/product/searchProducts";
    private int page=1;
    private Myadpter myadpter;
    private XRecyclerView xRecyclerView,xRecyclerView1;
    private LinearLayoutManager linearLayoutManager;
   private int mSpance = 2;
    private GridLayoutManager gridLayoutManager;
    private Button qh;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qh = findViewById(R.id.qh);
        qh.setOnClickListener(this);
        xRecyclerView = findViewById(R.id.xrecyc);
        xRecyclerView1 = findViewById(R.id.xrecyc1);
        myadpter = new Myadpter(this);
        initView();
        initLinear();
        initGrid();

    }
    //初始化 IPresrnterImpl
    public void initView(){
       miPresrnter = new IPresrnterImpl(this);
    }
    //展示reccler
    private void initLinear() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(myadpter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
         xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
             @Override
             public void onRefresh() {
                 page=1;
                 initdata();
                   xRecyclerView.refreshComplete();
             }

             @Override
             public void onLoadMore() {
                 initdata();
                 xRecyclerView.loadMoreComplete();
             }
         });
        initdata();
    }
    private void initGrid() {
        gridLayoutManager = new GridLayoutManager(this,mSpance);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView1.setLayoutManager(gridLayoutManager);
        xRecyclerView1.setAdapter(myadpter);
        xRecyclerView1.setLoadingMoreEnabled(true);
        xRecyclerView1.setPullRefreshEnabled(true);
        xRecyclerView1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initdata();
                xRecyclerView1.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                initdata();
                xRecyclerView1.loadMoreComplete();
            }
        });
        initdata();
    }
    private void initdata() {
        HashMap<String,String> map = new HashMap<>();
        map.put("keywords","手机");
        map.put("page",page+"");
        map.put("sort","0");
        miPresrnter.requestData(url,map,GoodsBean.class);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.qh:
                 if (i%2==0){
                     xRecyclerView.setVisibility(View.VISIBLE);
                     xRecyclerView1.setVisibility(View.INVISIBLE);
                 }
                 else {
                     xRecyclerView1.setVisibility(View.VISIBLE);
                     xRecyclerView.setVisibility(View.INVISIBLE);
                 }
                 i++;
                 break;
                 default:
                    break;
        }
    }

    @Override
    public void success(Object data) {
        GoodsBean bean = (GoodsBean) data;
        List<GoodsBean.DataBean> data1 = bean.getData();
        if (page==1){
              myadpter.setmData(data1);
          }
          else {
            myadpter.addmData(data1);
        }
        page++;
    }
}
