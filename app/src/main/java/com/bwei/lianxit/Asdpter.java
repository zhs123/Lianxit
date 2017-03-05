package com.bwei.lianxit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 1.类的用途
 * 2.@author:zhanghaisheng
 * 3.@2017/3/5
 */


public class Asdpter extends RecyclerView.Adapter<Asdpter.MyViewHolder>{
    public Asdpter(List<Bean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private List<Bean.ResultBean.DataBean> list;
    private Context context;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myviewholder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text1.setText(list.get(position).getHashId());
        holder.text2.setText(list.get(position).getContent());
        holder.text3.setText(list.get(position).getUnixtime()+"");
        holder.text4.setText(list.get(position).getUpdatetime()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        public MyViewHolder(View itemView) {
            super(itemView);
             text1 =(TextView) itemView.findViewById(R.id.text1);
             text2 =(TextView) itemView.findViewById(R.id.text2);
             text3 =(TextView) itemView.findViewById(R.id.text3);
             text4 =(TextView) itemView.findViewById(R.id.text4);
        }
    }

}
