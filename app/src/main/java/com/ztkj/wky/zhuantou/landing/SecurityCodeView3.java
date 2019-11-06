package com.ztkj.wky.zhuantou.landing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.MessageDialog;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.mine.RechargeActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.VerPayWordBean;

//这个是自定义view用来显示验证码
public class SecurityCodeView3 extends RelativeLayout {

    private EditText editText;
    private TextView[] TextViews;
    private StringBuffer stringBuffer = new StringBuffer();
    private int count = 6;
    private String inputContent;

    public SecurityCodeView3(Context context) {
        this(context, null);
    }

    public SecurityCodeView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SecurityCodeView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TextViews = new TextView[6];
        View.inflate(context, R.layout.view_security_code2, this);
        editText = findViewById(R.id.et);
        TextViews[0] = findViewById(R.id.item_code_iv1);
        TextViews[1] = findViewById(R.id.item_code_iv2);
        TextViews[2] = findViewById(R.id.item_code_iv3);
        TextViews[3] = findViewById(R.id.item_code_iv4);
        TextViews[4] = findViewById(R.id.item_code_iv5);
        TextViews[5] = findViewById(R.id.item_code_iv6);
        editText.setCursorVisible(false);//将光标隐藏
        setListener(context);
    }

    public void setLIstener(TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    private void setListener(Context context) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //如果字符不为""时才进行操作
                if (!editable.toString().equals("")) {
                    if (stringBuffer.length() > 5) {
                        //当文本长度大于5位时editText置空
                        editText.setText("");
                        return;
                    } else {
                        //将文字添加到StringBuffer中
                        stringBuffer.append(editable);
                        editText.setText("");//添加后将EditText置空
                        count = stringBuffer.length();
                        inputContent = stringBuffer.toString();
                        if (stringBuffer.length() == 6) {
                            //文字长度位6  则调用完成输入的监听

                            OkHttpUtils.post()
                                    .url("https://api.zhuantoukj.com/birck/index.php/Home/Bankcard/testPwd")
                                    .addParams("token", SPUtils.getInstance().getString("token"))
                                    .addParams("uid", SPUtils.getInstance().getString("uid"))
                                    .addParams("pp", inputContent)
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    VerPayWordBean verPayWordBean = GsonUtil.gsonToBean(response, VerPayWordBean.class);
                                    RechargeActivity.mListPopWindow2.dissmiss();
                                    if (verPayWordBean.getData().getState().equals("1")) {
                                        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    MessageDialog.build((AppCompatActivity) context)
                                            .setStyle(DialogSettings.STYLE.STYLE_IOS)
                                            .setTheme(DialogSettings.THEME.LIGHT)
                                            .setTitle("提示")
                                            .setMessage(verPayWordBean.getData().getTime())
                                            .setOkButton("确定", new OnDialogButtonClickListener() {
                                                @Override
                                                public boolean onClick(BaseDialog baseDialog, View v) {

                                                    return false;
                                                }

                                            })
                                            .show();
                                }
                            });


                            if (inputCompleteListener != null) {
                                inputCompleteListener.inputComplete();
                            }
                        }
                    }
                    for (int i = 0; i < stringBuffer.length(); i++) {
                        TextViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    }
                }
            }
        });
        editText.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (onKeyDelete()) return true;
                    return true;
                }
                return false;
            }

        });
    }

    public boolean onKeyDelete() {
        if (count == 0) {
            count = 6;
            return true;

        }
        if (stringBuffer.length() > 0) {
            //删除相应位置的字符
            stringBuffer.delete((count - 1), count);
            count--;
            inputContent = stringBuffer.toString();
            TextViews[stringBuffer.length()].setText("");
            TextViews[stringBuffer.length()].setBackgroundResource(R.drawable.bg_user_verify_code_grey);
            if (inputCompleteListener != null)
                inputCompleteListener.deleteContent(true);//有删除就通知manger
        }
        return false;
    }

    public void clearEditText() {
        stringBuffer.delete(0, stringBuffer.length());
        inputContent = stringBuffer.toString();
        for (int i = 0; i < TextViews.length; i++) {
            TextViews[i].setText("");
            TextViews[i].setBackgroundResource(R.drawable.bg_user_verify_code_grey);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void inputComplete();

        void deleteContent(boolean isDelete);
    }

    /**
     * 获取输入文本
     *
     * @return
     */
    public String getEditContent() {
        return inputContent;
    }

    /**
     *
     *
     * @return
     */

}
