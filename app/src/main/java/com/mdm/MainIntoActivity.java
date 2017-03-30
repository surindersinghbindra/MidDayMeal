package com.mdm;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;
import com.mdm.databinding.ActivityMainIntoBinding;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Calendar;

public class MainIntoActivity extends AppCompatActivity {

    private ActivityMainIntoBinding activityMainIntroBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_into);


    }


    public void homeActicty(View view) {
        activityMainIntroBinding.btGetStarted.setEnabled(false);

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        // run asynchronous transactions easily, with expressive builders
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // do something in BG
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                calendar.get(Calendar.DAY_OF_MONTH);
                RawData rawData = new RawData();
                rawData.setDay(calendar.get(Calendar.DAY_OF_MONTH));
                rawData.setMonth(calendar.get(Calendar.MONTH));
                rawData.setYear(calendar.get(Calendar.YEAR));
                rawData.setOpeningBalanceMoney(Integer.parseInt(activityMainIntroBinding.etOpeningBalance.getText().toString()));
                rawData.setOpeningBalanceWheat(Integer.parseInt(activityMainIntroBinding.etOpeningWheat.getText().toString()));
                rawData.setOpeningBalanceRice(Integer.parseInt(activityMainIntroBinding.etOpeningRice.getText().toString()));
                FlowManager.getModelAdapter(RawData.class).insert(rawData);
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                startActivity(new Intent(MainIntoActivity.this, MainActivity.class));
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                error.printStackTrace();
                activityMainIntroBinding.btGetStarted.setEnabled(true);
            }
        }).build().execute();


    }
}
