package com.shebut_dev.tele2marketreinvented.data.data_manager.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shebut_dev.tele2marketreinvented.data.data_manager.DataManager;
import com.shebut_dev.tele2marketreinvented.data.model.GBDailyStatsModel;
import com.shebut_dev.tele2marketreinvented.data.model.LotModel;
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
        List<LotModel> lotModels = new LinkedList<>();
        ref.child("Lot").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyNode : snapshot.getChildren() ) {
                    LotModel lotModel = keyNode.getValue(LotModel.class);
                    lotModels.add(lotModel);
                }
                callback.onFinish(lotModels);
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
                callback.onFinish(snapshot.getValue(GBDailyStatsModel.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.toException());
            }
        });
    }

    @Override
    public void getLot(String userId, String lotID, GetLotCallback callback) {
        ref.child("Lot").child(userId).child(lotID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                callback.onFinish(snapshot.getValue(LotModel.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.toException());
            }
        });
    }

    @Override
    public void postLot(String userID, LotModel lotModel, PostLotCallback callback) {
        String firebaseID = ref.push().getKey();
        Map<String, Object> map = new HashMap<>();
        lotModel.lotID = firebaseID;
        map.put(firebaseID, lotModel);
        DatabaseReference ref = database.getReference().child("Lot").child(userID);
        ref.updateChildren(map)
                .addOnSuccessListener(l -> callback.onFinish())
                .addOnFailureListener(callback::onError);
    }

    @Override
    public void editLot(String userID, LotModel lotModel, EditLotCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put(lotModel.lotID, lotModel);
        DatabaseReference ref = database.getReference().child("Lot").child(userID);
        ref.updateChildren(map)
                .addOnSuccessListener(l -> callback.onFinish())
                .addOnFailureListener(callback::onError);
    }


    @Override
    public void deleteLot() {

    }
}
