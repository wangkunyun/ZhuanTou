package com.ztkj.wky.zhuantou.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.oa.punch.Statistical_Table;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.TeamStatiscalBean;

import java.util.List;

public class TeamStaticalDetailsAdapter extends RecyclerView.Adapter<TeamStaticalDetailsAdapter.ViewHolder> {
    private Context context;
    private TeamStatiscalBean.DataBean.ArrayNewBean arrayNew;
    private Intent intent;
    private int num;
    private int size;

    public TeamStaticalDetailsAdapter(Context context, TeamStatiscalBean.DataBean.ArrayNewBean arrayNew, int num) {
        this.context = context;
        this.arrayNew = arrayNew;
        this.num = num;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_statistical, viewGroup, false);
//        attendanceDays = data.getAttendanceDays();
//        size = attendanceDays.size();
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (num) {
            case 1: //平均时长
                List<TeamStatiscalBean.DataBean.ArrayNewBean.WorkingHoursBean> workingHours = arrayNew.getWorkingHours();
                viewHolder.tv_date.setText(workingHours.get(i).getUsername());
                viewHolder.tv_unit.setText(workingHours.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", workingHours.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 2: //迟到
                List<TeamStatiscalBean.DataBean.ArrayNewBean.LateBean> late = arrayNew.getLate();
                viewHolder.tv_date.setText(late.get(i).getUsername());
                viewHolder.tv_unit.setText(late.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", late.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 3: //早退
                List<TeamStatiscalBean.DataBean.ArrayNewBean.LeaveEarlyBean> leaveEarly = arrayNew.getLeaveEarly();
                viewHolder.tv_date.setText(leaveEarly.get(i).getUsername());
                viewHolder.tv_unit.setText(leaveEarly.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", leaveEarly.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 4: //缺卡
                List<TeamStatiscalBean.DataBean.ArrayNewBean.MissingCardBean> missingCard = arrayNew.getMissingCard();
                viewHolder.tv_date.setText(missingCard.get(i).getUsername());
                viewHolder.tv_unit.setText(missingCard.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", missingCard.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 5: //旷工
                List<TeamStatiscalBean.DataBean.ArrayNewBean.AbsenteeismBean> absenteeism = arrayNew.getAbsenteeism();
                viewHolder.tv_date.setText(absenteeism.get(i).getUsername());
                viewHolder.tv_unit.setText(absenteeism.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", absenteeism.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 6: //外勤
                List<TeamStatiscalBean.DataBean.ArrayNewBean.FieldPersonnelBean> fieldPersonnel = arrayNew.getFieldPersonnel();
                viewHolder.tv_date.setText(fieldPersonnel.get(i).getUsername());
                viewHolder.tv_unit.setText(fieldPersonnel.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", fieldPersonnel.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;
            case 7: //加班
                List<TeamStatiscalBean.DataBean.ArrayNewBean.OvertimeBean> overtime = arrayNew.getOvertime();
                viewHolder.tv_date.setText(overtime.get(i).getUsername());
                viewHolder.tv_unit.setText(overtime.get(i).getNum());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(context, Statistical_Table.class);
                        intent.putExtra("uid", overtime.get(i).getUid());
                        context.startActivity(intent);
                    }
                });
                break;

        }
    }

    @Override
    public int getItemCount() {
        switch (num) {
            case 1:
                size = arrayNew.getWorkingHours().size();
                break;
            case 2:
                size = arrayNew.getLate().size();
                break;
            case 3:
                size = arrayNew.getLeaveEarly().size();
                break;
            case 4:
                size = arrayNew.getMissingCard().size();
                break;
            case 5:
                size = arrayNew.getAbsenteeism().size();
                break;
            case 6:
                size = arrayNew.getFieldPersonnel().size();
                break;
            case 7:
                size = arrayNew.getOvertime().size();
                break;

        }
        return size;
    }

    class ViewHolder extends MyAdapter.ViewHolder {
        private TextView tv_date, tv_unit;
        private RelativeLayout rl_click;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_item_statistical_date);
            tv_unit = itemView.findViewById(R.id.tv_item_statistical_unit);
            rl_click = itemView.findViewById(R.id.rl_click_staticalDetails);
        }
    }
}
