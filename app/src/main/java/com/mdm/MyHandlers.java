package com.mdm;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mdm.model.RawData;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by think360 on 16/03/17.
 */

public class MyHandlers {

    public void onClickFriend(View view) {
        Log.i(MyHandlers.class.getSimpleName(), "Now Friend");
        RawData rawData = new RawData();
        rawData.setDayCode(2);
        rawData.setStrength(94.0);
        rawData.getJeera();
        rawData.getMirch();
        ((TextView) view).setText(rawData.mirch + "");
        FlowManager.getModelAdapter(RawData.class).insert(rawData);
    }
}
