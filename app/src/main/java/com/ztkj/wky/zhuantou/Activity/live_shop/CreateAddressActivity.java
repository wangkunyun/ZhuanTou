package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AddressAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAddressActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.recycler_address)
    RecyclerView recycler_address;
    AddressAdapter addressAdapter;
    @BindView(R.id.btn_add_address)
    Button btn_add_address;

    public static void start(Context context) {
        Intent starter = new Intent(context, CreateAddressActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_address);
        ButterKnife.bind(this);
        layoutBack.setOnClickListener(this);
        btn_add_address.setOnClickListener(this);
        layoutTitleTv.setText("收获地址");
        recycler_address.setLayoutManager(new LinearLayoutManager(this));
        addressAdapter = new AddressAdapter(CreateAddressActivity.this);
        recycler_address.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_add_address:
                EditAddressActivity.start(CreateAddressActivity.this);
                break;

        }
    }
}
