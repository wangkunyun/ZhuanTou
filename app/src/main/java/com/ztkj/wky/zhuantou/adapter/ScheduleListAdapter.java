package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.oa.AddSchedule;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ScheduleListBean;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    private List<ScheduleListBean.DataBean> data;
    private Context context;

    public ScheduleListAdapter(List<ScheduleListBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_schedelelist, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_content.setText(data.get(i).getRemarks());
        String start_time = data.get(i).getStart_time();
        viewHolder.tv_time.setText(start_time.substring(start_time.indexOf(" ")));
        viewHolder.item_rl_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, AddSchedule.class);
                intent.putExtra("tag", "1");
                intent.putExtra("sc_id",data.get(i).getSc_id());
                intent.putExtra("startTime", data.get(i).getStart_time());
                intent.putExtra("endTime", data.get(i).getEnd_time());
                intent.putExtra("content", data.get(i).getRemarks());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_rl_click;
        private TextView tv_time, tv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_rl_click = itemView.findViewById(R.id.item_rl_click);
            tv_time = itemView.findViewById(R.id.item_scheduleTime);
            tv_content = itemView.findViewById(R.id.item_scheduleContent);
        }
    }
}
