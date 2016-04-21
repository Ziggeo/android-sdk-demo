package com.ziggeo.ziggeodemowithoutsources;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.common.eventbus.Subscribe;
import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.eventbus.BusProvider;
import com.ziggeo.androidsdk.eventbus.events.CreateVideoErrorEvent;
import com.ziggeo.androidsdk.eventbus.events.VideoSentEvent;

public class DemoActivityFullscreenRecorder extends Activity {

    public static final String TAG = DemoActivityFullscreenRecorder.class.getSimpleName();

    public static final String APP_TOKEN = ""; // TODO place your token here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BusProvider.getInstance().register(this);

        Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
        long maxDuration = 20000L;
        ziggeo.createVideo(this, maxDuration);
    }

    @Subscribe
    public void onVideoSent(VideoSentEvent event) {
        Log.e(TAG, "Video successfully sent. Video token is: " + event.getVideoToken());
    }

    @Subscribe
    public void onCreateVideoError(CreateVideoErrorEvent event) {
        Log.e(TAG, "Error while sending the video.");
    }

}
