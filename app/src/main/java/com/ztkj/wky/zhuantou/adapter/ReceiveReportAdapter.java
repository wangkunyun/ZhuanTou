package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReadReportDetails;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReadReportMidDetails;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReadReportTidDetails;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReadWidReportDetails;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ReceiveReportBean;

import java.util.List;

public class ReceiveReportAdapter extends RecyclerView.Adapter<ReceiveReportAdapter.ViewHolder> {
    private Context context;
    private List<ReceiveReportBean.DataBean> data;

    public ReceiveReportAdapter(Context context, List<ReceiveReportBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_re_show_report, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        viewHolder.item_report_name.setText(data.get(i).getUsername());
        viewHolder.item_report_time.setText(data.get(i).getAddtime());
        viewHolder.item_report_content.setText(data.get(i).getSummary());
        Glide.with(context).load(data.get(i).getHead())
                .apply(options)
                .into(viewHolder.item_report_img_head);
        viewHolder.item_report_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = data.get(i).getType();
                if (type.equals("did")) {
                    Intent intent = new Intent(context, ReadReportDetails.class);
                    intent.putExtra("report_type", data.get(i).getType());
                    intent.putExtra("report_id", data.get(i).getId());
                    intent.putExtra("report_name", data.get(i).getUsername());
                    intent.putExtra("report_head", data.get(i).getHead());
                    context.startActivity(intent);
                } else if (type.equals("wid")) {
                    Intent intent = new Intent(context, ReadWidReportDetails.class);
                    intent.putExtra("report_type", data.get(i).getType());
                    intent.putExtra("report_id", data.get(i).getId());
                    intent.putExtra("report_name", data.get(i).getUsername());
                    intent.putExtra("report_head", data.get(i).getHead());
                    context.startActivity(intent);
                } else if (type.equals("mid")) {
                    Intent intent = new Intent(context, ReadReportMidDetails.class);
                    intent.putExtra("report_type", data.get(i).getType());
                    intent.putExtra("report_id", data.get(i).getId());
                    intent.putExtra("report_name", data.get(i).getUsername());
                    intent.putExtra("report_head", data.get(i).getHead());
                    context.startActivity(intent);
                } else if (type.equals("tid")) {
                    Intent intent = new Intent(context, ReadReportTidDetails.class);
                    intent.putExtra("report_type", data.get(i).getType());
                    intent.putExtra("report_id", data.get(i).getId());
                    intent.putExtra("report_name", data.get(i).getUsername());
                    intent.putExtra("report_head", data.get(i).getHead());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_report_name, item_report_time, item_report_content;
        private ImageView item_report_img_head;
        private RelativeLayout item_report_relative;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_report_name = itemView.findViewById(R.id.item_report_name);
            item_report_time = itemView.findViewById(R.id.item_report_time);
            item_report_content = itemView.findViewById(R.id.item_report_content);
            item_report_img_head = itemView.findViewById(R.id.item_report_img_head);
            item_report_relative = itemView.findViewById(R.id.item_report_relative);


        }
    }
}
