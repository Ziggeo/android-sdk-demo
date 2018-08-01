# Android-SDK
### Upgrading from `v.0.73.12` to `v.0.74.0`
New version contains broken changes.
 * API 21+: Added screen recorder feature.
 * Fixed vector icon causing crash on android below API 21.
 * `com.ziggeo.androidsdk.net.rest.ProgressCallback` moved to `com.ziggeo.androidsdk.net.callbacks.ProgressCallback`
 * Class `ResponseException` renamed to `ResponseException`
 * In class `ResponseException` methods `getResponse` and `setResponse` are deprecated. Use `getMessage` and `setMessage` instead.
 * Classes `IStreamsService`, `StreamsService`,  `IVideosService`,  `VideosService` moved from `com.ziggeo.androidsdk.net.rest.services.` to `com.ziggeo.androidsdk.net.services.`

## Please, use latest build tools and compile sdk version.

### Preparation
**Step 1.** Add the JitPack repository in your root `build.gradle`
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency in your application related dependencies
```
dependencies {
        compile 'com.github.ZiggeoJitpackService:Android-SDK:$version'
}
```
## Initialize
```
/**
  * @param appToken - Ziggeo application token
  * @param context - Application context
  */
Ziggeo ziggeo = new Ziggeo(appToken, context);
```

## Configure
```
/**
  * For recorder.
  * <p>
  * Set the maximum duration of the video. `0` for endless recording.
  * Also used in elapsed time indicator in the top-right corner.
  * <p>
  * Default value is `0`
  *
  * @param duration - duration in millis
  */
ziggeo.setMaxRecordingDuration(maxVideoDuration);

/**
  * For recorder.
  * <p>
  * Set the time after what the recording will be automatically started
  * `0` value will start immediately
  * `-1` value will turn off this feature
  * Default value is `-1`
  *
  * @param millis - delay to autostart
  */
ziggeo.setAutostartRecordingAfter(autoStartAfterInMillis);

/**
  * For recorder.
  * <p>
  * Register a callback to be invoked when a recording is started, stopped or an error occupied.
  *
  * @param callback - the callback
  */
ziggeo.setVideoRecordingProcessCallback(callback);

/**
  * For recorder.
  * <p>
  * Register a callback to be invoked when request finished with either an HTTP response or a
  * failure exception.
  *
  * @param callback - the callback
  */
ziggeo.setNetworkRequestsCallback(@callback);

/**
  * For recorder.
  * <p>
  * If `true` will hide the preview when uploading is started and show the preview after
  * uploading is finished (successfully or with the error).
  * The preview will also be shown if user will start a new recording.
  * <p>
  * Default value is `false`
  * <p>
  * Does not release the `Camera`.
  *
  * @param turnOffCameraWhileUploading
  */
ziggeo.setTurnOffCameraWhileUploading(turnOffCameraWhileUploading);

/**
  * For recorder.
  * <p>
  * Set the color to be shown as a background when camera preview is stopped.
  * Default color is `@android:color/black`
  *
  * @param colorForStoppedCameraOverlay - the color
  */
ziggeo.setColorForStoppedCameraOverlay(colorForStoppedCameraOverlay);

/**
  * For recorder.
  * <p>
  * Set the drawable to be shown as a background when camera preview is stopped.
  * Default color is `@android:color/black`
  *
  * @param drawableResource - the drawable resource
  */
void setDrawableForStoppedCameraOverlay(@DrawableRes int drawableResource);

/**
  * For recorder.
  * <p>
  * Set the maximum duration of the video. `0` for endless recording.
  * Also used in elapsed time indicator in the top-right corner.
  * <p>
  * Default value is `0`
  *
  * @param duration - duration in millis
  */
ziggeo.setMaxRecordingDuration(duration);

/**
  * For recorder.
  * <p>
  * Set what camera facing to use by default.
  * Default value is {@link CameraView.FACING_BACK}
  *
  * @param facing - back or front facing
  */
ziggeo.setPreferredCameraFacing(facing);

/**
  * For recorder.
  * <p>
  * Set what quality to use for recording.
  * Default value is {@link CameraView.QUALITY_HIGH}
  *
  * @param videoQuality - the quality
  */
ziggeo.setPreferredQuality(videoQuality);

/**
  * For recorder.
  * <p>
  * If true the button for switching will not be shown.
  * Default value is `false`.
  */
ziggeo.setCameraSwitchDisabled(enabled);

/**
  * For recorder.
  * <p>
  * If true the video will be sent right after it was recorded.
  * Default value is `true`.
  */
ziggeo.setSendImmediately(boolean sendImmediately);

/**
  * For recorder.
  * <p>
  * Set extra arguments for create video request when creating video using embedded recorder.
  *
  * @param extraArgs - args will be sent with create video request
  */
ziggeo.setExtraArgsForRecorder(extraArgs);

/**
  * For player.
  * <p>
  * Set extra arguments for stream video player.
  *
  * @param extraArgs
  */
ziggeo.setExtraArgsForPlayer(@Nullable Map<String, String> extraArgs);
    
/**
  * For recorder.
  * <p>
  * Cancel the latest network request which is in execution right now.
  * For example, if two videos were recorder and both uploading right now the last one will be cancelled.
  */
ziggeo.cancelRequest();

/**
  * For recorder.
  * <p>
  * Configure a dialog to confirm recording stop.
  * The dialog will be shown for both cases: either user press `stop` button or `sendAndClose` checkmark.
  *
  * @param show        - if the dialog will be shown
  * @param titleResId  - the title
  * @param mesResId    - the message
  * @param posBtnResId - the text for positive button
  * @param negBtnResId - the text for negative button
  */
ziggeo.initStopRecordingConfirmationDialog(boolean show, @StringRes int titleResId, @StringRes int mesResId,
                                             @StringRes int posBtnResId, @StringRes int negBtnResId);
```

