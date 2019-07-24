package com.ziggeo.androidsdk.demo.recorders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.callbacks.RecorderCallback;
import com.ziggeo.androidsdk.demo.BaseActivity;
import com.ziggeo.androidsdk.net.callbacks.ProgressCallback;
import com.ziggeo.androidsdk.net.models.VideoReadyForStreamingModel;
import com.ziggeo.androidsdk.widgets.cameraview.CameraView;
import com.ziggeo.demo.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;
import timber.log.Timber;

/**
 * Created by Alex Bedulin on 4/3/17.
 */
public class CameraViewActivity extends BaseActivity implements View.OnClickListener {
    public static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final Gson GSON = new Gson();
    CameraView cvCamera;
    FloatingActionButton fabRecord;
    FloatingActionButton fabTakePicture;
    FloatingActionButton fabSwitchCamera;
    FloatingActionButton fabStream;
    private View preparingStreamProgress;
    private File fileToSaveRecording;
    private Ziggeo ziggeo;

    private String videoToken;
    private String streamToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ziggeo = new Ziggeo(APP_TOKEN, this);
        setupCamera();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera_view;
    }

    private void setupCamera() {
        preparingStreamProgress = findViewById(com.ziggeo.androidsdk.R.id.ll_preparing_stream_progress);
        fabStream = findViewById(R.id.fab_stream);
        fabStream.setOnClickListener(this);
        fabRecord = findViewById(R.id.fab_record);
        fabRecord.setOnClickListener(this);
        fabTakePicture = findViewById(R.id.fab_take_picture);
        fabTakePicture.setOnClickListener(this);
        fabSwitchCamera = findViewById(R.id.fab_switch_camera);
        fabSwitchCamera.setOnClickListener(this);

        cvCamera = findViewById(R.id.cv_camera);
        cvCamera.setCameraCallback(new CameraView.CameraCallback() {
            @Override
            public void cameraOpened() {
                super.cameraOpened();
                Timber.d("cameraOpened");
            }

            @Override
            public void cameraClosed() {
                super.cameraClosed();
                Timber.d("cameraClosed");
            }
        });
        cvCamera.setRecorderCallback(new RecorderCallback() {
            @Override
            public void readyToRecord() {
                super.readyToRecord();
                Timber.d("readyToRecord");
            }

            @Override
            public void recordingStarted() {
                super.recordingStarted();
                Timber.d("recordingStarted");
            }

            @Override
            public void recordingStopped(@NonNull String path) {
                super.recordingStopped(path);
                Timber.d("recordingStopped:%s", path);
            }

            @Override
            public void recordingProgress(long time) {
                super.recordingProgress(time);
                Timber.d("recordingProgress:%s", time);
            }

            @Override
            public void onPictureTaken(@NonNull String path) {
                super.onPictureTaken(path);
                Timber.d("onPictureTaken:%s", path);
            }

            @Override
            public void streamingStarted() {
                super.streamingStarted();
                Timber.d("streamingStarted");
            }

            @Override
            public void streamingStopped() {
                super.streamingStopped();
                Timber.d("streamingStopped");
            }
        });

        updateIcons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCameraAccessGranted() && isRecordAudioGranted() && isWriteStorageGranted()) {
            cvCamera.start();
        } else {
            ActivityCompat.requestPermissions(this, VIDEO_PERMISSIONS, 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_stream:
                if (cvCamera.isStreaming()) {
                    stopStream();
                } else {
                    startStream();
                }
                break;
            case R.id.fab_record:
                if (cvCamera.isRecording()) {
                    cvCamera.stopRecording();
                } else {
                    if (prepareRecordingFile()) {
                        final int maxDuration = 0; // endless
                        cvCamera.startRecording(fileToSaveRecording.getPath(), maxDuration);
                    }
                }
                break;
            case R.id.fab_take_picture:
                cvCamera.takePicture();
                break;
            case R.id.fab_switch_camera:
                if (cvCamera.getFacing() == CameraView.FACING_FRONT) {
                    cvCamera.setFacing(CameraView.FACING_BACK);
                } else {
                    cvCamera.setFacing(CameraView.FACING_FRONT);
                }
                break;
        }

        updateIcons();
    }

    private void updateIcons() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                fabRecord.setImageResource(
                        cvCamera.isRecording() ? R.drawable.ic_stop_recording : R.drawable.ic_start_recording
                );

                fabSwitchCamera.setImageResource(
                        cvCamera.getFacing() == CameraView.FACING_FRONT ? R.drawable.ic_switch_camera_back : R.drawable.ic_switch_camera_front
                );
            }
        });
    }

    private boolean prepareRecordingFile() {
        if (fileToSaveRecording == null || !fileToSaveRecording.exists()) {
            fileToSaveRecording = new File(Environment.getExternalStorageDirectory() + "/Download", "tempRecord.mp4");
            try {
                fileToSaveRecording.getParentFile().mkdirs();
                return fileToSaveRecording.createNewFile();
            } catch (IOException e) {
                Timber.e(e);
                return false;
            }
        } else return true;
    }

    void showImagePreview(final byte[] data) {
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        new AlertDialog.Builder(this)
                .setView(iv)
                .setPositiveButton(R.string.common_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private boolean isCameraAccessGranted() {
        return isPermissionGranted(Manifest.permission.CAMERA);
    }

    private boolean isRecordAudioGranted() {
        return isPermissionGranted(Manifest.permission.RECORD_AUDIO);
    }

    private boolean isWriteStorageGranted() {
        return isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean isPermissionGranted(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void uploadFile() {
        ziggeo.videos().create(fileToSaveRecording, null, new ProgressCallback() {
            @Override
            public void onProgressUpdate(@NonNull String videoToken, @NonNull File file, long sentBytes, long totalBytes) {
                Timber.d("Sent " + sentBytes + "/" + totalBytes);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Timber.e("onFailure:%s", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Timber.d("onResponse:%s", response.body().string());
                Util.closeQuietly(response);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void startStream() {
        if (!cvCamera.isStreaming()) {
            showPreparingStreamProgress();

            HashMap<String, String> args = new HashMap<>();
            args.put("flash_recording", String.valueOf(true));
            ziggeo.videos().create(args, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Timber.e("onFailure:%s", e.toString());
                    hidePreparingStreamProgress();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        VideoReadyForStreamingModel model =
                                GSON.fromJson(response.body().string(), VideoReadyForStreamingModel.class);
                        videoToken = model.getVideoModel().getToken();
                        streamToken = model.getStreamModel().getToken();

                        hidePreparingStreamProgress();
                        cvCamera.startStream(APP_TOKEN, videoToken, streamToken);

                        Timber.d("Streaming to:%s %s", videoToken, streamToken);
                    } else {
                        onFailure(call, new IOException(String.valueOf(response.code())));
                    }
                    Util.closeQuietly(response);
                }
            });
        }
    }

    @SuppressLint("CheckResult")
    private void stopStream() {
        cvCamera.stopStream();

        ziggeo.streams().bind(videoToken, streamToken, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Timber.d("binded");
                } else {
                    onFailure(call, new IOException(String.valueOf(response.code())));
                }
            }
        });
    }

    private void showPreparingStreamProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                preparingStreamProgress.setVisibility(View.VISIBLE);
            }
        });
    }

    private void hidePreparingStreamProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                preparingStreamProgress.setVisibility(View.GONE);
            }
        });
    }

}
