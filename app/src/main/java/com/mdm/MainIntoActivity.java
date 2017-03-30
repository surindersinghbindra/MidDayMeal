package com.mdm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
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
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.Calendar;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainIntoActivity extends AppCompatActivity {

    private ActivityMainIntoBinding activityMainIntroBinding;


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ENdxMM0DGGrRac3wK9jAbHlI6";
    private static final String TWITTER_SECRET = "WiIYd5SavYixZNEgIgWC967fNmpHMtNRQoW4Q8ClnrDvrB05EP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Digits.Builder digitsBuilder = new Digits.Builder().withTheme(R.style.CustomDigitsTheme);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig),  digitsBuilder.build());

      //  Fabric.with(this, new TwitterCore(authConfig), digitsBuilder.build());

        activityMainIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_into);

        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });



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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
