package com.ztkj.wky.zhuantou.isMy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.maning.updatelibrary.InstallUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.mine.ForgetPayWordActivity;
import com.ztkj.wky.zhuantou.Activity.mine.NameRenZhengActivity;
import com.ztkj.wky.zhuantou.Activity.mine.PayWord3Activity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.IsPayPassWordBean;
import com.ztkj.wky.zhuantou.bean.UpdateBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SzActivity extends AppCompatActivity {

    @BindView(R.id.sz_goback)
    ImageView szGoback;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.sz_tab1)
    RelativeLayout szTab1;
    //    @BindView(R.id.sz_tab2)
//    RelativeLayout szTab2;
    @BindView(R.id.sz_tab3)
    RelativeLayout szTab3;
    @BindView(R.id.gerenbg)
    LinearLayout gerenbg;
    @BindView(R.id.sz_tcdl)
    Button szTcdl;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.amendPayPassWord)
    RelativeLayout amendPayPassWord;
    @BindView(R.id.forgetPayPassWord)
    RelativeLayout forgetPayPassWord;
    @BindView(R.id.tv_amendPayPassWord)
    TextView tvAmendPayPassWord;
    @BindView(R.id.app_version)
    TextView appVersion;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_Create_team;
    private String uid, token;
    private Intent intent;
    private String url = StringUtils.jiekouqianzui + "User/androidVersion";
    private static final String savePath = "/sdcard/小砖开门/";
    private static final String saveFileName = savePath + "小砖开门.apk";
    private String TAG = "SzActivity";
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sz);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(SzActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        sp_Create_team = new SharedPreferencesHelper(SzActivity.this, "Create_team");
        appVersion.setText(StringUtils.string_version);
        request();
    }

    private void request() {
        OkHttpUtils.post().url(Contents.ISPAYPASSWORD)
                .addParams("token", token)
                .addParams("uid", uid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                IsPayPassWordBean isPayPassWordBean = GsonUtil.gsonToBean(response, IsPayPassWordBean.class);
                type = isPayPassWordBean.getData().getType();
                SPUtils.getInstance().put("IsPayPassWord", type);
                if (type.equals("0")) {
                    tvAmendPayPassWord.setText("设置支付密码");
                }
            }
        });
    }

    private void popuinit(String s, String s1, final String s2, final String s3) {
        View contentView = LayoutInflater.from(SzActivity.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(SzActivity.this).inflate(R.layout.activity_sz, null);

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

                if (s2.equals("立即更新")) {
                    Uri uri = Uri.parse("https://www.pgyer.com/hjNa");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
//                    InstallUtils.with(SzActivity.this)
//                            //必须-下载地址
//                            .setApkUrl(s3)
//                            //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
//                            .setApkPath(saveFileName)
//                            //非必须-下载回调
//                            .setCallBack(new InstallUtils.DownloadCallBack() {
//                                @Override
//                                public void onStart() {
//                                    //下载开始
//                                    Toast.makeText(SzActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onComplete(String path) {
//                                    //下载完成
//                                    //先判断有没有安装权限---适配8.0
//                                    InstallUtils.checkInstallPermission(SzActivity.this, new InstallUtils.InstallPermissionCallBack() {
//                                        @Override
//                                        public void onGranted() {
//                                            //去安装APK
//                                            getin(s2);
//                                        }
//
//                                        @Override
//                                        public void onDenied() {
//                                            //弹出弹框提醒用户
//                                            AlertDialog alertDialog = new AlertDialog.Builder(SzActivity.this)
//                                                    .setTitle("温馨提示")
//                                                    .setMessage("必须授权才能安装APK，请设置允许安装")
//                                                    .setNegativeButton("取消", null)
//                                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            //打开设置页面
//                                                            InstallUtils.openInstallPermissionSetting(SzActivity.this, new InstallUtils.InstallPermissionCallBack() {
//                                                                @Override
//                                                                public void onGranted() {
//                                                                    //安装APK
//                                                                    getin(s2);
//                                                                }
//
//                                                                @Override
//                                                                public void onDenied() {
//                                                                    //还是不允许让他退出吧
//                                                                    Toast.makeText(SzActivity.this, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
//                                                                }
//                                                            });
//                                                        }
//                                                    })
//                                                    .create();
//                                            alertDialog.show();
//                                        }
//                                    });
//                                }
//
//                                @SuppressLint("SetTextI18n")
//                                @Override
//                                public void onLoading(long total, long current) {
//                                    int progress = (int) (current * 100 / total);
//                                    //下载开始
//                                    View contentView = LayoutInflater.from(SzActivity.this).inflate(R.layout.pp_progress, null);
//                                    //设置popuwindow是在父布局的哪个地方显示
//                                    backgroundAlpha(0.2f);
//                                    //下面是p里面的东西
//                                    TextView progresstv = contentView.findViewById(R.id.ppt_tv1);
//                                    Button ppt_btn1 = contentView.findViewById(R.id.ppt_btn1);
//                                    final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//                                    window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//                                    window.setOutsideTouchable(true);
//                                    window.setTouchable(true);
//                                    View rootview = LayoutInflater.from(SzActivity.this).inflate(R.layout.activity_all, null);
//                                    progresstv.setText("已下载 " + progress + "%");
//                                    ppt_btn1.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            backgroundAlpha(1f);
//                                            cancle();
//                                            window.dismiss();
//                                        }
//                                    });
//                                    window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
//                                    window.dismiss();
//                                }
//
//                                @Override
//                                public void onFail(Exception e) {
//                                    //下载失败
//                                    Toast.makeText(SzActivity.this, "下载失败", Toast.LENGTH_SHORT);
//                                }
//
//                                @Override
//                                public void cancle() {
//                                    //下载取消
//                                    Toast.makeText(SzActivity.this, "下载取消", Toast.LENGTH_SHORT);
//                                }
//                            })//开始下载
//                            .startDownload();
////                //取消下载
////                InstallUtils.cancleDownload();
////                //是否正在下载
//                    boolean downloading = InstallUtils.isDownloading();
//                    if (downloading == true) {
//                        Log.d("restoo", "正在下载");
//                        Toast.makeText(SzActivity.this, "正在下载", Toast.LENGTH_SHORT);
//                    } else {
//                        Log.d("restoo", "没有正在下载");
//                        Toast.makeText(SzActivity.this, "没有在下载", Toast.LENGTH_SHORT);
//                    }
//
//                    //单独设置下载监听
//                    InstallUtils.setDownloadCallBack(new InstallUtils.DownloadCallBack() {
//                        @Override
//                        public void onStart() {
//                            Toast.makeText(SzActivity.this, "开始", Toast.LENGTH_SHORT);
//                        }
//
//                        @Override
//                        public void onComplete(String path) {
//                            Toast.makeText(SzActivity.this, "成了？", Toast.LENGTH_SHORT);
//                        }
//
//                        @Override
//                        public void onLoading(long total, long current) {
//                            Toast.makeText(SzActivity.this, (int) (current * 100 / total) + "%", Toast.LENGTH_SHORT);
//                        }
//
//                        @Override
//                        public void onFail(Exception e) {
//                            Toast.makeText(SzActivity.this, "失败", Toast.LENGTH_SHORT);
//                        }
//
//                        @Override
//                        public void cancle() {
//                            Toast.makeText(SzActivity.this, "取消", Toast.LENGTH_SHORT);
//                        }
//                    });


                } else if (s2.equals("确定")) {

                }

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


    private void getin(final String s2) {
//        安装APK
        /**
         * 安装APK工具类
         * @param activity       上下文
         * @param filePath      文件路径
         * @param callBack      安装界面成功调起的回调
         */
        InstallUtils.installAPK(SzActivity.this, saveFileName, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(SzActivity.this, "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(SzActivity.this, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                InstallUtils.installAPKWithBrower(SzActivity.this, s2);
            }
        });
    }

    private void popuinit2() {
        View contentView = LayoutInflater.from(SzActivity.this).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(SzActivity.this).inflate(R.layout.activity_sz, null);
        ptextView.setText("是否退出登录");
        pbutton.setText("取消");
        pbutton2.setText("确认");
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
                JPushInterface.deleteAlias(SzActivity.this, Integer.parseInt(uid));
//                String regid = (String) sharedPreferencesHelper.getSharedPreference("regId", "");
                sharedPreferencesHelper.clear();
                sp_Create_team.clear();
//                sp_create.clear();


//                sharedPreferencesHelper.put("regId", regid);
                ActivityManager.getInstance().exit();
                intent = new Intent(SzActivity.this, LoginActivity.class);
                startActivity(intent);
//                finish();


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


    @OnClick({R.id.sz_goback, R.id.sz_tab1, R.id.sz_tab3, R.id.sz_tcdl, R.id.amendPayPassWord, R.id.forgetPayPassWord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sz_goback:
                finish();
                break;
            case R.id.sz_tab1:
                break;
//            case R.id.sz_tab2:
//                Toast.makeText(SzActivity.this, "暂未开发", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.sz_tab3:
                OkHttpUtils.post()
                        .url(url)
                        .addParams("token", token)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                            }

                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                UpdateBean updateBean = gson.fromJson(response, UpdateBean.class);
                                if ("200".equals(updateBean.getErrno())) {
                                    if (StringUtils.string_version.equals(updateBean.getData().getAndroid_number())) {
                                        popuinit("当前版本已是最新版本", "取消 ", "确定", updateBean.getData().getAndroid_address());
                                    } else {
                                        //zhejiushi s3 下载地址没问题
                                        Log.e(TAG, "onResponse: " + updateBean.getData().getAndroid_address());
                                        popuinit("当前非最新版本，是否需要更新", "取消 ", "立即更新", updateBean.getData().getAndroid_address());
                                    }
                                } else if (updateBean.getErrno().equals("666666")) {
                                    Toast.makeText(SzActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                                    JPushInterface.deleteAlias(SzActivity.this, Integer.parseInt(uid));
                                    sharedPreferencesHelper.clear();
                                    ActivityManager.getInstance().exit();
                                    intent = new Intent(SzActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }//

                            }
                        });
                break;

            case R.id.sz_tcdl:
                popuinit2();
                break;
            case R.id.amendPayPassWord:
                if (type.equals("0")) {
                    tvAmendPayPassWord.setText("设置支付密码");
                    intent = new Intent(SzActivity.this, NameRenZhengActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(SzActivity.this, PayWord3Activity.class);
                    intent.putExtra("intentTag", "0");
                    startActivity(intent);
                }

                break;
            case R.id.forgetPayPassWord://忘记密码
                intent = new Intent(SzActivity.this, ForgetPayWordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
