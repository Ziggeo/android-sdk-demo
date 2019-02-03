package com.ziggeo.androidsdk.demo.players;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.callbacks.PlayerCallback;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.ui.theming.PlayerStyle;
import com.ziggeo.androidsdk.ui.theming.ZiggeoTheme;

import timber.log.Timber;

/**
 * Created by Alex Bedulin on 4/26/17.
 */
public class ZiggeoPlayerActivity extends BaseActivity {

    @Override
    protected void init() {
        super.init();
        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        ziggeo.setPlayerCallbacks(new PlayerCallback() {
            @Override
            public void loaded() {
                super.loaded();
                Timber.d("Player. Loaded");
            }

            @Override
            public void error(@NonNull Throwable throwable) {
                super.error(throwable);
                Timber.d(throwable, "Player. Error");
            }

            @Override
            public void playing() {
                super.playing();
                Timber.d("Player. Playing");
            }

            @Override
            public void paused() {
                super.paused();
                Timber.d("Player. Paused");
            }

            @Override
            public void ended() {
                super.ended();
                Timber.d("Player. Ended");
            }

            @Override
            public void seek(long millis) {
                super.seek(millis);
                Timber.d("Player. Seek");
            }

            @Override
            public void readyToPlay() {
                super.readyToPlay();
                Timber.d("Player. Ready to play");
            }
        });
        // video tokens or keys here
        String videoToPlay1 = "";
        ziggeo.startPlayer(videoToPlay1);
    }

    /**
     * Also see styles.xml for styling through XML
     */
    private void styling(@NonNull Ziggeo ziggeo) {
        ZiggeoTheme theme = new ZiggeoTheme();
        PlayerStyle playerStyle = new PlayerStyle.Builder()
                .controllerStyle(PlayerStyle.DEFAULT)
                .build();
        theme.setPlayerStyle(playerStyle);
    }
}
