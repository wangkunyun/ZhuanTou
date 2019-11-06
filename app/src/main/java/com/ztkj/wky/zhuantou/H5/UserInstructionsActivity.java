package com.ztkj.wky.zhuantou.H5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInstructionsActivity extends AppCompatActivity {

    @BindView(R.id.uinstr_goback)
    ImageView uinstrToolbarGoback;
    @BindView(R.id.uinstr_title)
    TextView uinstrToolbarTitle;
    @BindView(R.id.uinstr_toolbar)
    Toolbar uinstrToolbar;
    @BindView(R.id.uinstrxiahua)
    TextView uinstrxiahua;
    @BindView(R.id.uinstr_web)
    WebView uinstrWeb;
    private String urlmsg,title2;
    private Intent intent2;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_instructions);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        intent2 = getIntent();
        urlmsg = intent2.getStringExtra("urlmsg");
        title2 = intent2.getStringExtra("title");
        WebSettings webSettings = uinstrWeb.getSettings();
        // 设置android下容许执行js的脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 编码方式
        webSettings.setDefaultTextEncodingName("utf-8");

        uinstrWeb.setWebContentsDebuggingEnabled(true);

        if (title2.length() >= 8) {
            String sn = title2.toString().substring(0, 8);
            uinstrToolbarTitle.setText(sn + "...");
        } else {
            uinstrToolbarTitle.setText(title2);
        }
        /*
         * 使用这个函数将一个对象绑定到Javascript,因此可以从Javascript访问的方法,
         * Android（Java）与js（HTML）交互的接口函数, jsObj 为桥连对象可随意设值
         */
        //yqfyWeb.addJavascriptInterface(jsInterface.class, "jsObj");
//        WebChromeClient wvcc = new WebChromeClient() {
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                if (title.length() >= 8) {
//                    String sn = title.toString().substring(0, 8);
//                    h5Title.setText(sn + "...");
//                } else {
//                    h5Title.setText(title);
//                }
//                //title 就是网页的title
//            }
//        };
//        h5Web.setWebChromeClient(wvcc);
//        Log.d("h55","http://39.97.75.100/detial/article_detial.html?uid="+uid+"&aid="+aid);
        uinstrWeb.loadUrl(urlmsg);

        uinstrWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @OnClick(R.id.uinstr_goback)
    public void onViewClicked() {
        finish();
    }
}
