package com.mdm;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

public class MainIntoActivity extends IntroActivity implements CashReceived.OnFragmentInteractionListener{

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

        addSlide(new SimpleSlide.Builder()
                .title("Xfghfghgfhf")
                .description("suiderrrr")
                .image(R.drawable.add)
                .background(R.color.mi_status_bar_background)
                .backgroundDark(R.color.mi_text_color_primary_dark)
                .scrollable(true)
                .build());


       final Slide loginSlide;

        loginSlide = new FragmentSlide.Builder()
                .background(R.color.colorAccent)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(CashReceived.newInstance("", ""))
                .build();
        addSlide(loginSlide);

        Slide loginSlid2e = new FragmentSlide.Builder()
                .background(R.color.colorAccent)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(CashReceived.newInstance("", ""))
                .build();
        addSlide(loginSlid2e);

        addOnNavigationBlockedListener(new OnNavigationBlockedListener() {
            @Override
            public void onNavigationBlocked(int position, int direction) {
                View contentView = findViewById(android.R.id.content);
                if (contentView != null) {
                    Slide slide = getSlide(position);

                    if (slide == loginSlide) {
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
