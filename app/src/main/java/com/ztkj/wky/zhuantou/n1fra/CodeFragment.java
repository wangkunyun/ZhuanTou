package com.ztkj.wky.zhuantou.n1fra;

//style="?android:attr/borderlessButtonStyle"

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.MessageDialog;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.H5.UserInstructionsActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.Config;
import com.ztkj.wky.zhuantou.MyUtils.RandomUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ADBean;
import com.ztkj.wky.zhuantou.bean.EwmBean;
import com.ztkj.wky.zhuantou.isMy.EwmApplyActivity;
import com.ztkj.wky.zhuantou.isMy.FangKeActivity;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeFragment extends Fragment {


    @BindView(R.id.n2_sysm)
    TextView n2Sysm;
    @BindView(R.id.n2_ts)
    TextView n2Ts;
    @BindView(R.id.n2_ewm)
    ImageView n2Ewm;
    @BindView(R.id.n2_bg1)
    RelativeLayout n2Bg1;
    Unbinder unbinder;
    @BindView(R.id.n2_tvts)
    TextView n2Tvts;
    @BindView(R.id.n2_refresh)
    ImageView n2Refresh;
    @BindView(R.id.n2_fangke)
    Button n2Fangke;
    @BindView(R.id.n2_tips)
    TextView n2Tips;
    @BindView(R.id.lin_wait_erweima_apply)
    LinearLayout linWaitErweimaApply;
    private Intent intent;
    private String url = StringUtils.jiekouqianzui + "Passageway/passagewayQrCode";
    private String url2 = StringUtils.jiekouqianzui + "Passageway/jurisdictionAuthority";
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String token;
    private Bitmap bitmap = null;
    private String p = null;
    private boolean flag = false;//标志位，用来标识是否应该通知主线程或者其他对象改做事情了
    private String logcode = " ";
    private String TAG = "CodeFragment";
    private String errno;  //判断返回时间
    private String strType = "0"; //判断是否返回接口
    private String propertyPhone;
    private int num = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_n2, container, false);
        unbinder = ButterKnife.bind(this, view);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
//        gi();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Contents.strExit = "0";
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        gi();
    }

//    private void setLight(Activity context, int brightness) {
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
//        context.getWindow().setAttributes(lp);
//    }


    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: gi" + response);
                        Gson gson = new Gson();
                        EwmBean ewmBean = gson.fromJson(response, EwmBean.class);
                        propertyPhone = ewmBean.getData().getPropertyPhone();
                        Log.d("lue1", response);
                        errno = ewmBean.getErrno();
                        if (errno.equals("200")) {
                            logcode = ewmBean.getData().getCode();
                            Bitmap mBitmap = CodeUtils.createImage(ewmBean.getData().getCode(), 400, 400, null);
                            n2Ewm.setImageBitmap(mBitmap);
                            n2Ewm.setAlpha(1f);
                            n2Ts.setText("");
                            n2Tips.setText(ewmBean.getData().getTips());

                            //长连接请求广告
                            requestAD();


                        } else if (errno.equals("222") || errno.equals("202")) { //没有权限请审核
                            n2Tvts.setText("");
                            n2Ts.setText("对不起，您暂时没有该权限；请点击\n" +
                                    "下方按钮申请权限。");
                            n2Fangke.setText("申请通行权限");


                        } else if (errno.equals("333")) {    //正在审核
                            n2Tvts.setText("");
                            n2Ts.setText("");
                            linWaitErweimaApply.setVisibility(View.VISIBLE);
                            n2Fangke.setText("重新提交");
                            n2Fangke.setBackgroundResource(R.drawable.circle_grey);
                            n2Fangke.setTextColor(Color.parseColor("#999999"));

                        } else {
                            n2Tvts.setText("");
                            n2Ts.setText(ewmBean.getErrmsg());
                            n2Ewm.setAlpha(0f);
                        }
                    }

                    private void requestAD() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (num = 0; num < 120; num++) {
                                    Log.e(TAG, "run: strType" + strType);
                                    if (strType.equals("0") && Contents.strExit.equals("0")) {
                                        //请求超过120次则提醒刷新二维码
                                        if (num == 119) {
                                            MessageDialog.build((AppCompatActivity) getActivity())
                                                    .setStyle(DialogSettings.STYLE.STYLE_IOS)
                                                    .setTheme(DialogSettings.THEME.LIGHT)
                                                    .setTitle("提示")
                                                    .setMessage("该二维码已过期，请重新获取")
                                                    .setOkButton("确定", new OnDialogButtonClickListener() {
                                                        @Override
                                                        public boolean onClick(BaseDialog baseDialog, View v) {
                                                            gi();
                                                            return false;
                                                        }

                                                    })
                                                    .show();
                                            break;
                                        }
                                        OkHttpUtils.post()
                                                .url("http://apiback.zhuantoukj.com/brick/index.php/Home/FeedBack/info")
                                                .addParams("token", token)
                                                .addParams("uid", uid)
                                                .build().execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                            }

                                            @Override
                                            public void onResponse(String response) {
//                                                Log.e(TAG, "onCreate: " + "请求了一次" + "====" + strType + "====" + response);
                                                ADBean adBean = new Gson().fromJson(response, ADBean.class);
                                                if (adBean.getErrno().equals("200")) {
                                                    strType = adBean.getData().getType();
                                                    Log.e(TAG, "onResponse: strType2" + strType);
                                                    if (strType.equals("1")) {
                                                        gi();
                                                        strType = "0";
                                                        Log.e(TAG, "onResponse: strType3" + strType);
                                                        num = 120;
                                                        popuinit();
                                                    }
                                                }

                                            }
                                        });

                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (strType.equals("1")) { //如果请求成功则跳出for循环
                                        break;
                                    }
                                }

                            }
