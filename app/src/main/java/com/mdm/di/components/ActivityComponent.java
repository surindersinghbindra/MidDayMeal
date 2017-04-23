package com.mdm.di.components;


import com.mdm.di.modules.ActivityModule;
import com.mdm.di.scope.PerActivity;
import com.mdm.view.activities.MainActivity;

import dagger.Component;

/**
 * Created by janisharali on 08/12/16.
 */

@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
