package com.ziggeo.androidsdk.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ziggeo.androidsdk.Ziggeo;

/**
 * Created by Alex Bedulin on 4/26/17.
 */

public class PlayerActivity extends AppCompatActivity {

    public static final String APP_TOKEN = ""; // TODO place your token here

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);

        // your own container can be here
        final int fragmentContainerResId = android.R.id.content;
        final String videoTokenOrKey = "";// TODO place token or key of the video you want to play
        ziggeo.attachPlayer(getSupportFragmentManager(), fragmentContainerResId, videoTokenOrKey);
    }
}
