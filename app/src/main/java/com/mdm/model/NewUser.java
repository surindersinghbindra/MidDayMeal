package com.mdm.model;

/**
 * Created by surinder on 20-May-17.
 */

public class NewUser {

    public String uid;
    public String userName;
    public int startCashBalance;
    public int startWheatBalance;
    public int startRiceBalance;

    public NewUser() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public NewUser(String uid, String userName, int startCashBalance, int startWheatBalance, int startRiceBalance) {
        this.uid = uid;
        this.userName = userName;
        this.startCashBalance = startCashBalance;
        this.startWheatBalance = startWheatBalance;
        this.startRiceBalance = startRiceBalance;
    }


}
