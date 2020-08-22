package com.shebut_dev.tele2marketreinvented.data.data_manager.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStats;
import com.shebut_dev.tele2marketreinvented.data.model.Lot;
import com.shebut_dev.tele2marketreinvented.data.model.UserModel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataManagerFirebaseImpl implements DataManager {

    private FirebaseDatabase database;
    private DatabaseReference ref;

    public DataManagerFirebaseImpl(){
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
    }


    @Override
    public void testReq(String data, TestCallback testCallback) {
        ref.child("Lot").child("lot_id").child("type");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("FIREBASE_DATABASE", "DATA_CHANGED");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException().printStackTrace();
            }
        });
    }

    @Override
    public void getUserByID(String userID, GetUserByIDCallback callback) {
        ref.child("User").child(userID).child("type");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("FIREBASE_DATABASE", "DATA_CHANGED");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException().printStackTrace();
            }
        });
    }

    @Override
    public void getUserLots(String userID, GetUserLotsCallback callback) {
        List<Lot> lots = new LinkedList<>();
        ref.child("Lot").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyNode : snapshot.getChildren() ) {
                    Lot lot = keyNode.getValue(Lot.class);
                    lots.add(lot);
                }
                callback.onFinish(lots);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.toException());
            }
        });
    }

    @Override
    public void postUser(UserModel user,
                         PostUserCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put(user.uID, user);
        DatabaseReference ref = database.getReference().child("User");
        ref.updateChildren(map)
                .addOnSuccessListener(l -> callback.onFinish(user.uID))
                .addOnFailureListener(callback::onError);
    }

    @Override
    public void getGbStatistics(GetGbStatisticsCallback callback) {
        ref.child("DailyStats").child("GB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                callback.onFinish(snapshot.getValue(GBDailyStats.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.toException());
            }
        });
    }

    @Override
    public void postLot() {

    }

    @Override
    public void deleteLot() {

    }
}
