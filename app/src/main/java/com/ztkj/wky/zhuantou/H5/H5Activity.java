package com.ztkj.wky.zhuantou.H5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.Config;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class H5Activity extends AppCompatActivity {

    @BindView(R.id.h5_goback)
    ImageView h5Goback;
    @BindView(R.id.h5_share)
    ImageView h5Share;
    @BindView(R.id.h5rv1)
    Toolbar h5rv1;
    @BindView(R.id.h5xiahua)
    TextView h5xiahua;
    @BindView(R.id.h5_web)
    WebView h5Web;
    @BindView(R.id.h5_title)
    TextView h5Title;
    private Intent intent, intent2;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String aid;
    private String title;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        intent2 = getIntent();
        aid = intent2.getStringExtra("aid");
        Log.e("aid", "onCreate: " + aid);
        title = intent2.getStringExtra("title");
        sharedPreferencesHelper = new SharedPreferencesHelper(H5Activity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        WebSettings webSettings = h5Web.getSettings();
        // 设置android下容许执行js的脚本
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsmatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // 编码方式
        webSettings.setDefaultTextEncodingName("utf-8");
        h5Web.setWebContentsDebuggingEnabled(true);

        if (title.length() >= 8) {
            String sn = title.toString().substring(0, 8);
            h5Title.setText(sn + "...");
        } else {
            h5Title.setText(title);
        }

        h5Web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                Log.d("h58url",url);
                //startTimeTask();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO -generated method stub
                super.onPageFinished(view, url);
//                Log.d("h58url",url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("h58url", url);
                if (url.contains("passanopinion")) {
                    intent = new Intent(H5Activity.this, H5EdittextActivity.class);
                    intent.putExtra("uid", uid);
                    intent.putExtra("aid", aid);
                    intent.putExtra("types", "0");
                    startActivity(intent);
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    //if (uri.getAuthority().equals("++chongbi")) {
                    //步骤3：
                    // 执行JS所需要调用的逻辑
                    return true;
                } else if (url.contains("dianzan")) {
                    if (StringUtils.isEmpty(uid)){
                        Toast.makeText(H5Activity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        intent = new Intent(H5Activity.this, NewLoginActivity.class);
                        startActivity(intent);
                    }


                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        h5Web.loadUrl("https://api.zhuantoukj.com/detial/article_detial.html?" + uid + "&aid=" + aid);

    }

    @Override
    protected void onResume() {
        super.onResume();
        h5Web.loadUrl("https://api.zhuantoukj.com/detial/article_detial.html?" + uid + "&aid=" + aid);
    }

    private void popuinit() {
        View contentView = LayoutInflater.from(H5Activity.this).inflate(R.layout.pp1, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        Button pbutton = contentView.findViewById(R.id.pp1_btn);
        RelativeLayout pr1 = contentView.findViewById(R.id.pp1_zuijin1);
        RelativeLayout pr2 = contentView.findViewById(R.id.pp1_zuijin2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(H5Activity.this).inflate(R.layout.activity_h5, null);
        pr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatShare(0, title, "", "https://api.zhuantoukj.com/detial/article_detial.html?uid=" + uid + "&aid=" + aid);//分享到微信朋友
            }
        });
        pr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatShare(1, title, "", "https://api.zhuantoukj.com/detial/article_detial.html?uid=" + uid + "&aid=" + aid);//分享到微信朋友圈
            }
        });
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    IWXAPI wxApi;

    /**
     * 微信分享
     *
     * @param flag(0:分享到微信好友，1：分享到微信朋友圈)
     */
    private void wechatShare(int flag, String c1, String c2, String c4) {

        wxApi = WXAPIFactory.createWXAPI(H5Activity.this, Config.WX_APP_ID, true);
        wxApi.registerApp(Config.WX_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = c4;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = c1;
        msg.description = c2;
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.log_zt);
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        msg.setThumbImage(bmp);
        //方法中设置asBitmap可以设置回调类型
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    @OnClick({R.id.h5_goback, R.id.h5_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.h5_goback:
                finish();
                break;
            case R.id.h5_share:
                popuinit();
                break;
        }
    }

}
