package com.mdm.di.components;

import android.app.Application;
import android.content.Context;

import com.mdm.AppController;
import com.mdm.SharedPrefsHelper;
import com.mdm.di.modules.ApplicationModule;
import com.mdm.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by janisharali on 08/12/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppController appController);

    @ApplicationContext
    Context getContext();

    Application getApplication();


    SharedPrefsHelper getPreferenceHelper();


}
