# Android-SDK
### Upgrading from `v.0.77.0` to `0.78.0`
##### Broken changes
 - Java8 support added to the SDK. Please, add the following code in `app/build.gradle` in `android` section
 ```
compileOptions {
         sourceCompatibility JavaVersion.VERSION_1_8
         targetCompatibility JavaVersion.VERSION_1_8
}
```
 - method `startRecorder(boolean audioOnly)` is deprecated. Please, use `startAudioRecorder()`.
 - method `startRecorder()` is deprecated. Please, use `startCameraRecorder()`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId)` is deprecated. Please, use `attachCameraRecorder(FragmentManager fragmentManager, int contentId)`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId, boolean audioOnly)` is deprecated. Please, use `attachAudioRecorder(FragmentManager fragmentManager, int contentId)`.
 - methods `setExtraArgsForRecorder`, `getExtraArgsForRecorder`, `configureStopRecordingConfirmationDialog`, `getStopRecordingConfirmationDialogConfig`, `setAutostartRecordingAfter`, `setAutostartRecordingAfter`, `getAutostartRecordingAfter`, `setVideoRecordingProcessCallback`, `setRecordingProcessCallback`, `getVideoRecordingProcessCallback`, `getRecordingProcessCallback`,
 `setTurnOffCameraWhileUploading`, `setColorForStoppedCameraOverlay`, `setDrawableForStoppedCameraOverlay`, `setCoverSelectorEnabled`, `setCoverSelectorEnabled`, `setMaxRecordingDuration`, `setPreferredCameraFacing`, `setPreferredQuality`, `getPreferredQuality`, `setCameraSwitchDisabled`, `setSendImmediately`, `isSendImmediately` are deprecated. Please, use methods in `CameraRecorderConfig.Builder` class.
 - methods `configureLocalPlayback`, `getLocalPlaybackConfig`, `setExtraArgsForPlayer` are deprecated. Please, use corresponding
 - method `addCallback` in `CameraView` class is deprecated. Please, use `setCameraCallback` and `


##### New features
 - Changes in autostart logic.  
   Method `setAutostartRecordingAfter` is deprecated, instead there are now two methods  
    - `setAutostartRecording` - if `true`, when the recorder is initialized, the count down timer will be started.  
   After the time will run out the recording will be started.  
   Default if `false`.  
    - `setStartDelay` - set the value for count down timer before start the recording.  
   Default is `3` seconds.

 - Foreground service for recordings uploading.  
 During the uploading notifications will be visible in notification bar. In case of error there are two options available:  
   - `Retry` - just make another try to upload
   - `Try later` - the SDK will try to make uploading after the delay. Default delay is 2 hours.  
 Also it is possible to do uploading only when WiFi is available. See sample below:
 ```
ziggeo.configureUploading(new UploadingConfig.Builder()
                .syncInterval(15 * 60 * 1000)
                .useWifiOnly(true)
                .build());
 ```
 - Face outline image.  
 ```
 FaceOutlineConfig config = new FaceOutlineConfig();
 config.setShow(true);
 config.setOutlineImageResource(R.drawable.youImage); // optional
// put created object in RecorderConfig class
```
 - New file selector with multiple selection ability.
 - `ziggeo.clearRecorder` - the method that allows the devs to clear the cache through code. Would remove the videos even if they are not uploaded.
 - Playlist for the player. `ziggo.startPlayer(token1, token2, ...)`
 - Player theming.  
   There is now ability to style the player. You can use one of the predefined themes and change colors for them. Styling available through styles.xml and through code. If both way used, only params in code will be handled.  
   Sample can be found in `ZiggeoPlayerActivity`.
 - New callback system for player, recorder and camera
 - Subtitles for video player
 `playerConfig.showSubtitles(boolean value)`

Fixes/Small changes
 - New method `getNumberOfCameras()` in `CameraView` class.
 - fixed camera issue on Samsung Galaxy J7
 - date pattern in file names changed from `dd-MM-yyyy_hh-mm-ss` to `dd.MM.yyyy_HH.mm.ss`

### Please, use latest build tools and compile sdk version.

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

## Configure the recorder
```
RecorderConfig.Builder configBuilder = new RecorderConfig.Builder();
/**
  * Set the maximum duration of the video. `0` for endless recording.
  * Also used in elapsed time indicator in the top-right corner.
  * <p>
  * Default value is `0`
  *
  * @param duration - duration in millis
  */
