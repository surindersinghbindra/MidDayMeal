package com.mdm.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mdm.R;
import com.mdm.databinding.ActivityMainIntoBinding;
import com.mdm.db.AppDatabase;
import com.mdm.model.NewUser;
import com.mdm.model.RawData;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainIntoActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    //  private static final String TWITTER_KEY = "ENdxMM0DGGrRac3wK9jAbHlI6";
    //   private static final String TWITTER_SECRET = "WiIYd5SavYixZNEgIgWC967fNmpHMtNRQoW4Q8ClnrDvrB05EP";
    private ActivityMainIntoBinding activityMainIntroBinding;
    private String TAG = MainIntoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


     /*   Digits.Builder digitsBuilder = new Digits.Builder().withTheme(R.style.CustomDigitsTheme);


        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig),  digitsBuilder.build());
*/

        activityMainIntroBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_into);

        // DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);

   /*     digitsButton.setCallback(new AuthCallback() {
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
        });*/


    }


    public void homeActicty(View view) {
        activityMainIntroBinding.btGetStarted.setEnabled(false);
        final FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();


        if (userFirebase != null) {
            Query query = FirebaseDatabase.getInstance().getReference().child("users").child(userFirebase.getUid());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    Log.d(TAG, String.valueOf(userFirebase.getUid()));
                    if (dataSnapshot.exists()) {
                        NewUser user = dataSnapshot.getValue(NewUser.class);

                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userFirebase.getUid());

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("startCashBalance", Integer.parseInt(activityMainIntroBinding.etOpeningBalance.getText().toString()));
                        childUpdates.put("startWheatBalance", Integer.parseInt(activityMainIntroBinding.etOpeningWheat.getText().toString()));
                        childUpdates.put("startRiceBalance", Integer.parseInt(activityMainIntroBinding.etOpeningRice.getText().toString()));
                        mDatabase.updateChildren(childUpdates);

                     /*   if (user.getPhoneNumber() == null || user.getCollegeName() == null) {
                            startActivity(new Intent(MainIntoActivity.this, MainActivity.class));
                            finish();
                        } else {
                            callMainActivity();
                        }*/

                        // dataSnapshot is the "issue" node with all children with id 0
                             /*   for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    // do something with the individual "issues"
                                }*/
                        callMainActivity();
                    } else {

                        NewUser newUser = new NewUser(userFirebase.getUid(), userFirebase.getDisplayName(), Integer.parseInt(activityMainIntroBinding.etOpeningBalance.getText().toString()), Integer.parseInt(activityMainIntroBinding.etOpeningWheat.getText().toString()), Integer.parseInt(activityMainIntroBinding.etOpeningRice.getText().toString()));

                        FirebaseDatabase.getInstance().getReference().child("users").child(userFirebase.getUid()).setValue(newUser);
                            /*    Map<String, Object> childUpdates = new HashMap<>();
                                childUpdates.put("email", userFirebase.getEmail());
                                childUpdates.put("name", userFirebase.getDisplayName());
                                childUpdates.put("id", userFirebase.getUid());
                                childUpdates.put("image", userFirebase.getPhotoUrl());
                                mDatabase.updateChildren(childUpdates);*/

                        callMainActivity();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    databaseError.getDetails();
                    databaseError.getMessage();
                    databaseError.toException();
                }
            });


        }


    }

    private void callMainActivity() {

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
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
