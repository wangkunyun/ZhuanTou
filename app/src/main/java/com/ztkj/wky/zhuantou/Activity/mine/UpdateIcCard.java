package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.IdCardFrontsBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateIcCard extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.click_IdCardPros)
    ImageView clickIdCardPros;
    @BindView(R.id.click_IdCardCons)
    ImageView clickIdCardCons;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    private String pathPros, pathCons;
    private boolean pros = false;
    private boolean cons = false;
    private String TAG = "UpdateIcCard";
    private Intent intent;
    private String idCardName, idCardNum;
    private String idCardPath1, idCardPath2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ic_card);
        ButterKnife.bind(this);
        layoutTitleTv.setText("上传身份证照");
        intent = getIntent();
        idCardName = intent.getStringExtra("idCardName");
        idCardNum = intent.getStringExtra("idCardNum");
        idCardPath1 = SPUtils.getInstance().getString("IdCardPath1");
        idCardPath2 = SPUtils.getInstance().getString("IdCardPath2");
        if (!idCardPath1.equals("")) {
            Glide.with(this).load(idCardPath1).into(clickIdCardPros);
        }
        if (!idCardPath2.equals("")) {
            Glide.with(this).load(idCardPath2).into(clickIdCardCons);
        }

    }


    @OnClick({R.id.layout_back, R.id.click_IdCardPros, R.id.click_IdCardCons, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.click_IdCardPros:
                pros = true;
                ChoosePic();
                break;
            case R.id.click_IdCardCons:
                cons = true;
                ChoosePic();
                break;
            case R.id.btnSubmit:
                request();
                break;
        }
    }

    private void request() {
        if (idCardPath1.equals("")){
            Toast.makeText(this, "请上传身份证照片", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idCardPath2.equals("")){
            Toast.makeText(this, "请上传身份证照片", Toast.LENGTH_SHORT).show();
            return;
        }
            OkHttpUtils.post().url(Contents.PHPGETIDCARD)
                    .addParams("token", SPUtils.getInstance().getString("token"))
                    .addParams("idCardSide", "front")
                    .addParams("image", "data:image/jpg;base64," + pathPros)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    Log.e(TAG, "onError: ====php====" + e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: " + "data:image/jpg;base64," + pathPros);
                    Log.e(TAG, "onResponse: ====PHP====" + response);
                    IdCardFrontsBean idCardFrontsBean = GsonUtil.gsonToBean(response, IdCardFrontsBean.class);
                    SPUtils.getInstance().put("idCardName", idCardFrontsBean.getResult().getName());
                    SPUtils.getInstance().put("idCardNum", idCardFrontsBean.getResult().getCode());
                    /**
                     *  code为1则上传身份证
                     */
                    if (idCardFrontsBean.getCode().equals("1")) {

                        if (!idCardName.equals(idCardFrontsBean.getResult().getName())) {
                            Toast.makeText(UpdateIcCard.this, "您输入的名称与上传的身份证片有误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!idCardNum.equals(idCardFrontsBean.getResult().getCode())) {
                            Toast.makeText(UpdateIcCard.this, "您输入的身份证号码与上传的身份证片有误", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        intent = new Intent(UpdateIcCard.this, PayWordActivity.class);
                        SPUtils.getInstance().put("PathPros", pathPros);
                        SPUtils.getInstance().put("PathCons", pathCons);
                        SPUtils.getInstance().put("idCardNum", idCardNum);
                        SPUtils.getInstance().put("idCardName", idCardName);
                        startActivity(intent);


                    }

                }
            });


    }

    private void ChoosePic() {
        PictureSelector.create(UpdateIcCard.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)//最大图片选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .compress(true)// 是否压缩 true or false
                .enableCrop(true)// 是否裁剪 true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .withAspectRatio(3, 2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);

    }

    /**
     * 处理回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                /**
                 * 获取图片
                 */
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
//                    LocalMedia localMedia1 = localMedia.get(0);


                    if (localMedia.get(0).isCompressed()) {
                        String compressPath = localMedia.get(0).getCompressPath();
                        if (pros) {
                            pros = false;
                            pathPros = imageToBase64(compressPath);
                            SPUtils.getInstance().put("IdCardPath1", compressPath);
                            Glide.with(UpdateIcCard.this).load(compressPath).into(clickIdCardPros);
                        } else if (cons) {
                            cons = false;
                            pathCons = imageToBase64(compressPath);
                            SPUtils.getInstance().put("IdCardPath2", compressPath);
                            Glide.with(UpdateIcCard.this).load(compressPath).into(clickIdCardCons);

                        }
                    }


                    break;

            }
        }
    }


    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }


}
