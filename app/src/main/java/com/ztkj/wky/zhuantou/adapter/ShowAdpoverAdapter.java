package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

public class ShowAdpoverAdapter extends RecyclerView.Adapter<ShowAdpoverAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> listPictureName;//图片集合
    private ArrayList<String> listPicture;//图片集合

    public ShowAdpoverAdapter(Context context, ArrayList<String> listPictureName) {
        this.context = context;
        this.listPictureName = listPictureName;
    }

    public void setData(ArrayList<String> listPicture){
        this.listPicture=listPicture;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_re_show_picture, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        if(!listPictureName.get(i).equals("0")){
            Glide.with(context).load(listPictureName.get(i)).apply(options).into(viewHolder.showPicture);

        }else{
            Glide.with(context).load(R.drawable.head_portrait).apply(options).into(viewHolder.showPicture);

        }
        if(listPicture!=null) {
            viewHolder.tv_name.setText(listPicture.get(i));
        }
        viewHolder.delPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listPictureName.remove(i);
//                notifyItemRemoved(i);
//                notifyItemRangeChanged(0, listPictureName.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPictureName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView showPicture, delPicture;
        private TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showPicture = itemView.findViewById(R.id.item_img_showPicture);
            delPicture = itemView.findViewById(R.id.item_del_picture);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
}
