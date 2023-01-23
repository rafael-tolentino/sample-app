package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SecondScreenActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putString("screen_name", "tela2 teste 1 setCurrentScreen");
        mFirebaseAnalytics.logEvent("SCREENVIEW_CUSTOMIZADO", bundle);
        mFirebaseAnalytics.setCurrentScreen(this, "teste 1 setCurrentScreen", null);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.SCREEN_NAME, "tela2 teste 1");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params);
        Bundle params2 = new Bundle();
        params2.putString("screen_name", "tela2 teste 1");
        mFirebaseAnalytics.logEvent("SCREENVIEW_CUSTOMIZADO", params2);
    }
    }

