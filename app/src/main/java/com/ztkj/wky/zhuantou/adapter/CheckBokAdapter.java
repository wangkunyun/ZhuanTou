package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.MapBean;

import java.util.ArrayList;
import java.util.List;

public class CheckBokAdapter extends RecyclerView.Adapter<CheckBokAdapter.ViewHolder> {
    private Context context;
    private List<MapBean> mList;
    private CheckBox main_cb;

    public CheckBokAdapter(Context context, List<MapBean> mList, CheckBox main_cb) {
        this.context = context;
        this.mList = mList;
        this.main_cb = main_cb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.item_tv.setText(mList.get(i).getName());
        viewHolder.item_cb.setChecked(mList.get(i).getStatus());
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .error(R.drawable.head_portrait);
        Glide.with(context).load(mList.get(i).getHead())
                .apply(options)
                .into(viewHolder.item_imgHead);


        // 对复选框做监听
        viewHolder.item_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 是在修改list中的MapBean对象的状态
                mList.get(i).setStatus(isChecked);
                ArrayList<Boolean> ListState = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    ListState.add(mList.get(i).getStatus());
                }
                for (int i = 0; i < ListState.size(); i++) {
                    if (ListState.contains(false)) {
                        main_cb.setChecked(false);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_imgHead;
        private TextView item_tv;
        private CheckBox item_cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_imgHead = itemView.findViewById(R.id.item_imgHead);
            item_tv = itemView.findViewById(R.id.item_tv);
            item_cb = itemView.findViewById(R.id.item_cb);
        }
    }
}