//                            }
                        }).start();
                    }
                });

    }


    private void gi2() {
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: gi2" + response);
                        Gson gson = new Gson();
                        EwmBean ewmBean = gson.fromJson(response, EwmBean.class);
                        Log.d("lue1", response);
                        if (ewmBean.getErrno().equals("200")) {
                            String f = ewmBean.getData().getF();
                            if (f.equals("0")) {
                                popuinit1();
                            } else if (f.equals("1")) {
                                intent = new Intent(getActivity(), FangKeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), "出现错误，稍后重试", Toast.LENGTH_SHORT).show();
                            }


                        } else if (ewmBean.getErrno().equals("666666")) {
                            Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            SPUtils.getInstance().clear();
                            intent = new Intent(getContext(), NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {
                            n2Tvts.setText("");
                            n2Ts.setText(ewmBean.getErrmsg());
                            n2Ewm.setAlpha(0f);
                        }
                    }
                });

    }

    private void popuinit() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.jx_pp, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.mypp1_tv1);
        ImageView pbutton = contentView.findViewById(R.id.jingxuan_backimg);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.activity_all, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                Log.e("1111", "onClick: 刷新下了嘛" + "1111111111");
//                gi();
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
//                gi();
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void popuinit1() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pp_erweima, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        final ImageView pimg = contentView.findViewById(R.id.ppewm_img);
        RelativeLayout pr1 = contentView.findViewById(R.id.ppewm_rl1);
        RelativeLayout pr2 = contentView.findViewById(R.id.ppewm_rl2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.activity_h5, null);
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("visitor", "1")
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: popuinit1" + response);
                        Gson gson = new Gson();
                        EwmBean ewmBean = gson.fromJson(response, EwmBean.class);
                        Log.d("lue1", response);
                        if (ewmBean.getErrno().equals("200")) {
//                            logcode = ewmBean.getData().getCode();
                            Bitmap mBitmap = CodeUtils.createImage(ewmBean.getData().getCode(), 400, 400, null);
                            pimg.setImageBitmap(mBitmap);
//                            n2Ewm.setAlpha(1f);
//                            n2Ts.setText("");
//                            n2Tv2.setText(ewmBean.getData().getDuration());
                        } else if (ewmBean.getErrno().equals("666666")) {
                            Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {
//                            n2Tvts.setText("");
//                            n2Ts.setText(ewmBean.getErrmsg());
//                            n2Ewm.setAlpha(0f);
                        }
                    }
                });

        pr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pimg.setDrawingCacheEnabled(true);
                Bitmap obmp = Bitmap.createBitmap(pimg.getDrawingCache());
                DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                saveBitmap1(obmp, format.toString() + ".JPEG");
