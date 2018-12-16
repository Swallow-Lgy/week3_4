package com.example.dell.week3_4.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.week3_4.R;
import com.example.dell.week3_4.bean.GoodsBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Myadpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean.DataBean> mData;
    private Context mContext;

    public Myadpter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public void setmData(List<GoodsBean.DataBean> data) {
         mData.clear();
         if (data!=null){
             mData.addAll(data);
         }
         notifyDataSetChanged();
    }
    public void addmData(List<GoodsBean.DataBean> data) {

        if (data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.item,null);
        return new Viewolderone(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
          Viewolderone holder = (Viewolderone) viewHolder;
          holder.title.setText(mData.get(i).getTitle());
          holder.price.setText(mData.get(i).getPrice()+"");
          String images = mData.get(i).getImages();
          String[] split = images.split("\\|");
          List<String> list = Arrays.asList(split);
          Glide.with(mContext).load(list.get(0)).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class Viewolderone extends RecyclerView.ViewHolder{
        private TextView title,price;
        private ImageView icon;
        public Viewolderone(@NonNull View itemView) {
            super(itemView);
           icon =  itemView.findViewById(R.id.icon);
           title  = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }
}
