package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.EvectionDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.GoOutDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.LeaveDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.OutWorkDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.OverTimeDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.PayForDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.ReimbursementDetails;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.UseCarDetials;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials.UseSealDetails;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AllApplyBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AllApplyAdapter extends RecyclerView.Adapter<AllApplyAdapter.ViewHolder> {
    private Context context;
    private List<AllApplyBean.DataBean> data;
    private String discover;

    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_apply;
    private String token, is_appover;


    public AllApplyAdapter(Context context, List<AllApplyBean.DataBean> data, String discover) {
        this.context = context;
        this.data = data;
        this.discover = discover;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_re_show_apply, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(context, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        sp_apply = new SharedPreferencesHelper(context, "apply");
        is_appover = (String) sp_apply.getSharedPreference("apply_isAppover", "");

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(data.get(i).getHead())
                .apply(options)
                .into(viewHolder.head);
        viewHolder.title.setText(data.get(i).getUsername());
        viewHolder.time.setText(data.get(i).getAddtime());
        viewHolder.why.setText(data.get(i).getReason());

        //判断是否是从审批进来的 0是请假 1是审批
        if (is_appover.equals("0")) {
            viewHolder.is_state.setVisibility(View.GONE);
        } else if (is_appover.equals("1")) {
            if (discover.equals("3")) { //判断从未审批进来则切换接口
                viewHolder.is_state.setVisibility(View.GONE);
            } else {
                viewHolder.is_state.setVisibility(View.VISIBLE);
            }
            if (data.get(i).getStatus().equals("0")) {
                viewHolder.is_state.setText("通过");
                viewHolder.is_state.setTextColor(Color.parseColor("#65C9D2"));
                viewHolder.is_state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //通过的点击事件
                        OkHttpUtils.post()
                                .url(Contents.EXAMINE)
                                .addParams("token", token)
                                .addParams("type", data.get(i).getType())
                                .addParams("id", data.get(i).getId())
                                .addParams("status", "1")
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String errno = (String) jsonObject.get("errno");
                                    if (errno.equals("200")) {
                                        viewHolder.is_state.setText("已通过");
                                        viewHolder.is_state.setBackgroundResource(R.drawable.bg_is_state_grey);
                                        viewHolder.is_state.setTextColor(Color.parseColor("#E5E5E5"));
                                        viewHolder.state.setText("已通过");
                                        viewHolder.state.setTextColor(Color.parseColor("#65C9D2"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });
            } else if (data.get(i).getStatus().equals("1")) {
                viewHolder.is_state.setText("已通过");
                viewHolder.is_state.setBackgroundResource(R.drawable.bg_is_state_grey);
                viewHolder.is_state.setTextColor(Color.parseColor("#E5E5E5"));
                viewHolder.state.setTextColor(Color.parseColor("#65C9D2"));
            } else if (data.get(i).getStatus().equals("2")) {
                viewHolder.is_state.setText("已拒绝");
                viewHolder.is_state.setBackgroundResource(R.drawable.bg_is_state_grey);
                viewHolder.state.setTextColor(Color.parseColor("#EC4D4D"));
            }
        }


        if (data.get(i).getStatus().equals("0")) {
            viewHolder.state.setText("待审批");
            viewHolder.state.setTextColor(Color.parseColor("#EC754D"));
        } else if (data.get(i).getStatus().equals("1")) {
            viewHolder.state.setText("已通过");
            viewHolder.state.setTextColor(Color.parseColor("#65C9D2"));
        } else if (data.get(i).getStatus().equals("2")) {
            viewHolder.state.setText("已拒绝");
            viewHolder.state.setTextColor(Color.parseColor("#EC4D4D"));
        }

        viewHolder.rl_item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.get(i).getType()) {
                    case "vid": //用车
                        intent = new Intent(context, UseCarDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "pid": //用印
                        intent = new Intent(context, UseSealDetails.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "rid": //费用报销
                        intent = new Intent(context, ReimbursementDetails.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "oid": //加班申请
                        intent = new Intent(context, OverTimeDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "lid": //请假申请
                        intent = new Intent(context, LeaveDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "gid": //外出
                        intent = new Intent(context, GoOutDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "fid": //外勤
                        intent = new Intent(context, EvectionDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "bt_id": //出差
                        intent = new Intent(context, OutWorkDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                    case "paid":  //付款
                        intent = new Intent(context, PayForDetials.class);
                        if (is_appover.equals("1")) {
                            intent.putExtra("discover", discover);
                        } else {
                            intent.putExtra("discover", "4");
                        }
                        intent.putExtra("name", data.get(i).getUsername());
                        intent.putExtra("id", data.get(i).getId());
                        intent.putExtra("head", data.get(i).getHead());
                        context.startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, time, why, state, is_state;
        private ImageView head;
        private RelativeLayout rl_item_click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_apply_name);
            time = itemView.findViewById(R.id.item_apply_time);
            why = itemView.findViewById(R.id.item_apply_why);
            state = itemView.findViewById(R.id.item_apply_state);
            rl_item_click = itemView.findViewById(R.id.item_apply_relative);
            head = itemView.findViewById(R.id.item_apply_img_head);
            is_state = itemView.findViewById(R.id.item_apply_isState);
        }
    }
}
