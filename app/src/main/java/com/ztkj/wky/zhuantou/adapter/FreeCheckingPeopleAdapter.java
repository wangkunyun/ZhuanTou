package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.util.List;

public class FreeCheckingPeopleAdapter extends RecyclerView.Adapter<FreeCheckingPeopleAdapter.ViewHolder> {
    private Context context;
    private List<AddressBookBean.DataBean> data;

    public FreeCheckingPeopleAdapter(Context context, List<AddressBookBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_freecheckingpeople, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.freeCheckingName.setText(data.get(i).getName());
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .error(R.drawable.head_portrait);
        Glide.with(context).load(data.get(i).getHead())
                .apply(options)
                .into(viewHolder.freeCheckingHead);
        viewHolder.freeCheckingDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url(Contents.NOPUNCHINPEOPLE)
                        .addParams("phone", "13460720766")//SPUtils.getInstance().getString("phone")
                        .addParams("friend_phone", data.get(i).getPhone())
                        .addParams("cid", SPUtils.getInstance().getString("cid"))
                        .addParams("type", "0")
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {
                            Toast.makeText(context, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            data.remove(i);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends MyAdapter.ViewHolder {
        ImageView freeCheckingHead;
        TextView freeCheckingName, freeCheckingDel;

        public ViewHolder(View itemView) {
            super(itemView);
            freeCheckingHead = itemView.findViewById(R.id.freeCheckingHead);
            freeCheckingName = itemView.findViewById(R.id.freeCheckingName);
            freeCheckingDel = itemView.findViewById(R.id.freeCheckingDel);
        }
    }
}
