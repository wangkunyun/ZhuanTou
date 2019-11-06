package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.chat.activity.NewFriendDetails;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AgreeFriendListBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.ViewHolder> {
    private Context context;
    private List<AgreeFriendListBean.DataBean> data;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token, phone;


    public NewFriendAdapter(Context context, List<AgreeFriendListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_nwe_friends, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        sharedPreferencesHelper = new SharedPreferencesHelper(context, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");

        viewHolder.tv_newFriendName.setText(data.get(i).getFriendname());

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(data.get(i).getFriendhead())
                .apply(options).into(viewHolder.img_newFriendHead);

        viewHolder.agree_newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("NewAdapter", "onClick: " + "点点点");
                OkHttpUtils.post().url(Contents.AGREENEWFRIEND)
                        .addParams("token", token)
                        .addParams("phone", phone)
                        .addParams("friend_phone", data.get(i).getFriendphone())
                        .addParams("types", "1")
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("NewAdapter", "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String errno = (String) jsonObject.get("errno");
                            if (errno.equals("200")) {
                                Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                                viewHolder.agree_newFriend.setVisibility(View.GONE);
                                viewHolder.tv_agree.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        viewHolder.rl_click_item_layout_newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewFriendDetails.class);
                intent.putExtra("head", data.get(i).getFriendhead());
                intent.putExtra("name", data.get(i).getFriendname());
                intent.putExtra("friend_phone", data.get(i).getFriendphone());
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
        private ImageView img_newFriendHead;
        private TextView tv_newFriendName;
        private Button agree_newFriend;
        private TextView tv_agree;
        private RelativeLayout rl_click_item_layout_newFriend;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_newFriendHead = itemView.findViewById(R.id.img_newFriendHead);
            tv_newFriendName = itemView.findViewById(R.id.tv_newFriendName);
            agree_newFriend = itemView.findViewById(R.id.agree_newFriend);
            tv_agree = itemView.findViewById(R.id.tv_agree);
            rl_click_item_layout_newFriend = itemView.findViewById(R.id.rl_click_item_layout_newFriend);
        }
    }
}
