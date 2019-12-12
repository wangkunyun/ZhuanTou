package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.NoticeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.reView)
    RecyclerView reView;

    private String TAG = "NotificationActivity";
    private List<NoticeBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        layoutTitleTv.setText("通知");
        request();

    }

    private void request() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.selectNotice)
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("page", "1")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                if (response != null) {
                    NoticeBean noticeBean = GsonUtil.gsonToBean(response, NoticeBean.class);
                    data = noticeBean.getData();
                    if (data != null && data.size() > 0) {
                        reView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
                        reView.setAdapter(new mAdapter());
                    }
                }
            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NotificationActivity.class);
        context.startActivity(starter);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(NotificationActivity.this).inflate(R.layout.item_layout_notification, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Log.e(TAG, "onBindViewHolder: " + data.get(i).getSn_content());
            viewHolder.tv_notificationTime.setText(data.get(i).getSn_time());
            viewHolder.tv_notificationType.setText(data.get(i).getSn_name());
            viewHolder.tv_notificationContent.setText(data.get(i).getSn_content());
        }
        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_notificationTime, tv_notificationType, tv_notificationContent;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_notificationTime = itemView.findViewById(R.id.tv_notificationTime);
                tv_notificationType = itemView.findViewById(R.id.tv_notificationType);
                tv_notificationContent = itemView.findViewById(R.id.tv_notificationContent);

            }
        }
    }
}
