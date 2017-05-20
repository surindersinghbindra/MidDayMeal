package com.mdm.view.activities;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mdm.AppConstants;
import com.mdm.R;
import com.mdm.databinding.ActivityVerticalStepperFormBinding;
import com.mdm.db.AppDatabase;
import com.mdm.model.RawData;
import com.mdm.verticalstepperform.VerticalStepperFormLayout;
import com.mdm.verticalstepperform.fragments.BackConfirmationFragment;
import com.mdm.verticalstepperform.interfaces.VerticalStepperForm;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewEntryFormActivity extends AppCompatActivity implements VerticalStepperForm {

    public static final String NEW_ALARM_ADDED = "new_alarm_added";
    public static final String STATE_TITLE = "title";
    public static final String STATE_DESCRIPTION = "description";
    public static final String STATE_TIME_HOUR = "time_hour";
    public static final String STATE_TIME_MINUTES = "time_minutes";
    public static final String STATE_WEEK_DAYS = "week_days";
    // private static final int DAY_SELECT_STEP_NUM = 4;
    // Information about the steps/fields of the form
    private static final int STUDENT_STRENGH_STEP_NUM = 0;
    private static final int FUND_RECEIVED_STEP_NUM = 1;
    private static final int WHEATE_RECEIVED_STEP_NUM = 2;
    private static final int RICE_RECEIVED_STEP_NUM = 3;
    private static final int MIN_CHARACTERS_TITLE = 1;
    private ActivityVerticalStepperFormBinding activityVerticalStepperFormBinding;
    // Title step
    private EditText mStrenghEditText;
    // Description step
    private EditText mFundReceivedEditText;
    // Time step
    private EditText timeTextView;
    private TimePickerDialog timePicker;
    private Pair<Integer, Integer> time;
    // Week days step
    private boolean[] weekDays;
    private LinearLayout daysStepContent;
    private boolean confirmBack = true;
    private ProgressDialog progressDialog;
    private VerticalStepperFormLayout verticalStepperForm;


    //
    private int mStrengh = 0;
    private int mFundReceived = 0;
    private double mWheateRecieved = 0.0;
    private double mRiceRecieved = 0.0;
    private int dayCode = 0;
    private long time1 = 0L;
    private RawData rawData = new RawData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVerticalStepperFormBinding = DataBindingUtil.setContentView(NewEntryFormActivity.this, R.layout.activity_vertical_stepper_form);

        if (getIntent() != null) {
            dayCode = getIntent().getIntExtra(AppConstants.DAY_CODE, 0);
            time1 = getIntent().getLongExtra(AppConstants.TIME, 0L);
            rawData.setDayCode(dayCode);
            rawData.setTime(time1);
            activityVerticalStepperFormBinding.setRawData(rawData);
        }
        initializeActivity();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initializeActivity() {

        // Time step vars
        time = new Pair<>(8, 30);
        setTimePicker(8, 30);

        // Week days step vars
        weekDays = new boolean[7];

        // Vertical Stepper form vars
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
        String[] stepsTitles = getResources().getStringArray(R.array.steps_titles);

        //comment 1
        String[] stepsSubtitles = getResources().getStringArray(R.array.steps_subtitles);

        // Here we find and initialize the form
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, stepsTitles, this, this)
                //comment 3 lines
                .stepsSubtitles(stepsSubtitles)
                .materialDesignInDisabledSteps(true) // false by default
                .showVerticalLineWhenStepsAreCollapsed(true) // false by default

                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true)
                .init();

    }

    // METHODS THAT HAVE TO BE IMPLEMENTED TO MAKE THE LIBRARY WORK
    // (Implementation of the interface "VerticalStepperForm")

    @Override
    public View createStepContentView(int stepNumber) {
        // Here we generate the content view of the correspondent step and we return it so it gets
        // automatically added to the step layout (AKA stepContent)
        View view = null;
        switch (stepNumber) {
            case STUDENT_STRENGH_STEP_NUM:
                view = createStrenghStep();
                break;
            case FUND_RECEIVED_STEP_NUM:
                view = createFundReceivedStep();
                break;
            case WHEATE_RECEIVED_STEP_NUM:
                view = createWheateReceivedStep();
                break;
            case RICE_RECEIVED_STEP_NUM:
                view = createRiceReceivedStep();
                break;
        }
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case STUDENT_STRENGH_STEP_NUM:
                // When this step is open, we check that the title is correct
                if ((mStrenghEditText.getText().toString()).length() > 0) {
                    checkTitleStep(mStrenghEditText.getText().toString());
                }

                break;
            case FUND_RECEIVED_STEP_NUM:

            case WHEATE_RECEIVED_STEP_NUM:
            case RICE_RECEIVED_STEP_NUM:
                // As soon as they are open, these two steps are marked as completed because they
                // have default values
                verticalStepperForm.setStepAsCompleted(stepNumber);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            //  case DAY_SELECT_STEP_NUM:
            // When this step is open, we check the days to verify that at least one is selected
            //      checkDays();
            //      break;

        }
    }

    @Override
    public void sendData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.vertical_form_stepper_form_sending_data_message));
        executeDataSending();
    }

    // OTHER METHODS USED TO MAKE THIS EXAMPLE WORK

    private void executeDataSending() {

        // TODO Use here the data of the form as you wish
        // Fake data sending effect
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                    intent.putExtra(NEW_ALARM_ADDED, true);
                    intent.putExtra(STATE_TITLE, mStrenghEditText.getText().toString());
                    intent.putExtra(STATE_DESCRIPTION, mFundReceived + "");
                    intent.putExtra(STATE_TIME_HOUR, mWheateRecieved + "");
                    intent.putExtra(STATE_TIME_MINUTES, mRiceRecieved + "");
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    // You must set confirmBack to false before calling finish() to avoid the confirmation dialog
                    confirmBack = false;

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("entries").push().setValue(rawData);

                    RawData rawData1 = new RawData();
                    rawData1.setRecievedBalanceMoney(rawData.getRecievedBalanceMoney());
                    rawData1.setRecievedBalanceWheat(rawData.getRecievedBalanceWheat());
                    rawData1.setRecievedBalanceRice(rawData.getRecievedBalanceRice());
                    rawData1.setRecievedBalanceMoney(rawData.getRecievedBalanceMoney());
                    DatabaseReference reference2 = reference.child("entry").push();
                    Map<String, Object> childUpdates1 = new HashMap<>();
                    childUpdates1.put("lastUpdateOn", System.currentTimeMillis());


                    reference2.updateChildren(childUpdates1);

                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("lastUpdateOn", System.currentTimeMillis());
                    mDatabase.updateChildren(childUpdates);


                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }); // You should delete this code and add yours


        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);

        // run asynchronous transactions easily, with expressive builders
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // do something in BG
                rawData.setRecievedBalanceMoney(mFundReceived);
                rawData.setRecievedBalanceWheat(mWheateRecieved);
                rawData.setRecievedBalanceRice(mRiceRecieved);

                FlowManager.getModelAdapter(RawData.class).insert(rawData);
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {


                thread.start();
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                error.printStackTrace();
            }
        }).build().execute();


    }

    private View createStrenghStep() {
        // This step view is generated programmatically
        mStrenghEditText = new EditText(this);
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(4);
        mStrenghEditText.setFilters(filterArray);
        mStrenghEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mStrenghEditText.setHint(R.string.form_hint_title);
        mStrenghEditText.setSingleLine(true);
        mStrenghEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    rawData.setStrength(Long.parseLong(s.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    rawData.setStrength(Long.parseLong("0"));
                }

                checkTitleStep(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mStrenghEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (checkTitleStep(v.getText().toString())) {
                    verticalStepperForm.goToNextStep();
                }
                return false;
            }
        });

        return mStrenghEditText;
    }

    private View createFundReceivedStep() {
        mFundReceivedEditText = new EditText(this);
        mFundReceivedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mFundReceivedEditText.setHint(R.string.form_hint_description);
        mFundReceivedEditText.setSingleLine(true);
        mFundReceivedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    mFundReceived = Integer.parseInt(s.toString());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mFundReceivedEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });
        return mFundReceivedEditText;
    }

    private View createWheateReceivedStep() {
        EditText mWheateReceivedEditText = new EditText(this);
        mWheateReceivedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mWheateReceivedEditText.setHint(R.string.form_hint_wighted_things);
        mWheateReceivedEditText.setSingleLine(true);
        mWheateReceivedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    mWheateRecieved = Double.parseDouble(s.toString());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mWheateReceivedEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });
        return mWheateReceivedEditText;
    }

    private View createRiceReceivedStep() {
        EditText mRiceReceivedEditText = new EditText(this);
        mRiceReceivedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mRiceReceivedEditText.setHint(R.string.form_hint_wighted_things);
        mRiceReceivedEditText.setSingleLine(true);
        mRiceReceivedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    mRiceRecieved = Double.parseDouble(s.toString());

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mRiceReceivedEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });
        return mRiceReceivedEditText;
    }

    private View createTimeStep() {
        // This step view is generated by inflating a layout XML file
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout timeStepContent =
                (LinearLayout) inflater.inflate(R.layout.step_time_layout, null, false);
        timeTextView = (EditText) timeStepContent.findViewById(R.id.time);
        timeTextView.setInputType(InputType.TYPE_CLASS_NUMBER);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });
        timeTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });
        return timeStepContent;
    }


    private View createDaySelectStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        daysStepContent = (LinearLayout) inflater.inflate(
                R.layout.step_days_of_week_layout, null, false);

        String[] weekDays = getResources().getStringArray(R.array.week_days);
        for (int i = 0; i < weekDays.length; i++) {
            final int index = i;
            final LinearLayout dayLayout = getDayLayout(index);
            if (index < 5) {
                activateDay(index, dayLayout, false);
            } else {
                deactivateDay(index, dayLayout, false);
            }

            dayLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((boolean) v.getTag()) {
                        deactivateDay(index, dayLayout, true);
                    } else {
                        activateDay(index, dayLayout, true);
                    }
                }
            });

            final TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
            dayText.setText(weekDays[index]);
        }
        return daysStepContent;
    }

    private void setTimePicker(int hour, int minutes) {
        timePicker = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setTime(hourOfDay, minute);
                    }
                }, hour, minutes, true);
    }

    private boolean checkTitleStep(String title) {
        boolean titleIsCorrect = false;
        Integer temp = 0;
        try {
            temp = Integer.parseInt(title);
            mStrengh = temp;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (temp >= MIN_CHARACTERS_TITLE) {
            titleIsCorrect = true;

            verticalStepperForm.setActiveStepAsCompleted();
            // Equivalent to: verticalStepperForm.setStepAsCompleted(STUDENT_STRENGH_STEP_NUM);

        } else {
            String titleErrorString = getResources().getString(R.string.error_title_min_characters);
            String titleError = String.format(titleErrorString, MIN_CHARACTERS_TITLE);

            verticalStepperForm.setActiveStepAsUncompleted(titleError);
            // Equivalent to: verticalStepperForm.setStepAsUncompleted(STUDENT_STRENGH_STEP_NUM, titleError);

        }

        return titleIsCorrect;
    }

    private void setTime(int hour, int minutes) {
        time = new Pair<>(hour, minutes);
        String hourString = ((time.first > 9) ?
                String.valueOf(time.first) : ("0" + time.first));
        String minutesString = ((time.second > 9) ?
                String.valueOf(time.second) : ("0" + time.second));
        String time = hourString + ":" + minutesString;
        timeTextView.setText(time);
    }

    private void activateDay(int index, LinearLayout dayLayout, boolean check) {
        weekDays[index] = true;
//
        //   dayLayout.setTag(true);

        Drawable bg = ContextCompat.getDrawable(getBaseContext(),
                R.drawable.circle_step_done);
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        bg.setColorFilter(new PorterDuffColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN));
        dayLayout.setBackgroundDrawable(bg);

        TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
        dayText.setTextColor(Color.rgb(255, 255, 255));

        if (check) {
            checkDays();
        }
    }

    private void deactivateDay(int index, LinearLayout dayLayout, boolean check) {
        weekDays[index] = false;

        dayLayout.setTag(false);

        dayLayout.setBackgroundResource(0);

        TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
        int colour = ContextCompat.getColor(getBaseContext(), R.color.colorPrimary);
        dayText.setTextColor(colour);

        if (check) {
            checkDays();
        }
    }

    private boolean checkDays() {
        boolean thereIsAtLeastOneDaySelected = false;
        for (int i = 0; i < weekDays.length && !thereIsAtLeastOneDaySelected; i++) {
            if (weekDays[i]) {
                //     verticalStepperForm.setStepAsCompleted(DAY_SELECT_STEP_NUM);
                thereIsAtLeastOneDaySelected = true;
            }
        }
        if (!thereIsAtLeastOneDaySelected) {
            //   verticalStepperForm.setStepAsUncompleted(DAY_SELECT_STEP_NUM, null);
        }

        return thereIsAtLeastOneDaySelected;
    }

    private LinearLayout getDayLayout(int i) {
        int id = daysStepContent.getResources().getIdentifier(
                "day_" + i, "id", getPackageName());
        return (LinearLayout) daysStepContent.findViewById(id);
    }

    // CONFIRMATION DIALOG WHEN USER TRIES TO LEAVE WITHOUT SUBMITTING

    private void confirmBack() {
        if (confirmBack && verticalStepperForm.isAnyStepCompleted()) {
            BackConfirmationFragment backConfirmation = new BackConfirmationFragment();
            backConfirmation.setOnConfirmBack(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmBack = true;
                }
            });
            backConfirmation.setOnNotConfirmBack(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmBack = false;
                    finish();
                }
            });
            backConfirmation.show(getSupportFragmentManager(), null);
        } else {
            confirmBack = false;
            finish();
        }
    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && confirmBack) {
            confirmBack();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        confirmBack();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissDialog();
    }

    // SAVING AND RESTORING THE STATE

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Saving title field
        if (mStrenghEditText != null) {
            savedInstanceState.putString(STATE_TITLE, mStrenghEditText.getText().toString());
        }

        // Saving description field
        if (mFundReceivedEditText != null) {
            savedInstanceState.putString(STATE_DESCRIPTION, mFundReceivedEditText.getText().toString());
        }

        // Saving time field
        if (time != null) {
            savedInstanceState.putInt(STATE_TIME_HOUR, time.first);
            savedInstanceState.putInt(STATE_TIME_MINUTES, time.second);
        }

        // Saving week days field
        if (weekDays != null) {
            savedInstanceState.putBooleanArray(STATE_WEEK_DAYS, weekDays);
        }

        // The call to super method must be at the end here
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        // Restoration of title field
        if (savedInstanceState.containsKey(STATE_TITLE)) {
            String title = savedInstanceState.getString(STATE_TITLE);
            mStrenghEditText.setText(title);
        }

        // Restoration of description field
        if (savedInstanceState.containsKey(STATE_DESCRIPTION)) {
            String description = savedInstanceState.getString(STATE_DESCRIPTION);
            mFundReceivedEditText.setText(description);
        }

        // Restoration of time field
        if (savedInstanceState.containsKey(STATE_TIME_HOUR)
                && savedInstanceState.containsKey(STATE_TIME_MINUTES)) {
            int hour = savedInstanceState.getInt(STATE_TIME_HOUR);
            int minutes = savedInstanceState.getInt(STATE_TIME_MINUTES);
            time = new Pair<>(hour, minutes);
            setTime(hour, minutes);
            if (timePicker == null) {
                setTimePicker(hour, minutes);
            } else {
                timePicker.updateTime(hour, minutes);
            }
        }

        // Restoration of week days field
        if (savedInstanceState.containsKey(STATE_WEEK_DAYS)) {
            weekDays = savedInstanceState.getBooleanArray(STATE_WEEK_DAYS);
            if (weekDays != null) {
                for (int i = 0; i < weekDays.length; i++) {
                    LinearLayout dayLayout = getDayLayout(i);
                    if (weekDays[i]) {
                        activateDay(i, dayLayout, false);
                    } else {
                        deactivateDay(i, dayLayout, false);
                    }
                }
            }
        }

        // The call to super method must be at the end here
        super.onRestoreInstanceState(savedInstanceState);
    }

}
