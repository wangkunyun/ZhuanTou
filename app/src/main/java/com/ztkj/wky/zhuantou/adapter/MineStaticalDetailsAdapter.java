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

import com.ztkj.wky.zhuantou.Activity.oa.punch.StatisticalDetailsActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.MineStatisticalDetailsBean;
import com.ztkj.wky.zhuantou.bean.MissCardBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MineStaticalDetailsAdapter extends RecyclerView.Adapter<MineStaticalDetailsAdapter.ViewHolder> {
    private Context context;
    private MineStatisticalDetailsBean.DataBean data;
    private int num;
    private int size;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simHour = new SimpleDateFormat("HH");

//    private List<MineStatisticalDetailsBean.DataBean.AttendanceDaysBean> attendanceDays;

    public MineStaticalDetailsAdapter(Context context, MineStatisticalDetailsBean.DataBean data, int num) {
        this.context = context;
        this.data = data;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {

        switch (num) {
            case 1: //平均时长
                List<MineStatisticalDetailsBean.DataBean.WorkingHoursBean> workingHours = data.getWorkingHours();
                viewHolder.tv_date.setText(workingHours.get(i).getAddtime());
                int format = ((Integer.parseInt(workingHours.get(i).getEnd_time()) - Integer.parseInt(workingHours.get(i).getStart_time())) / 3600);
                viewHolder.tv_unit.setText(format + "小时");
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "1");
                        intent.putExtra("uid", workingHours.get(i).getUid());
                        intent.putExtra("startTime", workingHours.get(i).getStart_time());
                        intent.putExtra("endTime", workingHours.get(i).getEnd_time());
                        intent.putExtra("date", workingHours.get(i).getAddtime());
                        intent.putExtra("late", workingHours.get(i).getLate());
                        intent.putExtra("leave_early", workingHours.get(i).getLeave_early());
                        intent.putExtra("overtime", workingHours.get(i).getOvertime());
                        intent.putExtra("start_field_address", workingHours.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", workingHours.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 2: //出勤天数
                List<MineStatisticalDetailsBean.DataBean.AttendanceDaysBean> attendanceDays = data.getAttendanceDays();
                viewHolder.tv_date.setText(attendanceDays.get(i).getAddtime());
                int format2 = ((Integer.parseInt(attendanceDays.get(i).getEnd_time()) - Integer.parseInt(attendanceDays.get(i).getStart_time())) / 3600);

                if (format2 < 0 || format2 > 24) {
                    viewHolder.tv_unit.setText(0 + "小时");
                } else {
                    viewHolder.tv_unit.setText(format2 + "小时");
                }
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "2");
                        intent.putExtra("uid", attendanceDays.get(i).getUid());
                        intent.putExtra("startTime", attendanceDays.get(i).getStart_time());
                        intent.putExtra("endTime", attendanceDays.get(i).getEnd_time());
                        intent.putExtra("date", attendanceDays.get(i).getAddtime());
                        intent.putExtra("late", attendanceDays.get(i).getLate());
                        intent.putExtra("leave_early", attendanceDays.get(i).getLeave_early());
                        intent.putExtra("overtime", attendanceDays.get(i).getOvertime());
                        intent.putExtra("start_field_address", attendanceDays.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", attendanceDays.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 3: //休息天数
                List<MineStatisticalDetailsBean.DataBean.RestBean> rest = data.getRest();
                viewHolder.tv_date.setText(rest.get(i).getAddtime());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "3");
                        intent.putExtra("uid", rest.get(i).getUid());
                        intent.putExtra("startTime", rest.get(i).getStart_time());
                        intent.putExtra("endTime", rest.get(i).getEnd_time());
                        intent.putExtra("date", rest.get(i).getAddtime());
                        intent.putExtra("late", rest.get(i).getLate());
                        intent.putExtra("leave_early", rest.get(i).getLeave_early());
                        intent.putExtra("overtime", rest.get(i).getOvertime());
                        intent.putExtra("start_field_address", rest.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", rest.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 4: //迟到次数
                List<MineStatisticalDetailsBean.DataBean.LateBean> late = data.getLate();
                viewHolder.tv_date.setText(late.get(i).getAddtime());
                viewHolder.tv_unit.setText(late.get(i).getLate() + "分钟");
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "4");
                        intent.putExtra("uid", late.get(i).getUid());
                        intent.putExtra("startTime", late.get(i).getStart_time());
                        intent.putExtra("endTime", late.get(i).getEnd_time());
                        intent.putExtra("date", late.get(i).getAddtime());
                        intent.putExtra("late", late.get(i).getLate());
                        intent.putExtra("leave_early", late.get(i).getLeave_early());
                        intent.putExtra("overtime", late.get(i).getOvertime());
                        intent.putExtra("start_field_address", late.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", late.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 5: //早退次数
                List<MineStatisticalDetailsBean.DataBean.LeaveEarlyBean> leaveEarly = data.getLeaveEarly();
                viewHolder.tv_date.setText(leaveEarly.get(i).getAddtime());
                viewHolder.tv_unit.setText(leaveEarly.get(i).getLeave_early() + "分钟");
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "5");
                        intent.putExtra("uid", leaveEarly.get(i).getUid());
                        intent.putExtra("startTime", leaveEarly.get(i).getStart_time());
                        intent.putExtra("endTime", leaveEarly.get(i).getEnd_time());
                        intent.putExtra("date", leaveEarly.get(i).getAddtime());
                        intent.putExtra("late", leaveEarly.get(i).getLate());
                        intent.putExtra("leave_early", leaveEarly.get(i).getLeave_early());
                        intent.putExtra("overtime", leaveEarly.get(i).getOvertime());
                        intent.putExtra("start_field_address", leaveEarly.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", leaveEarly.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 6: //缺卡 分为上午缺卡 下午缺卡 需合并成一个新的集合
                List<MineStatisticalDetailsBean.DataBean.StartMissingCardBean> startMissingCard = data.getStartMissingCard();
                List<MineStatisticalDetailsBean.DataBean.EndMissingCardBean> endMissingCard = data.getEndMissingCard();
                ArrayList<MissCardBean> missCardBeans = new ArrayList<>();
                for (int j = 0; j < startMissingCard.size(); j++) {
                    missCardBeans.add(new MissCardBean(startMissingCard.get(i).getAddtime(), "上午"));
                }
                for (int j = 0; j < endMissingCard.size(); j++) {
                    missCardBeans.add(new MissCardBean(endMissingCard.get(i).getAddtime(), "下午"));
                }
                viewHolder.tv_date.setText(missCardBeans.get(i).getAddTime());
                viewHolder.tv_unit.setText(missCardBeans.get(i).getNoon());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        if (missCardBeans.get(i).equals("上午")) {
                            intent.putExtra("tag", "6");
                            intent.putExtra("uid", startMissingCard.get(i).getUid());
                            intent.putExtra("startTime", startMissingCard.get(i).getStart_time());
                            intent.putExtra("endTime", startMissingCard.get(i).getStart_time());
                            intent.putExtra("date", startMissingCard.get(i).getAddtime());
                            intent.putExtra("late", startMissingCard.get(i).getLate());
                            intent.putExtra("leave_early", startMissingCard.get(i).getLeave_early());
                            intent.putExtra("overtime", startMissingCard.get(i).getOvertime());
                            intent.putExtra("start_field_address", startMissingCard.get(i).getStart_field_address());
                            intent.putExtra("end_field_address", startMissingCard.get(i).getEnd_field_address());
                            context.startActivity(intent);
                        } else {
                            intent.putExtra("tag", "6");
                            intent.putExtra("uid", endMissingCard.get(i).getUid());
                            intent.putExtra("startTime", endMissingCard.get(i).getStart_time());
                            intent.putExtra("endTime", endMissingCard.get(i).getStart_time());
                            intent.putExtra("date", endMissingCard.get(i).getAddtime());
                            intent.putExtra("late", endMissingCard.get(i).getLate());
                            intent.putExtra("leave_early", endMissingCard.get(i).getLeave_early());
                            intent.putExtra("overtime", endMissingCard.get(i).getOvertime());
                            intent.putExtra("start_field_address", endMissingCard.get(i).getStart_field_address());
                            intent.putExtra("end_field_address", endMissingCard.get(i).getEnd_field_address());
                            context.startActivity(intent);
                        }

                    }
                });
                break;
            case 7: //旷工次数
                List<MineStatisticalDetailsBean.DataBean.AbsenteeismBean> absenteeism = data.getAbsenteeism();
                viewHolder.tv_date.setText(absenteeism.get(i).getAddtime());
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "7");
                        intent.putExtra("uid", absenteeism.get(i).getUid());
                        intent.putExtra("startTime", absenteeism.get(i).getStart_time());
                        intent.putExtra("endTime", absenteeism.get(i).getEnd_time());
                        intent.putExtra("date", absenteeism.get(i).getAddtime());
                        intent.putExtra("late", absenteeism.get(i).getLate());
                        intent.putExtra("leave_early", absenteeism.get(i).getLeave_early());
                        intent.putExtra("overtime", absenteeism.get(i).getOvertime());
                        intent.putExtra("start_field_address", absenteeism.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", absenteeism.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 8: //外勤次数
                List<MineStatisticalDetailsBean.DataBean.FieldPersonnelBean> fieldPersonnel = data.getFieldPersonnel();
                viewHolder.tv_date.setText(fieldPersonnel.get(i).getAddtime());
                //判断是否为1，上下午为1则上下午都是外勤，显示全天；上午为1 显示上午； 下午为1  显示下午
                if (fieldPersonnel.get(i).getStart_field_address().equals("1") && fieldPersonnel.get(i).getEnd_field_address().equals("1")) {
                    viewHolder.tv_unit.setText("全天");
                } else {
                    if (fieldPersonnel.get(i).getStart_field_address().equals("1")) {
                        viewHolder.tv_unit.setText("上午");
                    } else if (fieldPersonnel.get(i).getEnd_field_address().equals("1")) {
                        viewHolder.tv_unit.setText("下午");
                    }
                }
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "8");
                        intent.putExtra("uid", fieldPersonnel.get(i).getUid());
                        intent.putExtra("startTime", fieldPersonnel.get(i).getStart_time());
                        intent.putExtra("endTime", fieldPersonnel.get(i).getEnd_time());
                        intent.putExtra("date", fieldPersonnel.get(i).getAddtime());
                        intent.putExtra("late", fieldPersonnel.get(i).getLate());
                        intent.putExtra("leave_early", fieldPersonnel.get(i).getLeave_early());
                        intent.putExtra("overtime", fieldPersonnel.get(i).getOvertime());
                        intent.putExtra("start_field_address", fieldPersonnel.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", fieldPersonnel.get(i).getEnd_field_address());

                        context.startActivity(intent);
                    }
                });
                break;
            case 9: //加班时长
                List<MineStatisticalDetailsBean.DataBean.OvertimeBean> overtime = data.getOvertime();
                viewHolder.tv_date.setText(overtime.get(i).getAddtime());
                viewHolder.tv_unit.setText(overtime.get(i).getOvertime() + "分钟");
                viewHolder.rl_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StatisticalDetailsActivity.class);
                        intent.putExtra("tag", "9");
                        intent.putExtra("startTime", overtime.get(i).getStart_time());
                        intent.putExtra("endTime", overtime.get(i).getEnd_time());
                        intent.putExtra("date", overtime.get(i).getAddtime());
                        intent.putExtra("late", overtime.get(i).getLate());
                        intent.putExtra("leave_early", overtime.get(i).getLeave_early());
                        intent.putExtra("overtime", overtime.get(i).getOvertime());
                        intent.putExtra("start_field_address", overtime.get(i).getStart_field_address());
                        intent.putExtra("end_field_address", overtime.get(i).getEnd_field_address());

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
                size = data.getWorkingHours().size();
                break;
            case 2:
                size = data.getAttendanceDays().size();
                break;
            case 3:
                size = data.getRest().size();
                break;
            case 4:
                size = data.getLate().size();
                break;
            case 5:
                size = data.getLeaveEarly().size();
                break;
            case 6:
                size = data.getStartMissingCard().size() + data.getEndMissingCard().size();
                break;
            case 7:
                size = data.getAbsenteeism().size();
                break;
            case 8:
                size = data.getFieldPersonnel().size();
                break;
            case 9:
                size = data.getOvertime().size();
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
