package com.shebut_dev.tele2marketreinvented.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
import com.shebut_dev.tele2marketreinvented.presentation.activity.MainActivity;
import com.shebut_dev.tele2marketreinvented.presentation.fragment.HomeFragment;
import com.shebut_dev.tele2marketreinvented.presentation.viewholder.MyLotsViewHolder;

import java.util.LinkedList;
import java.util.List;

public class MyLotsAdapter extends RecyclerView.Adapter<MyLotsViewHolder> {

    private List<LotModel> lotModels = new LinkedList<>();
    private HomeFragment parentFragment;
    private Context context;

    public MyLotsAdapter(Context context){
        this.context = context;
    }



    @NonNull
    @Override
    public MyLotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_lot, parent, false);
        return new MyLotsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLotsViewHolder holder, int position) {
        LotModel lotModelOnBind = lotModels.get(position);
        if (lotModelOnBind != null){
            holder.itemView.setOnClickListener(l ->{
                String lotID = lotModelOnBind.lotID;
                if (context != null && context instanceof MainActivity){
                    ((MainActivity)context).navigateToEditLot(lotID);
                }
            });
            holder.bind(lotModelOnBind);
        }
    }

    @Override
    public int getItemCount() {
        return (this.lotModels != null) ? this.lotModels.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLotModels(List<LotModel> lotModels){
        this.lotModels.clear();
        this.lotModels.addAll(lotModels);
        notifyDataSetChanged();
    }
}
