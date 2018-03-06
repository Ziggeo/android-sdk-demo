package com.ziggeo.androidsdk.demo.players;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.vimeo.networking.Configuration;
import com.vimeo.networking.GsonDeserializer;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.error.VimeoError;
import com.ziggeo.demo.R;

/**
 * Created by alexa on 22.01.2018.
 */
public class VimeoPlayerActivity extends AppCompatActivity {

    private static final String TAG = VimeoPlayerActivity.class.getSimpleName();

    private VideoView videoView;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.video_view);
        pbProgress = findViewById(R.id.pb_progress);
        initVimeoPlayer();
    }

    private void initVimeoPlayer() {
        // replace with proper values
        final String videoId = "VIDEO_ID";
        final String clientId = "CLIENT_ID";
        final String clientSecret = "CLIENT_SECRET";

        final String scope = "public private";
        if (!TextUtils.isEmpty(videoId) && !TextUtils.isEmpty(clientId) && !TextUtils.isEmpty(clientSecret)) {
            VimeoClient.initialize(new Configuration.Builder(clientId, clientSecret, scope, null, new GsonDeserializer())
                    .build());
            showProgress(true);
            VimeoClient.getInstance().fetchNetworkContent("/videos/" + videoId, new ModelCallback<Video>(Video.class) {
                @Override
                public void success(Video video) {
                    if (video != null && video.getPlay() != null && video.getPlay().getDashVideoFile() != null
                            && video.getPlay().getDashVideoFile().getLink() != null) {
                        initializeSimplePlayer(
                                Uri.parse(video.getPlay().getDashVideoFile().getLink())
                        );
                    } else {
                        Log.e(TAG, "No file like in Vimeo response");
                    }
                }

                @Override
                public void failure(VimeoError error) {
                    showProgress(false);
                    Log.e(TAG, "Error code: " + error.getErrorCode());
                    Log.e(TAG, "Error message: " + error.getErrorMessage());
                    Log.e(TAG, "Dev error message: " + error.getDeveloperMessage());
                }
            });
        }
    }

    private void initializeSimplePlayer(@NonNull Uri uri) {
        videoView.setVideoURI(uri);
        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView);
        videoView.setMediaController(controller);
        showProgress(true);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                showProgress(false);
            }
        });
        videoView.start();
    }

    public void showProgress(boolean show) {
        pbProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
