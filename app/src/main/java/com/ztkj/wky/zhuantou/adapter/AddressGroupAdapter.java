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
import com.ztkj.wky.zhuantou.Activity.chat.Constant;
import com.ztkj.wky.zhuantou.Activity.chat.ECChatAcitivty;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.QunListBean;

import java.util.List;

public class AddressGroupAdapter extends RecyclerView.Adapter<AddressGroupAdapter.ViewHolder> {


    private List<QunListBean.DataBean.StayGroupBean> stayGroup;
    private Context context;

    public AddressGroupAdapter(List<QunListBean.DataBean.StayGroupBean> stayGroup, Context context) {
        this.stayGroup = stayGroup;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_re_address, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        viewHolder.item_tv_name.setText(stayGroup.get(i).getImgname());
        if (!stayGroup.get(i).getImglogo().equals("0")) {
            Glide.with(context).load(stayGroup.get(i).getImglogo())
                    .apply(options)
                    .into(viewHolder.item_img_head);
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ECChatAcitivty.class);
                intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
                intent.putExtra("userId", stayGroup.get(i).getGroupid() );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stayGroup.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_img_head;
        private TextView item_tv_name;
        private RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img_head = itemView.findViewById(R.id.item_img_head);
            item_tv_name = itemView.findViewById(R.id.item_tv_name);
            relativeLayout = itemView.findViewById(R.id.item_click);

        }
    }
}