configBuilder.maxDuration(long maxDuration);

/**
 * Set the delay before the recording will be started
 * Default value is `3` seconds
 *
 * @param startDelay - delay
 */
configBuilder.startDelay(int startDelay);

/**
 * Configure if the recording should be started 
 * after initialisation of the recorder.
 */
configBuilder.autostartRecording(boolean autostart);

/**
  * Register a callback to be invoked when a recording 
  * is started, stopped, an error occurred, etc.
  *
  * @param callback - the callback
  */
configBuilder.callback(callback);

/**
  * Set what camera facing to use by default.
  * Default value is {@link CameraView.FACING_BACK}
  *
  * @param facing - back or front facing
  */
configBuilder.facing(@CameraView.Facing int facing);

/**
  * Set what quality to use for recording.
  * Default value is {@link CameraView.QUALITY_HIGH}
  *
  * @param videoQuality - the quality
  */
configBuilder.quality(@CameraView.Quality int videoQuality);

/**
  * If true the button for switching will not be shown.
  * Default value is `false`.
  */
configBuilder.disableCameraSwitch(boolean disable);

/**
  * If true the video will be sent right after it was recorded.
  * Default value is `true`.
  */
configBuilder.sendImmediately(boolean send);

/**
  * Set extra arguments for create video request.
  *
  * @param extraArgs - args will be sent with create video request
  */
configBuilder.extraArgs(@Nullable HashMap<String, String> extraArgs);

/**
  * Configure a dialog to confirm recording stop.
  * The dialog will be shown for both cases: either user press `stop` button or `sendAndClose` checkmark.
  *
  * @param show        - if the dialog will be shown
  * @param titleResId  - the title
  * @param mesResId    - the message
  * @param posBtnResId - the text for positive button
  * @param negBtnResId - the text for negative button
  */
configBuilder.confirmStopRecording(boolean confirmStopRecording);
configBuilder.initStopRecordingConfirmationDialog(@Nullable StopRecordingConfirmationDialogConfig config);
```

## Fullscreen Camera Recorder
```
/**
  * Launch standalone activity with camera recorder and player.
  */
ziggeo.startCameraRecorder();
```

## Embedded Video Recorder
```
/**
  * Embed the recorder.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  */
ziggeo.attachCameraRecorder(@NonNull FragmentManager fragmentManager, int contentId);
```
## 
## Fullscreen Video Player
```
/**
  * Launch standalone activity with the player to play the file from uri.
  *
  * @param path - {@link Uri} path to file.
  */
ziggeo.startPlayer(@NonNull Uri... path);

/**
  * Launch standalone activity with the player to play the file from stream.
  *
  * @param videoToken - video token.
  */
ziggeo.startPlayer(@NonNull String... videoToken);
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
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, Uri... path);

/**
  * Embed the player to play the file from stream.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param videoToken      - video token.
  */
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, String... videoToken);
```

## Callbacks
##### Common:
```
/**
 * Called in case of any error occurred
 *
 * @param throwable
 */
void error(@NonNull Throwable throwable);

/**
 * Triggered when the player / recorder is loaded for the first time
 */
void loaded();
```
##### Recorder
```
/**
 * Video has been recorded and is available for preview and redo option and this shows a button
 * that just raises this event, does not do anything on our end,
 * however the button is basically there to confirm the submission of that video.
 * <p>
 * This is a requirement in some countries.
 * <p>
 * Will be fired in case of sendImmediately = false.
 */
void manuallySubmitted();

/**
 * Triggered when a recording process has started
 * (Press on the Record button if update 0 or after the update goes to 0)
 */
