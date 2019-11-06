package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyEvection;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyGoOut;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyLeave;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyOutWork;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyOverTime;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyPayFor;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyReimbursement;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplySeal;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyUseCar;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

public class ApplyRecenlyAdapter extends RecyclerView.Adapter<ApplyRecenlyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> recently;
    private int SizeAdapter;
    private Intent intent;

    public ApplyRecenlyAdapter(Context context, ArrayList<String> recently, int sizeAdapter) {
        this.context = context;
        this.recently = recently;
        SizeAdapter = sizeAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_apply_recently, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        intent = new Intent();
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recently.get(i).equals("vid")) {
                    intent.setClass(context, ApplyUseCar.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("pid")) {
                    intent.setClass(context, ApplySeal.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("rid")) {
                    intent.setClass(context, ApplyPayFor.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("oid")) {
                    intent.setClass(context, ApplyOverTime.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("lid")) {
                    intent.setClass(context, ApplyLeave.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("gid")) {
                    intent.setClass(context, ApplyGoOut.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("fid")) {
                    intent.setClass(context, ApplyOutWork.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("btid")) {
                    intent.setClass(context, ApplyEvection.class);
                    context.startActivity(intent);
                } else if (recently.get(i).equals("paid")) {
                    intent.setClass(context, ApplyReimbursement.class);
                    context.startActivity(intent);
                }
            }
        });
        if (recently.get(i).equals("vid")) {
            Glide.with(context).load(R.mipmap.icon_apply_usercar).into(viewHolder.img);
        } else if (recently.get(i).equals("pid")) {
            Glide.with(context).load(R.mipmap.icon_apply_seal).into(viewHolder.img);
        } else if (recently.get(i).equals("rid")) {
            Glide.with(context).load(R.mipmap.icon_apply_reimbursement).into(viewHolder.img);
        } else if (recently.get(i).equals("oid")) {
            Glide.with(context).load(R.mipmap.icon_apply_overtime).into(viewHolder.img);
        } else if (recently.get(i).equals("lid")) {
            Glide.with(context).load(R.mipmap.icon_apply_leave).into(viewHolder.img);
        } else if (recently.get(i).equals("gid")) {
            Glide.with(context).load(R.mipmap.icon_apply_goout).into(viewHolder.img);
        } else if (recently.get(i).equals("fid")) {
            Glide.with(context).load(R.mipmap.icon_apply_outworker).into(viewHolder.img);
        } else if (recently.get(i).equals("btid")) {
            Glide.with(context).load(R.mipmap.icon_apply_evection).into(viewHolder.img);
        } else if (recently.get(i).equals("paid")) {
            Glide.with(context).load(R.mipmap.icon_apply_payfor).into(viewHolder.img);
        }
    }

    @Override
    public int getItemCount() {
        return SizeAdapter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_apply_recently_img);
        }
    }
}
