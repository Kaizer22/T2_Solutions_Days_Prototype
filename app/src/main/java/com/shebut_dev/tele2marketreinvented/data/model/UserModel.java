package com.shebut_dev.tele2marketreinvented.data.model;

import java.util.List;

public class UserModel {
    public String uID;
    public float balance;
    public String name;
    public String phone;
    public List<String> lotIDs;

    public UserModel(String uID, float balance,
                     String name, String phone,
                     List<String> lotIDs){
        this.uID = uID;
        this.balance = balance;
        this.name = name;
        this.phone = phone;
        this.lotIDs = lotIDs;

    }
}
