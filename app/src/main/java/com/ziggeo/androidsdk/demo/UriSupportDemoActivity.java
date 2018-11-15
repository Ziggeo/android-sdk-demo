package com.ziggeo.androidsdk.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.ziggeo.demo.R;

import butterknife.OnClick;

public class UriSupportDemoActivity extends BaseActivity {
    // put your tokens here
    private static final String APP_TOKEN = "";
    private static final String VIDEO_TOKEN = "";
    private static final String IMAGE_TOKEN = "";
    private static final String AUDIO_TOKEN = "";
    private static final String STUB = "1";

    private static final String URL_PLAY_VIDEO = "ziggeo://play?v=" + VIDEO_TOKEN + "&app=" + APP_TOKEN;
    private static final String URL_VIEW_IMAGE = "ziggeo://play?i=" + IMAGE_TOKEN + "&app=" + APP_TOKEN;
    private static final String URL_PLAY_AUDIO = "ziggeo://play?a=" + AUDIO_TOKEN + "&app=" + APP_TOKEN;
    private static final String URL_RECORD_SCREEN = "ziggeo://record?s=" + STUB + "&app=" + APP_TOKEN;
    private static final String URL_RECORD_VIDEO = "ziggeo://record?v=" + STUB + "&app=" + APP_TOKEN;
    private static final String URL_RECORD_AUDIO = "ziggeo://record?a=" + STUB + "&app=" + APP_TOKEN;
    private static final String URL_CAPTURE_IMAGE = "ziggeo://record?i=" + STUB + "&app=" + APP_TOKEN;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_uris_sample;
    }

    @OnClick(R.id.btn_play_video)
    void onPlayVideoClicked() {
        postViewIntent(URL_PLAY_VIDEO);
    }

    @OnClick(R.id.btn_view_image)
    void onViewImageClicked() {
        postViewIntent(URL_VIEW_IMAGE);
    }

    @OnClick(R.id.btn_play_audio)
    void onPlayAudioClicked() {
        postViewIntent(URL_PLAY_AUDIO);
    }

    @OnClick(R.id.btn_record_screen)
    void onRecordScreenClicked() {
        postViewIntent(URL_RECORD_SCREEN);
    }

    @OnClick(R.id.btn_record_video)
    void onRecordVideoClicked() {
        postViewIntent(URL_RECORD_VIDEO);
    }

    @OnClick(R.id.btn_record_audio)
    void onRecordAudioClicked() {
        postViewIntent(URL_RECORD_AUDIO);
    }

    @OnClick(R.id.btn_capture_image)
    void onCaptureImageClicked() {
        postViewIntent(URL_CAPTURE_IMAGE);
    }

    private void postViewIntent(@NonNull String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
