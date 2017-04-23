package com.mdm.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mdm.AppConstants;
import com.mdm.R;
import com.mdm.model.RawData;
import com.mdm.model.RawData_Table;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_ALARM = 1;

    private static final String DATA_RECEIVED = "data_received";
    private static final String INFORMATION = "information";
    private static final String DISCLAIMER = "disclaimer";

    private FloatingActionButton fab, fabWhatsApp;
    private TextView information, disclaimer;
    private boolean dataReceived = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long count = new Select(Method.count()).from(RawData.class).count();
// auto-unboxing does not go from Long to int directly, so
                Integer i = (int) (long) count;
                RawData rawData = SQLite.select().from(RawData.class).where(RawData_Table.id.is(i - 1)).querySingle();

                Intent intent = new Intent(MainActivity.this, NewEntryFormActivity.class);

                intent.putExtra(AppConstants.TIME, System.currentTimeMillis());
                Calendar rightNow = Calendar.getInstance();

                intent.putExtra(AppConstants.DAY_CODE, rightNow.get(Calendar.DAY_OF_WEEK) - 1);

                if ((rightNow.get(Calendar.DAY_OF_WEEK) - 1) == 0) {
                    Snackbar.make(fab, getString(R.string.sunday), Snackbar.LENGTH_LONG).show();
                } else {
                    startActivityForResult(intent, NEW_ALARM);
                }

            }
        });

        fabWhatsApp = (FloatingActionButton) findViewById(R.id.fabWhatsApp);
        fabWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "*RICE* 3 Kgs \n*WHEAT* 5 Kgs \n*FUND PENDING* 5 Kgs \nShared via *MDM-PUNJAB-APP(...)*");
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        information = (TextView) findViewById(R.id.information);
        disclaimer = (TextView) findViewById(R.id.disclaimer);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        dataReceived = savedInstanceState.getBoolean(DATA_RECEIVED, false);
        if (dataReceived) {
            disclaimer.setVisibility(View.VISIBLE);
            information.setText(savedInstanceState.getString(INFORMATION));
            disclaimer.setText(savedInstanceState.getString(DISCLAIMER));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(DATA_RECEIVED, dataReceived);
        if (dataReceived) {
            savedInstanceState.putString(INFORMATION, information.getText().toString());
            savedInstanceState.putString(DISCLAIMER, disclaimer.getText().toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == NEW_ALARM && data != null) {
            if (data.hasExtra(NewAlarmFormActivity.NEW_ALARM_ADDED)
                    && data.getExtras().getBoolean(NewAlarmFormActivity.NEW_ALARM_ADDED, false)) {

                // Handling the data received from the stepper form
                dataReceived = true;
                String title = data.getExtras().getString(NewAlarmFormActivity.STATE_TITLE);
                String description = data.getExtras().getString(NewAlarmFormActivity.STATE_DESCRIPTION);
                int hour = data.getExtras().getInt(NewAlarmFormActivity.STATE_TIME_HOUR);
                int minutes = data.getExtras().getInt(NewAlarmFormActivity.STATE_TIME_MINUTES);
             /*   String time = ((hour > 9) ? hour : ("0" + hour))
                        + ":" + ((minutes > 9) ? minutes : ("0" + minutes));*/
                //boolean[] weekDays = data.getExtras().getBooleanArray(NewAlarmFormActivity.STATE_WEEK_DAYS);
                information.setText("Studens Present" + title + "\n" +
                        "Fund Received" + description + "\n" +
                        "Wheate Received" + hour + "\n" +
                        "Rice Received" + minutes);
                disclaimer.setVisibility(View.VISIBLE);
                Snackbar.make(fab, getString(R.string.new_alarm_added), Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
