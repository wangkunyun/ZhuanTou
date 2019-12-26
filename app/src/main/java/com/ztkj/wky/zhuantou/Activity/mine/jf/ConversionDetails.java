package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ztkj.wky.zhuantou.R;

public class ConversionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_details);
    }

     public static void start(Context context) {
        Intent starter = new Intent(context, ConversionDetails.class);
        context.startActivity(starter);
    }
}