## Fullscreen Video Recorder
```
/**
  * Launch standalone activity with video recorder and player.
  */
ziggeo.startRecorder();
```

## Embedded Video Recorder
```
/**
  * Embed the recorder.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  */
ziggeo.attachRecorder(@NonNull FragmentManager fragmentManager, int contentId);
```

## Fullscreen Video Player
```
/**
  * Launch standalone activity with the player to play the file from uri.
  *
  * @param path - {@link Uri} path to file.
  */
ziggeo.startPlayer(@NonNull Uri path);

/**
  * Launch standalone activity with the player to play the file from stream.
  *
  * @param videoToken - video token.
  */
ziggeo.startPlayer(@NonNull String videoToken);
```

## Embedded Video Player
```
/**
  * Embed the player to play the file from uri.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param path            - {@link Uri} path to file.
  */
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, Uri path);

/**
  * Embed the player to play the file from stream.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param videoToken      - video token.
  */
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, String videoToken);
```

## Ziggeo API Access

##### Videos API
```
/**
  * Delete a single video by token or key.
  *
  * @param keyOrToken - video token or key.
  *                   If you're using key make sure to add "_" prefix.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().destroy(String keyOrToken, Callback callback);

/**
  * Query an array of videos (will return at most 50 videos by default). Newest videos come first.
  *
  * @param argsMap  - limit: Limit the number of returned videos. Can be set up to 100.
  *                 - skip: Skip the first [n] entries.
  *                 - reverse: Reverse the order in which videos are returned.
  *                 - states: Filter videos by state
  *                 - tags: Filter the search result to certain tags
  * @param callback - - callback to receive action result
  */
ziggeo.videos().index(HashMap<String, String> argsMap, Callback callback);

/**
  * Get a single video by token or key.
  *
  * @param keyOrToken - video token or key.
  *                   If you're using key make sure to add "_" prefix.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().get(String keyOrToken, Callback callback);

/**
  * Download the video data file.
  *
  * @param keyOrToken - video token or key.
  *                   If you're using key make sure to add "_" prefix.
  * @param callback   - callback to receive action result
  */
  ziggeo.videos().downloadVideo(@NonNull String keyOrToken, @NonNull Callback callback);

 /**
  * Download the video data file.
  *
  * @param keyOrToken - video token or key.
  *                   If you're using key make sure to add "_" prefix.
  * @param callback   - callback to receive action result
  */
   ziggeo.videos().downloadImage(@NonNull String keyOrToken, @NonNull Callback callback);

/**
  * Update single video by token or key.
  *
  * @param keyOrToken - video token or key.
  *                   If you're using key make sure to add "_" prefix.
  * @param argsMap    - min_duration: Minimal duration of video
  *                   - max_duration: Maximal duration of video
  *                   - tags: Video Tags
  *                   - key: Unique (optional) name of video
  *                   - volatile: Automatically removed this video if it remains empty
  *                   - expiration_days: After how many days will this video be deleted
  * @param callback   - callback to receive action result
  */
ziggeo.videos().update(String keyOrToken, HashMap<String, String> argsMap, Callback callback);

/**
  * Create a new video.
  *
  * @param argsMap  - file: Video file to be uploaded
  *                 - min_duration: Minimal duration of video
  *                 - max_duration: Maximal duration of video
  *                 - tags: Video Tags
  *                 - key: Unique (optional) name of video
  *                 - volatile: Automatically removed this video if it remains empty
  * @param callback - callback to receive action result
  */
ziggeo.videos().create(HashMap<String, String> argsMap, Callback callback);
```

