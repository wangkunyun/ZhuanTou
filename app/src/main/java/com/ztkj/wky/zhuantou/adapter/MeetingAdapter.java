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

import com.ztkj.wky.zhuantou.Activity.oa.report.AddMeeting;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.MeetingListBean;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {
    private Context context;
    private List<MeetingListBean.DataBean> data;

    public MeetingAdapter(Context context, List<MeetingListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_meeting, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
//        //获取汉字格式的时间戳
//        long l = Integer.parseInt(data.get(i).getTimes()) ;
//        String time = simpleDateFormat.format(l);
        String substring = data.get(i).getAddtime().substring(data.get(i).getAddtime().indexOf("-") + 1, data.get(i).getAddtime().indexOf(" "));
        viewHolder.tv_title.setText(data.get(i).getTitle());
        viewHolder.tv_time.setText(substring);
        viewHolder.tv_contens.setText(data.get(i).getContent());
        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddMeeting.class);
                intent.putExtra("tag", "1");
                intent.putExtra("title", data.get(i).getTitle());
                intent.putExtra("meid", data.get(i).getMeid());
                intent.putExtra("content", data.get(i).getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_time, tv_contens;
        private RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_tv_meeting_title);
            tv_time = itemView.findViewById(R.id.item_tv_meeting_time);
            tv_contens = itemView.findViewById(R.id.item_tv_meeting_content);
            rl = itemView.findViewById(R.id.rl_click);
        }
    }
}
