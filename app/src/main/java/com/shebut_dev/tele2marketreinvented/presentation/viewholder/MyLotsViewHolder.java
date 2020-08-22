package com.shebut_dev.tele2marketreinvented.presentation.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.model.Lot;

public class MyLotsViewHolder extends RecyclerView.ViewHolder {

    private TextView count;
    private TextView price;
    private TextView timestamp;
    private TextView type;
    private TextView lotID;

    public MyLotsViewHolder(@NonNull View itemView) {
        super(itemView);

        count = itemView.findViewById(R.id.items_count);
        price = itemView.findViewById(R.id.items_price);
        timestamp = itemView.findViewById(R.id.items_timestamp);
        type = itemView.findViewById(R.id.lot_type);
        lotID = itemView.findViewById(R.id.lot_id);
    }

    public void bind(Lot lotOnBind){
        count.setText(String.valueOf(lotOnBind.nominal));
        price.setText(String.valueOf(lotOnBind.price));
        //TODO текстовая интерпретация даты
        timestamp.setText(lotOnBind.creationDate);
        type.setText(lotOnBind.lotType);
        lotID.setText(lotOnBind.lotID);
    }
}
