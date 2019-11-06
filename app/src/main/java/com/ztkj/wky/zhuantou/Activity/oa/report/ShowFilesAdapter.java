package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

public class ShowFilesAdapter extends RecyclerView.Adapter<ShowFilesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> ListPathName;//文件名称集合

    public ShowFilesAdapter(Context context, ArrayList<String> listPathName) {
        this.context = context;
        ListPathName = listPathName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_re_show_files, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.fileName.setText(ListPathName.get(i));
        viewHolder.delFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListPathName.remove(i);

                notifyItemRemoved(i);
                notifyItemRangeChanged(0,ListPathName.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return ListPathName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fileName;
        private ImageView delFile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.show_files_files_name);
            delFile = itemView.findViewById(R.id.show_files_del_files);
        }
    }
}
