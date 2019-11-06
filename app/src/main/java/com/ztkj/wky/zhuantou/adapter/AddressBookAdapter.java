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
import com.ztkj.wky.zhuantou.Activity.chat.activity.PersonalDetails;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;

import java.util.List;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.ViewHolder> {


    private List<AddressBookBean.DataBean> data;
    private Context context;

    public AddressBookAdapter(List<AddressBookBean.DataBean> data, Context context) {
        this.data = data;
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
        RequestOptions options = RequestOptions
                .bitmapTransform(roundedCorners)
                .error(R.drawable.head_portrait);
        viewHolder.item_tv_name.setText(data.get(i).getName());
        if (!data.get(i).getHead().equals("0")) {
            Glide.with(context).load(data.get(i).getHead())
                    .apply(options)
                    .into(viewHolder.item_img_head);
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "敬请期待！！！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PersonalDetails.class);
                intent.putExtra("friendTag", data.get(i).getTypes());
                intent.putExtra("name", data.get(i).getName());
                intent.putExtra("head", data.get(i).getHead());
                intent.putExtra("phone", data.get(i).getPhone());
                intent.putExtra("company", data.get(i).getCname());
                intent.putExtra("job", data.get(i).getPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
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
