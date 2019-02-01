package com.ziggeo.androidsdk.demo.api;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ziggeo.androidsdk.Ziggeo;
import com.ziggeo.androidsdk.net.exceptions.ResponseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import timber.log.Timber;

/**
 * Created by Alex Bedulin on 18-Apr-18.
 */
public class VideosApiSamples {

    private static final String TAG = VideosApiSamples.class.getSimpleName();

    private Ziggeo ziggeo;

    public VideosApiSamples(@NonNull String applicationToken, @NonNull Context context) {
        ziggeo = new Ziggeo(applicationToken, context);
    }

    public void downloadImageAndSaveToFile(@NonNull String videoTokenOrKey) {
        ziggeo.videos().downloadImage(videoTokenOrKey, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Error during image download:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                final ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    final File imageFile = new File(Environment.getExternalStorageDirectory() + "/Download/image.png");
                    saveByteStreamToFile(body.byteStream(), imageFile);
                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }

                Util.closeQuietly(response);
            }
        });
    }

    public void downloadVideoAndSaveToFile(@NonNull String videoTokenOrKey) {
        ziggeo.videos().downloadVideo(videoTokenOrKey, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Error during image download:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.e(TAG, "onResponse");
                final ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    final File videoFile = new File(Environment.getExternalStorageDirectory() + "/Download/video.mp4");
                    saveByteStreamToFile(body.byteStream(), videoFile);
                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }

                Util.closeQuietly(response);
            }
        });
    }

    public void createVideo(Callback callback) {
        String videoPath = "";
        File file = new File(videoPath);
        if (file.exists()) {
            HashMap<String, String> argsMap = new HashMap<>();
            argsMap.put("tags", "tag1,tag2");
            argsMap.put("key", "The best video ever");

            ziggeo.videos().create(file, argsMap, callback);
        } else {
            Timber.e("Video file doesn't exists");
        }
    }

    /**
     * Will update video data, not video file itself.
     * To update video file you need to delete old video and start new one.
     */
    private void updateVideo(String videoToken, Callback callback) {
        HashMap<String, String> argsMap = new HashMap<>();
        argsMap.put("tags", "newTag");

        ziggeo.videos().update(videoToken, argsMap, callback);
    }

    /**
     * Will delete selected video.
     * Make sure your application was allowed to do delete.
     */
    private void deleteVideo(String videoToken, Callback callback) {
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
            Timber.e("Video or snapshot file not found");
        }
    }

    private Callback createVideoCallback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            Timber.e("Create video object failure. Exception:%s", e.toString());
        }

        @Override
        public void onResponse(@NonNull Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Timber.d("Create video object success:%s", responseString);

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
                Timber.e(e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private Callback createStreamCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Timber.e("Create stream failure. Exception:%s", e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Timber.d("Create stream success:%s", responseString);

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
                Timber.e(e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private Callback attachImageToStreamCallback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            Timber.e("Attach image failure. Exception:%s", e.toString());
        }

        @Override
        public void onResponse(@NonNull Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Timber.d("Attach image success:%s", responseString);

                    ziggeo.streams().attachVideo(videoToken, streamToken, videoFile,
                            attachVideoToStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Timber.e(e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }

    };

    private Callback attachVideoToStreamCallback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            Timber.e("Attach video failure. Exception:%s", e.toString());
        }

        @Override
        public void onResponse(@NonNull Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Timber.d("Attach video success:%s", responseString);

                    ziggeo.streams().bind(videoToken, streamToken, bindStreamCallback);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Timber.e(e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private Callback bindStreamCallback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            Timber.e("Bind stream failure. Exception:%s", e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.isSuccessful() && response.body() != null) {
                    String responseString = response.body().string();
                    Timber.d("Bind stream success:%s", responseString);

                } else {
                    ResponseException exception = new ResponseException(
                            response.code(), response.message()
                    );
                    onFailure(call, exception);
                }
            } catch (IOException e) {
                Timber.e(e.toString());
            } finally {
                Util.closeQuietly(response);
            }
        }
    };

    private void saveByteStreamToFile(@NonNull InputStream is, @NonNull File fileSaveTo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileSaveTo);
            byte[] buffer = new byte[1024];
            while (true) {
                int readed = is.read(buffer);

                if (readed == -1) {
                    break;
                }
                fos.write(buffer, 0, readed);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            Util.closeQuietly(fos);
            Util.closeQuietly(is);
        }
    }

}
