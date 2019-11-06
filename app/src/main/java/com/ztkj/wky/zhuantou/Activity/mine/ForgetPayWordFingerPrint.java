package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.MyUtils.FingerPrintHelper;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPayWordFingerPrint extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    private MyFingerCallBack callBack;
    private FingerPrintHelper manageer;
    private TextView tv_fingerMessage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_word_finger_print);
        ButterKnife.bind(this);
        layoutTitleTv.setText("修改支付密码");

        manageer = new FingerPrintHelper(this, "com.ztkj.wky.zhuantou.fingerprint_authentication_key");
        if (manageer.checkSuopprtFingerPrint() != FingerPrintHelper.TYPE_SUCCESS) {
            Toast.makeText(this, "不支持 code =" + manageer.checkSuopprtFingerPrint(), Toast.LENGTH_SHORT).show();
            return;
        }
        callBack = new MyFingerCallBack();

        try {
            manageer.startFingerPrint(callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        popuinit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void popuinit() {
        View contentView = LayoutInflater.from(ForgetPayWordFingerPrint.this).inflate(R.layout.pp_fingerprint, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        tv_fingerMessage = contentView.findViewById(R.id.tv_fingerMessage);
        TextView click_cancel = contentView.findViewById(R.id.click_cancel);

        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        View rootview = LayoutInflater.from(ForgetPayWordFingerPrint.this).inflate(R.layout.activity_forget_pay_word_finger_print, null);

        tv_fingerMessage.setText("请验证已有指纹");


        click_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                finish();
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                finish();
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


    class MyFingerCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            //发生不可恢复的错误，比如传感器异常
            tv_fingerMessage.setText("发生了不可预知的错误！");

            Log.e("print", "errMsgId = " + errMsgId + ",  errString = " + errString);
            try {
                manageer.stopFingerPrint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onAuthenticationFailed() {
            //认证失败，表示本次采集的指纹信息和之前注册的指纹信息不一致
            tv_fingerMessage.setText("指纹信息不一致,请重试");
//            btn_sign.setEnabled(true);
//            btn_cancel.setEnabled(false);
            //其实这个时候，传感器还是在工作的。知道你录入正确指纹。好像有五次机会.
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            //发生可以恢复的错误。比如手指离开的太快，还没采集结束
            switch (helpMsgId) {
                case FingerprintManager.FINGERPRINT_ACQUIRED_GOOD:
//                    setResultInfo("", Color.YELLOW);
                    break;
                case FingerprintManager.FINGERPRINT_ACQUIRED_IMAGER_DIRTY:
                    tv_fingerMessage.setText("指纹图像太嘈杂由于在传感器上可疑或检测到的污垢");
                    break;
                case FingerprintManager.FINGERPRINT_ACQUIRED_INSUFFICIENT:
                    tv_fingerMessage.setText("皮肤太干");
                    break;
                case FingerprintManager.FINGERPRINT_ACQUIRED_PARTIAL:
                    tv_fingerMessage.setText("只检测到一个局部指纹图像");

                    break;
                case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_FAST:
                    tv_fingerMessage.setText("指纹图像是不完整的，由于快速运动");
                    break;
                case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_SLOW:
                    tv_fingerMessage.setText("指纹图像是不可读的");

                    break;
            }
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            //认证成功
            tv_fingerMessage.setText("指纹认证成功！！");
            intent = new Intent(ForgetPayWordFingerPrint.this, ForgetSetPayWord.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            manageer.stopFingerPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
