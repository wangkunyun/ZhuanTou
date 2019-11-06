package com.ztkj.wky.zhuantou.Activity.oa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CreateTeamBean;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class Create_Team extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.team_name)
    EditText teamName;
    @BindView(R.id.team_scale)
    RelativeLayout teamScale;
    @BindView(R.id.guide)
    RelativeLayout guide;
    @BindView(R.id.district)
    RelativeLayout district;
    @BindView(R.id.create)
    Button create;
    @BindView(R.id.tv_team_scale)
    TextView tvTeamScale;
    @BindView(R.id.tv_team_guide)
    TextView tvTeamGuide;
    private String TAG = "Create_Team";
    private String dialogtips;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp_scale, sp_guide, sp_create_team;
    private String uid;
    private String token;
    private String cid;
    private String phone;
    private Intent intent, teamscale_intent;
    private String create_team_scale;
    private String create_team_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__team);
        ButterKnife.bind(this);


        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
//        获取规模
        sp_scale = new SharedPreferencesHelper(this, "Create_team");
        create_team_scale = (String) sp_scale.getSharedPreference("create_team_scale", "");
//        获取行业
        sp_guide = new SharedPreferencesHelper(this, "Create_team");
        create_team_guide = (String) sp_guide.getSharedPreference("create_team_guide", "");
//        Toast.makeText(this, create_team_scale, Toast.LENGTH_SHORT).show();
        //清除sp里的规模和人数
//        sp_scale.clear();
//        sp_guide.clear();
        if (!create_team_scale.isEmpty()) { //判断sp是否有值 有的话则赋值
            tvTeamScale.setText(create_team_scale);
        }
        if (!create_team_guide.isEmpty()) { //判断sp是否有值 有的话则赋值
            tvTeamGuide.setText(create_team_guide);
        }


    }


    @OnClick({R.id.back, R.id.team_scale, R.id.guide, R.id.district, R.id.create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
//                intent = new Intent(Create_Team.this, N4Fragment.class);
//                startActivity(intent);
                finish();
                break;
            case R.id.team_scale:
                //点击跳转到详情页
                intent = new Intent(Create_Team.this, Team_Scale.class);
                startActivity(intent);
                finish();
                break;
            case R.id.guide:
                //点击跳转到详情页
                intent = new Intent(Create_Team.this, Team_Guide.class);
                startActivity(intent);
                finish();
                break;
            case R.id.district:
                break;
            case R.id.create:
                if (teamName.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入团队名称", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvTeamScale.getText().toString().equals("请选择")) {
                    Toast.makeText(this, "请输入团队规模", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvTeamGuide.getText().toString().equals("请选择")) {
                    Toast.makeText(this, "请输入所属行业", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    request();
                }
                break;
        }
    }

    private void request() {
        Log.e(TAG, "request: cid=====" + cid);
        String str_team_name = teamName.getText().toString();
        Log.e(TAG, "上传的团队名称" + str_team_name);
        OkHttpUtils.post()
                .url(Contents.CREATETEAM)
                .addParams("team_name", str_team_name)
                .addParams("token", token)
                .addParams("team_size", tvTeamScale.getText().toString())
                .addParams("team_industry", tvTeamGuide.getText().toString())
                .addParams("cid", cid)
                .addParams("phone", phone)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                CreateTeamBean createTeamBean = new Gson().fromJson(response, CreateTeamBean.class);
                if (createTeamBean.getErrno().equals("200")) {
                    //将团队名称存入sp
                    sp_create_team.put("Create_team_name", createTeamBean.getData().getTeam_name());
                    sp_create_team.put("Create_team_jurisdiction", createTeamBean.getData().getJurisdiction());
                    Log.e(TAG, "刚存入的团队权限" + sp_create_team.getSharedPreference("Create_team_jurisdiction", ""));
                    Log.e(TAG, "刚存入的团队名称" + sp_create_team.getSharedPreference("Create_team_name", ""));

                    //创建成功提示前往企业认证
                    dialogtips = "已成功创建【" + teamName.getText().toString() + "】是否前往企业认证,获得更优质服务?";
                    popuinit(dialogtips, "取消 ", "前往");
                } else if (createTeamBean.getErrno().equals("666666")) {
                    Toast.makeText(Create_Team.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(Create_Team.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(Create_Team.this, LoginActivity.class);
                    startActivity(intent);
//                            getActivity().finish();
                } else {

                }
            }
        });
    }


    private void popuinit(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(Create_Team.this).inflate(R.layout.pp_create, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(Create_Team.this).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 前往企业认证
                 */
                intent = new Intent(Create_Team.this, Enterprise.class);
                startActivity(intent);
                finish();
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
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
