package com.ztkj.wky.zhuantou.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.NearSearcchBean;

import java.util.List;

public class MyAdapter8 extends RecyclerView.Adapter<MyAdapter8.ViewHolder>{
    private List<NearSearcchBean.DataBean> mData;
    private Context context;
    private String first;
    private SharedPreferencesHelper sharedPreferencesHelper;
    public MyAdapter8(List<NearSearcchBean.DataBean>  mData, Context context ,String first) {
        this.mData = mData;
        this.context = context;
        this.first = first;
    }

    @Override
    public MyAdapter8.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchn_item, parent, false);
        MyAdapter8.ViewHolder viewHolder = new MyAdapter8.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter8.ViewHolder holder, final int position) {

//        holder.textView.setText(mData.get(position).getState());
//        holder.textView2.setText(mData.get(position).getDatetime()+"  "+mData.get(position).getIntervaltime());
//        holder.textView3.setText(mData.get(position).getSpanlength());

//        holder.textView.setText(mData.get(position).getTitle());

        holder.textView.setText(mData.get(position).getCo_name());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper = new SharedPreferencesHelper(context, "anhua");
                sharedPreferencesHelper.put("co_id", mData.get(position).getCo_id());
                sharedPreferencesHelper.put("co_address", mData.get(position).getCo_name());
                Activity activity = (Activity) context;
                activity.finish();
//                if ("0".equals(first)){
//                    Intent intent = new Intent(context, NearActivity.class);
//                    intent.putExtra("co_id",mData.get(position).getCo_id());
//                    intent.putExtra("co_address",mData.get(position).getCo_address());
//                    context.startActivity(intent);
//                if (Activity.class.isInstance(context)) {
                    // 转化为activity，然后finish就行了

//                }
//                }
//                else {
////                    Intent intent = new Intent(context, NearActivity.class);
////                    intent.putExtra("co_id",mData.get(position).getCo_id());
////                    intent.putExtra("co_address",mData.get(position).getCo_address());
////                    context.startActivity(intent);
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.sean_item1_zx);
            relativeLayout = itemView.findViewById(R.id.sean_item1_bg);

        }
    }
}


