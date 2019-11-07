package com.ztkj.wky.zhuantou.isMy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.LikeCompanyBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EwmApplyActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.company_name)
    EditText companyName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.click_sex)
    RelativeLayout clickSex;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.likeCompany)
    RecyclerView likeCompany;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String token, username;
    private String TAG = "EwmApplyActivity";
    private String searchtv = " ";
    private List<LikeCompanyBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewm_apply);
        ButterKnife.bind(this);
        //获取token和id
        layoutTitleTv.setText("通行二维码");
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        username = (String) sharedPreferencesHelper.getSharedPreference("realname", "");


        companyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                register();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    /**
     * 模糊查询
     */
    private void register() {
        if ("".equals(companyName.getText().toString())) {
            searchtv = " ";
        } else {
            searchtv = companyName.getText().toString();
        }
        OkHttpUtils.post()
                .url(Contents.LIKECOMPANY)
                .addParams("cname", searchtv)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        LikeCompanyBean likeCompanyBean = GsonUtil.gsonToBean(response, LikeCompanyBean.class);
                        if (likeCompanyBean.getErrno().equals("200")) {
                            data = likeCompanyBean.getData();
                            if (data.size() == 0) {
                                likeCompany.setVisibility(View.VISIBLE);
                            }
                            LikeAdapter likeAdapter = new LikeAdapter();
                            likeCompany.setLayoutManager(new LinearLayoutManager(EwmApplyActivity.this));
                            likeCompany.setAdapter(likeAdapter);
                        }
                    }
                });
    }

    @OnClick({R.id.click_sex, R.id.update, R.id.layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
            case R.id.click_sex:
                popuinit2();
                break;
            case R.id.update:
                /**
                 *  提交信息
                 */
                if (companyName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入所属企业", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sex.getText().toString().equals("请选择")) {
                    Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post().url(Contents.SAVEEWMINFO)
                        .addParams("token", token)
                        .addParams("uid", uid)
                        .addParams("companyname", companyName.getText().toString())
                        .addParams("username", name.getText().toString())
                        .addParams("sex", sex.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String errno = (String) jsonObject.get("errno");
                            String errmsg = (String) jsonObject.get("errmsg");
                            if (errno.equals("200")) {
                                Toast.makeText(EwmApplyActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(EwmApplyActivity.this, errmsg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
        }
    }

    private void popuinit2() {
        View contentView = LayoutInflater.from(EwmApplyActivity.this).inflate(R.layout.sex_pp, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.4f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
//        final EditText editText = contentView.findViewById(R.id.ppsz_edt);
        final TextView pbutton = contentView.findViewById(R.id.sexpp_t1);
        final TextView pbutton2 = contentView.findViewById(R.id.sexpp_t2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(EwmApplyActivity.this).inflate(R.layout.activity_ge_ren, null);
//        ptextView.setText("5338-9498");
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText(pbutton.getText());
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText(pbutton2.getText());
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


    class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(EwmApplyActivity.this).inflate(R.layout.item_layout_likecompany, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.itemTitle.setText(data.get(i).getCname());
            viewHolder.rlClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    companyName.setText(data.get(i).getCname());
                    likeCompany.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemTitle;
            private RelativeLayout rlClick;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemTitle = itemView.findViewById(R.id.item_title);
                rlClick = itemView.findViewById(R.id.rl_click);
            }
        }
    }
}
