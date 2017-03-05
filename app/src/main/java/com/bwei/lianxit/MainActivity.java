package com.bwei.lianxit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import costomview.library.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String url="http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page=2&pagesize=10&sort=asc&time=1418745237";
    private Bean bean;
    private RecyclerView recyclerview;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String data= (String) msg.obj;
                Gson gson=new Gson();
                bean=gson.fromJson(data,Bean.class);
                setDtat(bean);
            }
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDara();
    }

    private void initDara() {
        OkHttpUtils.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                handler.obtainMessage(0,str).sendToTarget();
            }
        });
    }

    private void initView() {
         recyclerview=(RecyclerView) findViewById(R.id.recyclerview);
    }
    private void setDtat(Bean bean) {
        List<Bean.ResultBean.DataBean> list=bean.getResult().getData();
        Asdpter asdpter=new Asdpter(list,MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setAdapter(asdpter);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }
}
