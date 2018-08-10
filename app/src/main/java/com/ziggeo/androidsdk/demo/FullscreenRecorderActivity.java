package com.ziggeo.androidsdk.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.net.callbacks.ProgressCallback;
import com.ziggeo.androidsdk.net.exceptions.ResponseException;
import com.ziggeo.androidsdk.recording.VideoRecordingCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.Util;

public class FullscreenRecorderActivity extends AppCompatActivity implements ProgressCallback {

    public static final String TAG = FullscreenRecorderActivity.class.getSimpleName();

    public static final String APP_TOKEN = ""; // place your token here

    private Ziggeo ziggeo;
    private int progressPercent = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ziggeo = new Ziggeo(APP_TOKEN, this);

        long maxDuration = 20000L;
        ziggeo.setSendImmediately(false);
        ziggeo.setTurnOffCameraWhileUploading(true);
        Map<String, String> args = new HashMap<>();
        args.put("key", "key_for_video");
        args.put("tags", "tag_for_video1, tag_for_video2");
        ziggeo.setExtraArgsForRecorder(args);
        ziggeo.setMaxRecordingDuration(maxDuration);
        ziggeo.setNetworkRequestsCallback(this);
        ziggeo.setVideoRecordingProcessCallback(new VideoRecordingCallback() {
            @Override
            public void onStarted() {
                Log.d(TAG, "onStarted");
            }

            @Override
            public void onStopped(@NonNull String path) {
                Log.d(TAG, "onStopped:" + path);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                super.onError(throwable);
                Log.d(TAG, "onError:" + throwable.toString());
            }
        });
        ziggeo.startRecorder();
    }

    @Override
    public void onProgressUpdate(long sent, long total) {
        int newProgr = (int) ((float) sent / total * 100);
        if (newProgr > progressPercent) {
            progressPercent = newProgr;
            Log.d(TAG, "Sent " + sent + "/" + total + "(" + progressPercent + "%)");
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "Request failure. Exception:" + e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful() && response.body() != null) {
            try {
                String responseString = response.body().string();
                response.close();
                Log.d(TAG, "Request success:" + responseString);

//              do something here
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        } else {
            ResponseException exception = new ResponseException(
                    response.code(), response.message()
            );
            onFailure(call, exception);
        }
    }

    private void startVideoRecordingWithDelay() {
        // starting preview with delay
        final long previewDelay = 3000L; // 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final long autostartRecording = 5000L; // after 5 seconds
                ziggeo.setAutostartRecordingAfter(autostartRecording);
                ziggeo.startRecorder();
            }
        }, previewDelay);
    }

    /**
     * Simple video file uploading.
     */
    private void createVideoApiCallExample(Callback callback) {
        String videoPath = "";
        File file = new File(videoPath);
        if (file.exists()) {
            HashMap<String, String> argsMap = new HashMap<>();
            argsMap.put("tags", "tag1,tag2");
            argsMap.put("key", "The best video ever");

            ziggeo.videos().create(file, argsMap, callback);
        } else {
            Log.e(TAG, "Video file doesn't exists");
        }
    }

    /**
     * Will update video data, not video file itself.
     * To update video file you need to delete old video and create new one.
     */
    private void updateVideoApiCallExample(String videoToken, Callback callback) {
        HashMap<String, String> argsMap = new HashMap<>();
        argsMap.put("tags", "newTag");

        ziggeo.videos().update(videoToken, argsMap, callback);
    }

    /**
     * Will delete selected video.
     * Make sure your application was allowed to do delete.
     */
    private void deleteVideoApiCallExample(String videoToken, Callback callback) {
        ziggeo.videos().destroy(videoToken, callback);
    }

    /**
     * Get list with video
     * Make sure your application was allowed to do index
     */
    private void index(Callback callback) {
        HashMap<String, String> args = new HashMap<>();
        args.put("tags", "your_tags_here");
        ziggeo.videos().index(args, callback);
    }

    /**
     * Here is example how we can combine video and stream services
     * and use this combination for upload custom preview image for video.
     * <p/>
     * How we will do it
     * 1. Create video object
     * 2. Create stream for it
     * 3. Attach image to stream
     * 4. Attach video to stream
     * 5. Bind the stream.
     */
    private File videoFile;
    private File snapshotFile;
    private String videoToken;
    private String streamToken;

    private void uploadVideoWithCustomSnapshotExample() {
        String videoFilePath = "";
        String snapshotImagePath = "";
        videoFile = new File(videoFilePath);
        snapshotFile = new File(snapshotImagePath);
        // make sure files exists
        if (videoFile.exists() && snapshotFile.exists()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("tags", "t1,t2");
            args.put("key", "test_key");

            ziggeo.videos().create(args, createVideoCallback);
        } else {
            Log.e(TAG, "Video or snapshot file not found");
        }
    }

    private Callback createVideoCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Create video object failure. Exception:" + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Log.d(TAG, "Create video object success:" + responseString);

                    final JSONObject responseJson = new JSONObject(responseString);
                    videoToken = responseJson.getString("token");

                    ziggeo.streams().create(videoToken, createStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private Callback createStreamCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Create stream failure. Exception:" + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Log.d(TAG, "Create stream success:" + responseString);

                    JSONObject responseJson = new JSONObject(responseString);
                    streamToken = responseJson.getString("token");

                    ziggeo.streams().attachImage(videoToken, streamToken, snapshotFile,
                            attachImageToStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private Callback attachImageToStreamCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Attach image failure. Exception:" + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Log.d(TAG, "Attach image success:" + responseString);

                    ziggeo.streams().attachVideo(videoToken, streamToken, videoFile,
                            attachVideoToStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }

    };

    private Callback attachVideoToStreamCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Attach video failure. Exception:" + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Log.d(TAG, "Attach video success:" + responseString);

                    ziggeo.streams().bind(videoToken, streamToken, bindStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }

    };

    private Callback bindStreamCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Bind stream failure. Exception:" + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Log.d(TAG, "Bind stream success:" + responseString);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

}
