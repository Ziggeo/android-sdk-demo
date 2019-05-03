package com.ziggeo.androidsdk.demo.recorders;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.callbacks.IRecorderCallback;
import com.ziggeo.androidsdk.callbacks.RecorderCallback;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.recorder.MicSoundLevel;
import com.ziggeo.androidsdk.recorder.RecorderConfig;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class CameraFullscreenRecorderActivity extends BaseActivity {

    private Ziggeo ziggeo;
    private int progressPercent = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ziggeo = new Ziggeo(APP_TOKEN, CameraFullscreenRecorderActivity.this);
        RecorderConfig config = new RecorderConfig.Builder()
                .callback(prepareCallback())
                .sendImmediately(false)
                .enableCoverShot(false)
                .maxDuration(20000)
                .build();
        ziggeo.setRecorderConfig(config);
        ziggeo.setSensorCallback(level -> Timber.d("Sensors. lightSensorLevel:%s", level));
        ziggeo.startCameraRecorder();
    }

    private IRecorderCallback prepareCallback() {
        return new RecorderCallback() {
            @Override
            public void loaded() {
                super.loaded();
                Timber.d("Recorder. Loaded");
            }

            @Override
            public void manuallySubmitted() {
                super.manuallySubmitted();
                Timber.d("Recorder. ManuallySubmitted");
            }

            @Override
            public void uploaded(@NonNull String path, @NonNull String token) {
                super.uploaded(path, token);
                Timber.d("Recorder. Uploaded. Token:%s, Path:%s", token, path);
            }

            @Override
            public void recordingStarted() {
                super.recordingStarted();
                Timber.d("Recorder. RecordingStarted");
            }

            @Override
            public void recordingStopped(@NonNull String path) {
                super.recordingStopped(path);
                Timber.d("Recorder. RecordingStopped. Path:%s", path);
            }

            @Override
            public void uploadingStarted(@NonNull String path) {
                super.uploadingStarted(path);
                Timber.d("Recorder. UploadingStarted. Path:%s", path);
            }

            @Override
            public void countdown(int timeLeft) {
                super.countdown(timeLeft);
                Timber.d("Recorder. Countdown:%s", timeLeft);
            }

            @Override
            public void recordingProgress(long time) {
                super.recordingProgress(time);
                Timber.d("Recorder. RecordingProgress:%s", time);
            }

            @Override
            public void uploadProgress(@NonNull String videoToken, @NonNull File file, long uploaded, long total) {
                super.uploadProgress(videoToken, file, uploaded, total);
                int newProgr = (int) ((float) uploaded / total * 100);
                if (newProgr > progressPercent) {
                    progressPercent = newProgr;
                    Timber.d("Recorder. UploadProgress. Token:%s, %s/%s, Path:%s", videoToken,
                            uploaded, total, file.getAbsolutePath());
                }
            }

            @Override
            public void readyToRecord() {
                super.readyToRecord();
                Timber.d("Recorder. ReadyToRecord");
            }

            @Override
            public void accessForbidden(@NonNull List<String> permissions) {
                super.accessForbidden(permissions);
                Timber.d("Recorder. AccessForbidden:%s", Arrays.toString(permissions.toArray()));
            }

            @Override
            public void accessGranted() {
                super.accessGranted();
                Timber.d("Recorder. AccessGranted");
            }

            @Override
            public void processing(@NonNull String token) {
                super.processing(token);
                Timber.d("Recorder. Processing. Token:%s", token);
            }

            @Override
            public void processed(@NonNull String token) {
                super.processed(token);
                Timber.d("Recorder. Processed. Token:%s", token);
            }

            @Override
            public void verified(@NonNull String token) {
                super.verified(token);
                Timber.d("Recorder. Verified. Token:%s", token);
            }

            @Override
            public void noCamera() {
                super.noCamera();
                Timber.d("Recorder. NoCamera");
            }

            @Override
            public void noMicrophone() {
                super.noMicrophone();
                Timber.d("Recorder. NoMicrophone");
            }

            @Override
            public void hasCamera() {
                super.hasCamera();
                Timber.d("Recorder. HasCamera");
            }

            @Override
            public void hasMicrophone() {
                super.hasMicrophone();
                Timber.d("Recorder. HasMicrophone");
            }

            @Override
            public void microphoneHealth(@NonNull MicSoundLevel level) {
                super.microphoneHealth(level);
                Timber.d("Recorder. microphoneHealth:%s", level);
            }

            @Override
            public void canceledByUser() {
                super.canceledByUser();
                Timber.d("Recorder. Canceled ByUser");
            }

            @Override
            public void error(@NonNull Throwable throwable) {
                super.error(throwable);
                Timber.d(throwable, "Recorder. Error");
            }
        };
    }

}