##### Streams API
```
/**
  * Create a new stream
  *
  * @param videoTokenOrKey - video for which stream will be created
  * @param callback        - callback to receive action result
  */
ziggeo.streams().create(String videoTokenOrKey, Callback callback);

/**
  * Create a new stream
  *
  * @param videoTokenOrKey - video for which stream will be created
  * @param videoFile       - Video file to be uploaded
  * @param callback        - callback to receive action result
  */
 ziggeo.streams().create(@NonNull String videoTokenOrKey, @NonNull File videoFile, @Nullable HashMap<String, String> argsMap, @NonNull Callback callback);

/**
  * Attaches an image to a new stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param imageFile        - file to attach
  * @param callback         - callback to receive action result or handler progress
  */
ziggeo.streams().attachImage(videoTokenOrKey, String streamTokenOrKey, File imageFile, Callback callback);

/**
  * Attaches a video to a new stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param videoFile        - file to attach
  * @param callback         - callback to receive action result or handle progress
  */
ziggeo.streams().attachVideo(String videoTokenOrKey, String streamTokenOrKey, File videoFile, Callback callback);

/**
  * Closes and submits the stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param callback         - callback to receive action result
  */
ziggeo.streams().bind(String videoTokenOrKey, String streamTokenOrKey, Callback callback);

 /**
  * Download the video data associated with the stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param callback         - callback to receive action result
  */
 ziggeo.streams().downloadVideo(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);

 /**
  * Download the image data associated with the stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param callback         - callback to receive action result
  */
 ziggeo.streams().downloadImage(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);


/**
  * Get a single stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param callback         - callback to receive action result
  */
ziggeo.streams().get(String videoTokenOrKey, String streamTokenOrKey, Callback callback);

/**
  * Delete the stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param callback         - callback to receive action result
  */
ziggeo.streams().delete(String videoTokenOrKey, String streamTokenOrKey, Callback callback);
```

##### Request Cancellation
Every service call returns a `Call` object, allowing you to cancel the request execution, by invoking the ```cancel``` method. 
For example:
```
Call call = mVideoService.create(...);
call.cancel();
```
Note: you can also cancel the last active request for recorder using
`mZiggeo.cancel()`

#### Authentication
```
/**
  * Sets the client auth token.
  *
  * @param token - token which will be used for authentication in requests
  */
ziggeo.setClientAuthToken(@NonNull String token);

/**
  * Sets the server auth token.
  *
  * @param token - token which will be used for authentication in requests
  */
ziggeo.setServerAuthToken(@NonNull String token);
```

## Screen recording (available on API 21+)
```
/**
  * Start foreground service for screen recording
  */
ziggeo.startScreenCaptureService(@Nullable ScreenRecordServiceNotificationConfig config);

/**
  * Stop foreground service for screen recording
  */
ziggeo.stopScreenCaptureService();

/**
  * Start foreground service for screen recording and screen recording
  */
ziggeo.startScreenCapture(@Nullable ScreenRecordServiceNotificationConfig config);

/**
  * Stop screen recording if running
  */
ziggeo.stopScreenCapture();
```

## Proguard config
See `app\proguard-rules.pro`