//                p = saveBitmap(getContext(),bitmap);
//                wechatShare(0, title, "","https://api.zhuantoukj.com/detial/article_detial.html?uid="+uid+"&aid="+aid);//分享到微信朋友
            }
        });
        pr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pimg.setDrawingCacheEnabled(true);
                        bitmap = Bitmap.createBitmap(pimg.getDrawingCache());
                        p = saveBitmap(getContext(), bitmap);
                        flag = true;
                    }
                }).start();
                while (true) {
                    if (getFlag()) {
                        //System.out.println(Thread.currentThread().getName() + " do something ...");
                        Log.d("wcal", bitmap.toString());
                        Log.d("wcal", p);
                        imageShare(p, 0);
                        break;
                    }
                }
//                wechatShare(1, title, "","https://api.zhuantoukj.com/detial/article_detial.html?uid="+uid+"&aid="+aid);//分享到微信朋友圈
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

    }//

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

    /*
     * 保存文件，文件名为当前日期
     */
    public void saveBitmap1(Bitmap bitmap, String bitName) {
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机 小米手机和其他手机存储方式不一样
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {  // Meizu 、Oppo。。。。。
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            //格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
                // 插入图库
                MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                        file.getAbsolutePath(), bitName, null);
                Toast.makeText(getContext(), "保存二维码到本地成功",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发送广播，通知刷新图库的显示
        getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
    }

    IWXAPI wxApi;

    public void imageShare(String l, int sendtype) {
        //Toast.makeText(YqActivity.this,l,Toast.LENGTH_SHORT).show();
        wxApi = WXAPIFactory.createWXAPI(getContext(), Config.WX_APP_ID, true);
        wxApi.registerApp(Config.WX_APP_ID);
        File file = new File(l);
        if (!file.exists()) {
            Toast.makeText(getContext(), "图片不存在", Toast.LENGTH_LONG).show();
        }
        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(l);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        Bitmap bmp = BitmapFactory.decodeFile(l);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 100, 200, true);
        msg.setThumbImage(thumbBmp);
        bmp.recycle();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = sendtype == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public boolean getFlag() {
        return flag;
    }

    private static final String SD_PATH = "/sdcard/zhuantkj/pic/";
    private static final String IN_PATH = "/zhuantkj/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + RandomUtil.lovea() + "zhuantoukj.jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO -generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }


    /**
     * 切换fragment调用的方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Contents.strExit = "1";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.e(TAG, "onDestroyView: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        Contents.strExit = "1";
    }


    @OnClick({R.id.n2_sysm, R.id.n2_refresh, R.id.n2_fangke, R.id.click_emergencyPhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.n2_sysm:
                intent = new Intent();
                intent.setClass(getContext(), UserInstructionsActivity.class);
                intent.putExtra("urlmsg", "https://api.zhuantoukj.com/image/shuoming.html");
                intent.putExtra("title", "使用说明");
                startActivity(intent);
                break;
            case R.id.n2_refresh:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                gi();
                break;
            case R.id.n2_fangke:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (errno.equals("200")) {
                    gi2();
                } else {
                    intent = new Intent(getActivity(), EwmApplyActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.click_emergencyPhone:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                popuinit2("是否遇到紧急突发事件需拨打物业联系电话", "取消 ", "确定");
                break;
        }
    }

    private void popuinit2(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_sz, null);

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

                intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + propertyPhone);
                intent.setData(data);
                startActivity(intent);
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

}
