# Android-SDK
## Please, use latest build tools and compile sdk version.

### Download:
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
        compile 'com.github.ZiggeoJitpackService:Android-SDK:0.67.1'
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

## Configure:
```
/**
  * Set the maximum duration of the video.
  * If the value is 0, the recording will be endless.
  * <p>
  * Default value is 0
  *
  * @param duration - duration in millis
  */
ziggeo.setMaxRecordingDuration(maxVideoDuration);

/**
  * Set the time after what the recording will be automatically started.
  * If the value is 0, the recording will be started immediately.
  * If the value is -1, the feature will be turned off. 
  * <p>  
  * Default value is -1
  *
  * @param millis - delay to autostart
  */
ziggeo.setAutostartRecordingAfter(autoStartAfterInMillis);

/**
  * Set the folder where videos will be stored after recording.
  * Default path is Environment.getExternalStorageDirectory() + "/Ziggeo"
  *
  * @param cacheFolderPath - path to the folder
  */
ziggeo.setCacheFolder(path);

/**
  * Register a callback to be invoked when a recording is started, stopped or an error occupied.
  *
  * @param callback - the callback
  */
ziggeo.setVideoRecordingProcessCallback(callback);

/**
  * Register a callback to be invoked when request finished with either an HTTP response or a
  * failure exception.
  *
  * @param callback - the callback
  */
ziggeo.setNetworkRequestsCallback(@callback);

/**
  * If true will hide the preview when uploading is started and show the preview after
  * uploading will be finished successfully or with the error.
  * The preview will also be shown if user will start a new recording.
  * <p>
  * Default value is false
  * <p>
  * Does not release the Camera.
  *
  * @param turnOffCameraWhileUploading
  */
ziggeo.setTurnOffCameraWhileUploading(turnOffCameraWhileUploading);

/**
  * Set the color to be shown as a background when camera preview is stopped.
  * <p>
  * Default color is @android:color/black
  *
  * @param colorForStoppedCameraOverlay - the color
  */
ziggeo.setColorForStoppedCameraOverlay(colorForStoppedCameraOverlay);

/**
  * Set if a dialog for selecting a cover shot should be shown after the recording is stopped.
  * <p>
  * Default value is true
  *
  * @param enabled
  */
ziggeo.setCoverSelectorEnabled(enabled);

 /**
  * Set the maximum duration of a video.
  * 0 is for endless recording
  * <p>
  * Default value is 0
  *
  * @param duration - duration in millis
  */
ziggeo.setMaxRecordingDuration(duration);

/**
  * Set what camera facing to use by default.
  * <p>
  * Default value is CameraView.FACING_BACK
  *
  * @param facing - back or front facing
  */
ziggeo.setPreferredCameraFacing(facing);

/**
  * Set what quality to use for recording.
  * <p>
  * Default value is CameraView.QUALITY_HIGH
  *
  * @param videoQuality - the quality
  */
ziggeo.setPreferredQuality(videoQuality);

/**
  * If true disable switching camera from back to front and vice versa.
  * <p>
  * Default value is false.
  */
ziggeo.setCameraSwitchDisabled(enabled);

/**
  * Set the recorder to send a video right after it was recorded.
  * <p>
  * Default value is true.
  */
ziggeo.setSendImmediately(boolean sendImmediately);

/**
  * Set extra arguments for create video request.
  *
  * @param extraArgs - map of args which will be sent with create video request
  */
ziggeo.setExtraArgsForCreateVideo(extraArgs);

/**
  * Set extra arguments for stream video player.
  *
  * @param extraArgs
  */
ziggeo.setExtraArgsForPlayVideo(@Nullable Map<String, String> extraArgs);
    
/**
  * Cancel a network request which is in execution right now.
  * Can be used only with native recorder.
  */
ziggeo.cancelRequest();

/**
  * Configure a dialog to confirm recording stop.
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

## Fullscreen video recorder:
```
/**
  * Launch standalone activity with video recorder and player.
  */
ziggeo.startRecorder();
```
## Embedded video recorder:
```
/**
  * Embed the recorder.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  */
ziggeo.attachRecorder(@NonNull FragmentManager fragmentManager, int contentId);
```

## Fullscreen video player:
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
## Embedded video player:
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

## Ziggeo API access
##### Videos api
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

##### Streams api

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

##### Requests cancellation
Every service call returns `Call` object, you can cancel request execution, by calling ```cancel``` method. 
For example
```
Call call = mVideoService.create(...);
call.cancel();
```
Also you can cancel requests for embedded recorder
`mZiggeo.cancel()`
