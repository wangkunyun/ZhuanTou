package com.ztkj.wky.zhuantou.Activity.live_shop.order;

/**
 * 作者：wky
 * 功能描述：全部订单
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment {


    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        unbinder = ButterKnife.bind(this, view);

        AdapterOut adapterOut = new AdapterOut();
        reView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reView.setAdapter(adapterOut);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class AdapterOut extends RecyclerView.Adapter<AdapterOut.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_out, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.item_reOrderOut.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterIn adapterIn = new AdapterIn();
            viewHolder.item_reOrderOut.setAdapter(adapterIn);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView item_reOrderOut;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_reOrderOut = itemView.findViewById(R.id.item_reOrderOut);
            }
        }
    }

    class AdapterIn extends RecyclerView.Adapter<AdapterIn.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_in, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
