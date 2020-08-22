package com.shebut_dev.tele2marketreinvented.presentation.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shebut_dev.tele2marketreinvented.R;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;

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

    public void bind(LotModel lotModelOnBind){
        count.setText(String.valueOf(lotModelOnBind.nominal));
        price.setText(String.valueOf(lotModelOnBind.price));
        //TODO текстовая интерпретация даты
        timestamp.setText(lotModelOnBind.creationDate);
        type.setText(lotModelOnBind.lotType);
        lotID.setText(lotModelOnBind.lotID);
    }
}
