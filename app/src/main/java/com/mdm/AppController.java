package com.mdm;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by think360 on 16/03/17.
 */

/** Example from GitHub.com
 * https://github.com/ernestoyaquello/vertical-stepper-form
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        Stetho.initializeWithDefaults(this);
    }
}