void recordingStarted();

/**
 * Triggered when a recording process has stopped
 * (Press on the Stop button or duration/size limit set)
 */
void recordingStopped(@NonNull String path);

/**
 * Triggered while a video recorder counts down (happens before recordingStarted)
 *
 * @param timeLeft - seconds before start, e.g. 10, 9, 8, 7, ...
 */
void countdown(int timeLeft);

/**
 * Continuous update notification during the recording process.
 * Fires every second.
 *
 * @param time - parameter stating how much time has passed since the event has started.
 */
void recordingProgress(long time);

/**
 * Gets triggered as soon as the recording can be made.
 * (Camera is ready and permissions are granted)
 */
void readyToRecord();

/**
 * Triggered when access to camera, mic or file storage is not granted.
 *
 * @param permissions - list of not granted permissions
 */
void accessForbidden(@NonNull List<String> permissions);

/**
 * Gets triggered when someone gives OK for our system to use camera, microphone and file storage.
 */
void accessGranted();
    
/**
 * Called when no cameras found.
 * It checks the {@link android.content.pm.PackageManager.FEATURE_CAMERA}
 * and the number of available cameras.
 */
void noCamera();

/**
 * Called when no microphone found.
 * It checks the {@link android.content.pm.PackageManager.FEATURE_MICROPHONE}
 */
void noMicrophone();

/**
 * Called when at least one camera is accessible
 */
void hasCamera();

/**
 * Called when at microphone is accessible
 */
void hasMicrophone();

/**
 * Called when the user cancelled the recording and closed the screen
 */
void canceledByUser();

/**
 * Fires after upload has finished.
 */
void uploaded(@NonNull String path, @NonNull String token);

/**
 * Triggered when a video uploadingStarted has started
 */
void uploadingStarted(@NonNull String path);

/**
 * Continuous updates on the upload progress.
 */
void uploadProgress(@NonNull String videoToken, @NonNull File file, long uploaded, long total);

/**
 * Continuous update notifications while processing the video.
 */
void processing(@NonNull String token);

/**
 * Video successfully processed.
 */
void processed(@NonNull String token);

/**
 * Triggered after video is uploaded and verified that it can be processed.
 */
void verified(@NonNull String token);

/**
 * Called every second and checks recorder's amplitude.
 *
 * @param level - GOOD, MODERATE or BAD value
 */
void microphoneHealth(@NonNull MicSoundLevel level);
```

##### Player:
```
/**
 * Fires any time a playback is started
 */
void playing();

/**
 * Fires when the pause button is clicked (and at the end of the video)
 */
void paused();

/**
 * Fires when a video playback has ended (reaches the end)
 */
void ended();

/**
 * Triggered when the user moves the progress indicator to continue video playback from a different position
 */
void seek(long millis);

/**
 * Triggered when a video player is ready to play a video
 */
void readyToPlay();
```
##### Sensor
```
/**
 * Called every second.
 *
 * @param level - {@link Sensor.TYPE_LIGHT} value
 */
void lightSensorLevel(float level);
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
    ziggeo.startScreenRecordService(@Nullable ScreenRecordServiceNotificationConfig config);

/**
  * Stop foreground service for screen recording
  */
ziggeo.stopScreenCaptureRecord();

/**
  * Start foreground service for screen recording and screen recording
  */
ziggeo.startScreenRecord(@Nullable ScreenRecordServiceNotificationConfig config);

/**
  * Stop screen recording if running
  */
ziggeo.stopScreenRecord();
```

## Theming
 * There is an ability to style the player. You can use one of the predefined themes and change colors for them.  
 Styling available through `styles.xml` and through `PlayerStyle` class.  
 If both way used, only params in code will be handled.  
 Sample can be found in `ZiggeoPlayerActivity`.
 * Use `hideControls()` method in `PlayerStyle` and `CameraRecorderStyle` for making the control invisible.  
 Or `hidePlayerControls` and `hideRecorderControls` in `styles.xml`

## Proguard config
See `app\proguard-rules.pro`
