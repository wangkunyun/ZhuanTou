package com.ztkj.wky.zhuantou.homepage;

import android.support.v7.app.AppCompatActivity;

public class NearActivity extends AppCompatActivity {

//    @BindView(R.id.near_back)
//    ImageView nearBack;
//    @BindView(R.id.near_backrl)
//    AutoRelativeLayout nearBackrl;
//    @BindView(R.id.near_dingweiimg)
//    ImageView nearDingweiimg;
//    @BindView(R.id.near_rv)
//    RecyclerView nearRv;
//    @BindView(R.id.near_title)
//    TextView nearTitle;
//    @BindView(R.id.near_rl)
//    AutoRelativeLayout nearRl;
//    private Intent intent, intent2;
//    private String co_id, co_address;
//    private SharedPreferencesHelper sharedPreferencesHelper;
//    private String uid,token;
//    private String url = StringUtils.jiekouqianzui+"Community/businessList";
//    private String first = "1";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_near);
//        ButterKnife.bind(this);
//        ActivityManager.getInstance().addActivity(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPreferencesHelper = new SharedPreferencesHelper(NearActivity.this, "anhua");
//        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
//        token = (String) sharedPreferencesHelper.getSharedPreference("token","");
//        co_id = (String) sharedPreferencesHelper.getSharedPreference("co_id","");
//        co_address = (String) sharedPreferencesHelper.getSharedPreference("co_address","");
//        nearTitle.setText(co_address);
//        gi();
//    }
//
//    private void gi() {
//        OkHttpUtils.post()
//                .url(url)
//                .addParams("co_id",co_id)
//                .addParams("token",token)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Gson gson = new Gson();
//                        NearBean nearBean = gson.fromJson(response, NearBean.class);
//                        if (nearBean.getErrno().equals("200")) {
//                            List<NearBean.DataBean> enterprise = nearBean.getData();
//                            MyAdapter9 myAdapter9 = new MyAdapter9(enterprise, NearActivity.this);
//                            nearRv.setLayoutManager(new LinearLayoutManager(NearActivity.this));
//                            nearRv.setAdapter(myAdapter9);
//                        }else if (nearBean.getErrno().equals("666666")){
//                            Toast.makeText(NearActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
//                            JPushInterface.deleteAlias(NearActivity.this, Integer.parseInt(uid));
//                            sharedPreferencesHelper.clear();
//                            ActivityManager.getInstance().exit();
//                            intent = new Intent(NearActivity.this, NewLoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                });
//
//    }
//
//    @OnClick({R.id.near_backrl, R.id.near_rl})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.near_backrl:
//                finish();
//                break;
//            case R.id.near_rl:
//                intent = new Intent(NearActivity.this,SearchNearActivity.class);
//                intent.putExtra("first",first);
//                startActivity(intent);
//                break;
//        }
//    }

}
