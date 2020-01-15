package com.ztkj.wky.zhuantou.Activity.mine.jf;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter4;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JiFenList;
import com.ztkj.wky.zhuantou.bean.MyZtBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class JfConversionRecordFragment extends Fragment {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jf_conversion_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        layoutTitleTv.setText("兑换记录");
        uid = SPUtils.getInstance().getString("uid");
        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    List<JiFenList.DataBean> data;
    JiFenList jiFenList;
    public void initData() {
        OkHttpUtils.post().url(Contents.BASE + Contents.jifenList)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            jiFenList=new Gson().fromJson(response,JiFenList.class);
                            if(jiFenList.getData()!=null){
                                data=jiFenList.getData();
                                reView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                MyAdapter4 myAdapter4=new MyAdapter4(data,getActivity());
                                reView.setAdapter(myAdapter4);
                            }
                        }
                    }
                });
    }


    public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ViewHolder> {
        private List<JiFenList.DataBean> mData;

        private Context context;

        public MyAdapter4(List<JiFenList.DataBean> mData, Context context) {
            this.mData = mData;
            this.context = context;
        }

        @Override
        public MyAdapter4.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_recoder_jf_layout, parent, false);
            MyAdapter4.ViewHolder viewHolder = new MyAdapter4.ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyAdapter4.ViewHolder holder, final int position) {

            Glide.with(context).load(mData.get(position).getCom_cover()).into(holder.iv_jifen);
            holder.tv_jf_time.setText(mData.get(position).getAddtime());
//            holder.tv_jifen_num.setText("x"+mData.get(position).get);
            holder.tv_jifen_name.setText(mData.get(position).getGname());
            holder.tv_jifen_reduce.setText("-"+mData.get(position).getIntegral()+"砖头");
            holder.constrain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转至积分详情
                    JfSuceesDetailActivity.start(context,mData.get(position).getGid(),mData.get(position).getCom_cover());
//                    ConversionShopDetails.start(context, mData.get(position).getCom_id());
                }
            });


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_jf_time, tv_jifen_name,tv_jifen_num,tv_jifen_reduce;
            private ImageView iv_jifen;
            private ConstraintLayout constrain;

            public ViewHolder(View itemView) {

                super(itemView);
                iv_jifen = itemView.findViewById(R.id.iv_jifen);
                tv_jf_time = itemView.findViewById(R.id.tv_jf_time);
                tv_jifen_name = itemView.findViewById(R.id.tv_jifen_name);
                tv_jifen_num = itemView.findViewById(R.id.tv_jifen_num);
                tv_jifen_reduce = itemView.findViewById(R.id.tv_jifen_reduce);
                constrain = itemView.findViewById(R.id.constrain);

            }
        }
    }

    @OnClick({R.id.layout_back, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                getActivity().finish();
                break;
            case R.id.more:
                break;
        }
    }
}
