package com.ztkj.wky.zhuantou.homepage;

import android.support.v7.app.AppCompatActivity;

public class AllActivity extends AppCompatActivity {

//    @BindView(R.id.all_goback)
//    ImageView allGoback;
//    @BindView(R.id.all_title)
//    TextView allTitle;
//    @BindView(R.id.all_servicetv)
//    TextView allServicetv;
//    @BindView(R.id.all_zuijinimg1)
//    ImageView allZuijinimg1;
//    @BindView(R.id.zuijintv1)
//    TextView zuijintv1;
//    @BindView(R.id.all_zuijin1)
//    AutoRelativeLayout allZuijin1;
//    @BindView(R.id.all_zuijinimg2)
//    ImageView allZuijinimg2;
//    @BindView(R.id.zuijintv2)
//    TextView zuijintv2;
//    @BindView(R.id.all_zuijin2)
//    AutoRelativeLayout allZuijin2;
//    @BindView(R.id.all_zuijinimg3)
//    ImageView allZuijinimg3;
//    @BindView(R.id.zuijintv3)
//    TextView zuijintv3;
//    @BindView(R.id.all_zuijin3)
//    AutoRelativeLayout allZuijin3;
//    @BindView(R.id.all_zuijinimg4)
//    ImageView allZuijinimg4;
//    @BindView(R.id.zuijintv4)
//    TextView zuijintv4;
//    @BindView(R.id.all_zuijin4)
//    AutoRelativeLayout allZuijin4;
//    @BindView(R.id.all_servicetv2)
//    TextView allServicetv2;
//    @BindView(R.id.all_rv)
//    RecyclerView allRv;
//    private SharedPreferencesHelper sharedPreferencesHelper;
//    private List<String> listz1;
//    private List<String> listz2;
//    private List<String> listz3;
//    private String eid1,eid2,eid3,eid4,name1,name2,name3,name4,log1,log2,log3,log4;
////    private String fineo;
//    private String uid,token;
//    private Intent intent;
//    private String url = StringUtils.jiekouqianzui+"Article/enterpriseService";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all);
//        ButterKnife.bind(this);
//        ActivityManager.getInstance().addActivity(this);
//        sharedPreferencesHelper = new SharedPreferencesHelper(AllActivity.this, "anhua");
//        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
//        token = (String) sharedPreferencesHelper.getSharedPreference("token","");
//        gi();
//
//    }
//
//    private void gi() {
//        OkHttpUtils.post()
//                .url(url)
//                .addParams("types","1")
//                .addParams("token",token)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                    }
//                    @Override
//                    public void onResponse(String response) {
//                        Gson gson = new Gson();
//                        RvBean3 rvBean3 = gson.fromJson(response, RvBean3.class);
//                        if (rvBean3.getErrno().equals("200")) {
//                            List<RvBean3.DataBean> data = rvBean3.getData();
//                            Log.d("allfinea", response);
//                            if (sharedPreferencesHelper.contain("fineo")) {
//                                Log.d("lowwop0", "1");
//                                String zuijin1 = (String) sharedPreferencesHelper.getSharedPreference("zuijin1", "");
//                                String zuijin2 = (String) sharedPreferencesHelper.getSharedPreference("zuijin2", "");
//                                String zuijin3 = (String) sharedPreferencesHelper.getSharedPreference("zuijin3", "");
//                                Log.d("zuijin3", zuijin1 + "+" + zuijin2 + "+" + zuijin3);
//                                zuijin1 = zuijin1.replace("[", "");
//                                zuijin1 = zuijin1.replace("]", "");
//                                zuijin1 = zuijin1.replaceAll(" ", "");
//                                zuijin2 = zuijin2.replace("[", "");
//                                zuijin2 = zuijin2.replace("]", "");
//                                zuijin2 = zuijin2.replaceAll(" ", "");
//                                zuijin3 = zuijin3.replace("[", "");
//                                zuijin3 = zuijin3.replace("]", "");
//                                zuijin3 = zuijin3.replaceAll(" ", "");
//                                Log.d("zuijin3", zuijin1 + "+" + zuijin2 + "+" + zuijin3);
//                                String[] arr = zuijin1.split(",");
//                                String[] arr2 = zuijin2.split(",");
//                                String[] arr3 = zuijin3.split(",");
//                                List<String> mdata1 = new ArrayList(Arrays.asList(zuijin1.split(",")));
//                                List<String> mdata2 = new ArrayList(Arrays.asList(zuijin2.split(",")));
//                                List<String> mdata3 = new ArrayList(Arrays.asList(zuijin3.split(",")));
//
//                                eid1 = mdata1.get(0);
//                                eid2 = mdata1.get(1);
//                                eid3 = mdata1.get(2);
//                                eid4 = mdata1.get(3);
//                                zuijintv1.setText(mdata2.get(0));
//                                zuijintv2.setText(mdata2.get(1));
//                                zuijintv3.setText(mdata2.get(2));
//                                zuijintv4.setText(mdata2.get(3));
//                                Glide.with(AllActivity.this).load(mdata3.get(0)).into(allZuijinimg1);
//                                Glide.with(AllActivity.this).load(mdata3.get(1)).into(allZuijinimg2);
//                                Glide.with(AllActivity.this).load(mdata3.get(2)).into(allZuijinimg3);
//                                Glide.with(AllActivity.this).load(mdata3.get(3)).into(allZuijinimg4);
//                                sharedPreferencesHelper.put("zuijin1", mdata1 + "");
//                                sharedPreferencesHelper.put("zuijin2", mdata2 + "");
//                                sharedPreferencesHelper.put("zuijin3", mdata3 + "");
//
//                            } else {
//                                popuinit();
//                                listz1 = new ArrayList<>();
//                                listz2 = new ArrayList<>();
//                                listz3 = new ArrayList<>();
//                                eid1 = "1";
//                                eid2 = "3";
//                                eid3 = "5";
//                                eid4 = "7";
//                                listz1.add(eid1);
//                                listz1.add(eid2);
//                                listz1.add(eid3);
//                                listz1.add(eid4);
//                                for (int i = 0; i < data.size(); i++) {
//                                    if (eid1.equals(data.get(i).getEid())) {
//                                        name1 = data.get(i).getEname();
//                                        log1 = data.get(i).getElogo();
//                                    } else if (eid2.equals(data.get(i).getEid())) {
//                                        name2 = data.get(i).getEname();
//                                        log2 = data.get(i).getElogo();
//                                    } else if (eid3.equals(data.get(i).getEid())) {
//                                        name3 = data.get(i).getEname();
//                                        log3 = data.get(i).getElogo();
//                                    } else if (eid4.equals(data.get(i).getEid())) {
//                                        name4 = data.get(i).getEname();
//                                        log4 = data.get(i).getElogo();
//                                    }
//                                }
//                                listz2.add(name1);
//                                listz2.add(name2);
//                                listz2.add(name3);
//                                listz2.add(name4);
//                                listz3.add(log1);
//                                listz3.add(log2);
//                                listz3.add(log3);
//                                listz3.add(log4);
//                                sharedPreferencesHelper.put("fineo", "111");
//                                sharedPreferencesHelper.put("zuijin1", listz1 + "");
//                                sharedPreferencesHelper.put("zuijin2", listz2 + "");
//                                sharedPreferencesHelper.put("zuijin3", listz3 + "");
//                                Glide.with(AllActivity.this).load(log1).into(allZuijinimg1);
//                                Glide.with(AllActivity.this).load(log2).into(allZuijinimg2);
//                                Glide.with(AllActivity.this).load(log3).into(allZuijinimg3);
//                                Glide.with(AllActivity.this).load(log4).into(allZuijinimg4);
//                                zuijintv1.setText(name1);
//                                zuijintv2.setText(name2);
//                                zuijintv3.setText(name3);
//                                zuijintv4.setText(name4);
//                            }
//                            MyAdapter3 myAdapter2 = new MyAdapter3(data, AllActivity.this);
//                            allRv.setLayoutManager(new GridLayoutManager(AllActivity.this, 4));
//                            allRv.setAdapter(myAdapter2);
//                        }else if (rvBean3.getErrno().equals("666666")){
//                            Toast.makeText(AllActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
//                            JPushInterface.deleteAlias(AllActivity.this, Integer.parseInt(uid));
//                            sharedPreferencesHelper.clear();
//                            ActivityManager.getInstance().exit();
//                            intent = new Intent(AllActivity.this, NewLoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//
//                    }
//                });
//    }
//
//    private void popuinit() {
//        View contentView = LayoutInflater.from(AllActivity.this).inflate(R.layout.jx_pp, null);
//        //设置popuwindow是在父布局的哪个地方显示
//        backgroundAlpha(0.2f);
//        //下面是p里面的东西
////        TextView ptextView = contentView.findViewById(R.id.mypp1_tv1);
//        ImageView pbutton = contentView.findViewById(R.id.jingxuan_backimg);
//        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//        window.setOutsideTouchable(true);
//        window.setTouchable(true);
//        View rootview = LayoutInflater.from(AllActivity.this).inflate(R.layout.activity_all, null);
//
//        pbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backgroundAlpha(1f);
//                window.dismiss();
//            }
//        });
//        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(1f);
//                window.dismiss();
//            }
//        });
//        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
//    }
//
//    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        getWindow().setAttributes(lp);
//    }
//
//    @OnClick({R.id.all_goback, R.id.all_zuijin1, R.id.all_zuijin2, R.id.all_zuijin3,R.id.all_zuijin4})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.all_goback:
//                finish();
//                break;
//            case R.id.all_zuijin1:
//                intent = new Intent(AllActivity.this, AllServiceActivity.class);
//                intent.putExtra("eid",eid1);
//                intent.putExtra("ase","0");
//                startActivity(intent);
//                break;
//            case R.id.all_zuijin2:
//                intent = new Intent(AllActivity.this, AllServiceActivity.class);
//                intent.putExtra("eid",eid2);
//                intent.putExtra("ase","0");
//                startActivity(intent);
//                break;
//            case R.id.all_zuijin3:
//                intent = new Intent(AllActivity.this, AllServiceActivity.class);
//                intent.putExtra("eid",eid3);
//                intent.putExtra("ase","0");
//                startActivity(intent);
//                break;
//            case R.id.all_zuijin4:
//                intent = new Intent(AllActivity.this, AllServiceActivity.class);
//                intent.putExtra("eid",eid4);
//                intent.putExtra("ase","0");
//                startActivity(intent);
//                break;
//        }
//    }


}
