package com.mdm;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

public class MainIntoActivity extends IntroActivity implements StockAndCashReceivedFragment.OnFragmentInteractionListener ,WheatReceivedFragment.OnFragmentInteractionListener,PrevBalanceFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);
        setButtonBackFunction(1);
        setButtonNextFunction(1);
        setButtonBackVisible(true);
        setButtonNextVisible(true);
        setButtonCtaVisible(true);
        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_TEXT);


        //   setContentView(R.layout.activity_main_into);

        final Slide wheateRecieved = new FragmentSlide.Builder()
                .background(R.color.wheate)
                .backgroundDark(R.color.wheateDark)
                .fragment(StockAndCashReceivedFragment.newInstance("", ""))
                .build();
        addSlide(wheateRecieved);




        addOnNavigationBlockedListener(new OnNavigationBlockedListener() {
            @Override
            public void onNavigationBlocked(int position, int direction) {
                View contentView = findViewById(android.R.id.content);
                if (contentView != null) {
                    Slide slide = getSlide(position);

                    if (slide == wheateRecieved) {
                        Snackbar.make(contentView, "thtrhthtyh", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
