package com.ziggeo.androidsdk.demo.players;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.callbacks.PlayerCallback;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.player.PlayerConfig;
import com.ziggeo.androidsdk.ui.theming.PlayerStyle;

import timber.log.Timber;

/**
 * Created by Alex Bedulin on 4/26/17.
 */
public class ZiggeoPlayerActivity extends BaseActivity {

    @Override
    protected void init() {
        super.init();
        Ziggeo ziggeo = new Ziggeo(APP_TOKEN, this);
        configurePlayer(ziggeo);
        // video tokens or keys here
        String videoToPlay1 = "";
        ziggeo.startPlayer(videoToPlay1);
    }

    /**
     * Also see styles.xml for configurePlayer through XML
     */
    private void configurePlayer(@NonNull Ziggeo ziggeo) {
        PlayerConfig playerConfig = new PlayerConfig.Builder()
                .style(new PlayerStyle.Builder()
                        .controllerStyle(PlayerStyle.DEFAULT)
                        .build())
                .callback(new PlayerCallback() {
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
                })
                .build();

        ziggeo.configurePlayer(playerConfig);
    }
}
