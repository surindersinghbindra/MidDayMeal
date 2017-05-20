package com.mdm.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by surinder on 20-May-17.
 */
@IgnoreExtraProperties
public class NewUser {

    public String uid;
    public String userName;
    public int startCashBalance;
    public int startWheatBalance;
    public int startRiceBalance;
    public long lastUpdateOn;

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

    public long getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(long lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }


}
