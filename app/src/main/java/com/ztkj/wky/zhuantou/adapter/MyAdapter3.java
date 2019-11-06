package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.RvBean3;
import com.ztkj.wky.zhuantou.homepage.AllServiceActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ViewHolder>{
    private List<RvBean3.DataBean> mData;
    private List<String> listz1 = new ArrayList<>();
    private List<String> listz2 = new ArrayList<>();
    private List<String> listz3 = new ArrayList<>();

    private Context context;
    private SharedPreferencesHelper sharedPreferencesHelper;
    public MyAdapter3(List<RvBean3.DataBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_item3, parent, false);
        MyAdapter3.ViewHolder viewHolder = new MyAdapter3.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter3.ViewHolder holder, final int position) {

        Glide.with(context).load(mData.get(position).getElogo()).into(holder.img);
        holder.textView.setText(mData.get(position).getEname());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper = new SharedPreferencesHelper(context, "anhua");
                String zuijin1 = (String) sharedPreferencesHelper.getSharedPreference("zuijin1", "");
                String zuijin2  = (String) sharedPreferencesHelper.getSharedPreference("zuijin2", "");
                String zuijin3  = (String) sharedPreferencesHelper.getSharedPreference("zuijin3", "");
                Log.d("zuijin3",zuijin1+"+"+zuijin2+"+"+zuijin3);
                zuijin1 = zuijin1.replace("[","");
                zuijin1 = zuijin1.replace("]","");
                zuijin1 = zuijin1.replaceAll(" " ,"");
                zuijin2 = zuijin2.replace("[","");
                zuijin2 = zuijin2.replace("]","");
                zuijin2 = zuijin2.replaceAll(" " ,"");
                zuijin3 = zuijin3.replace("[","");
                zuijin3 = zuijin3.replace("]","");
                zuijin3 = zuijin3.replaceAll(" " ,"");
                Log.d("zuijin3",zuijin1+"+"+zuijin2+"+"+zuijin3);
                List<String> mdata1 = new ArrayList(Arrays.asList(zuijin1.split(",")));
                List<String> mdata2 = new ArrayList(Arrays.asList(zuijin2.split(",")));
                List<String> mdata3 = new ArrayList(Arrays.asList(zuijin3.split(",")));

                Log.d("bububub",mdata2.size()+"");

                if (mdata2.get(0).equals(mData.get(position).getEname())
                        ||mdata2.get(1).equals(mData.get(position).getEname())
                        ||mdata2.get(2).equals(mData.get(position).getEname())
                        ||mdata2.get(3).equals(mData.get(position).getEname())){
                    Log.d("bububu","不变");
                }else {
                    Log.d("bububu","变了");
                    mdata1.remove(0);
                    mdata2.remove(0);
                    mdata3.remove(0);
                    mdata1.add(mData.get(position).getEid());
                    mdata2.add(mData.get(position).getEname());
                    mdata3.add(mData.get(position).getElogo());

                    sharedPreferencesHelper.put("zuijin1",mdata1+"");
                    sharedPreferencesHelper.put("zuijin2",mdata2+"");
                    sharedPreferencesHelper.put("zuijin3",mdata3+"");
                }

                Intent intent = new Intent(context, AllServiceActivity.class);
                intent.putExtra("eid",mData.get(position).getEid()+"");
                intent.putExtra("ase","0");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView img;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            img  = itemView.findViewById(R.id.rv3_img1);
            textView = itemView.findViewById(R.id.rv3_tv1);
            relativeLayout = itemView.findViewById(R.id.item3_r1);

        }
    }
}
