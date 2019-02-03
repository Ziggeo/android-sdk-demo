package com.ziggeo.androidsdk.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    // put your tokens here
    public static final String APP_TOKEN = "";
    public static final String VIDEO_TOKEN = "";
    public static final String IMAGE_TOKEN = "";
    public static final String AUDIO_TOKEN = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        init();
    }

    protected int getLayoutId(){
        return 0;
    }

    protected void init(){

    }
}
