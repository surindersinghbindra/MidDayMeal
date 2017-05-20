package com.mdm.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.raizlabs.android.dbflow.annotation.Column;

import java.util.Date;

/**
 * Created by surinder on 20-May-17.
 */

@IgnoreExtraProperties
public class EntryRequiredData {

    @Column
    public long time;

    @Column(defaultValue = "0")
    public Date dayOfMonth;


    @Column(defaultValue = "0")
    public int month;

    @Column(defaultValue = "0")
    public int year;

    @Column(defaultValue = "0")
    public int dayCode;

}
