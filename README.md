# Android SDK

# Ziggeo's Android SDK

## Index

1. [Why Ziggeo's Android SDK?](#why-us)
2. [Prerequisites](#prerequisites)
    1. [Download](#download)
    2. [Dependencies](#dependencies)
    3. [Install](#install)
3. [Codes](#codes)
    1. [Init](#init)
    2. [Recorder](#recorder)
    3. [Player](#player)
    4. [Events / Callbacks](#events)
    5. [API](#API)
    6. [Examples](#examples)
4. [Update Information](#update)
5. [Changelog](#changelog)

## Why Ziggeo's Android SDK? <a name="why-us"></a>

Ziggeo is powerfull whitelabel video SAAS that helps people be part and lead the video revolution. It has award winning multimedia API and its CEO and CTO can often be seen talking in various events around the world.

Ziggeo's Android SDK is utilizing the API to bring you a native library you can add to your project. Just add to project and allow your application evolve into something much more, something video!

It offers you pre-built functionality to call and manipulate. This library also offers you direct access to the Ziggeo API as well if you want to do more things yourself.

### Who it is for?

Do you have a complex Android app that is missing that one key ingredient - the video?

Do you need a video library that can be seamlesly integrated into your existing app?

Want something that is simple and easy to use?

You need some powerful features high end video apps provide?

Want to provide a great video experience for your own customers?

If any of the above is "Yes" then you are in the right place as this SDK is for you!

## Prerequisites <a name="prerequisites"></a>

### Download <a name="download"></a>

You will want to either download the SDK zip file or to pull it in as git repository into your own project.

### Dependencies<a name="dependencies"></a>

Please use latest build tools and sdk version when compiling.

You also should use `Theme.AppCompat` or its descendants as an application theme.

For Proguard config please see `app\\proguard-rules.pro`

### Install<a name="install"></a>

**Step 1.** Add the JitPack repository in your root `build.gradle`

    allprojects {
        repositories {
            ...
            maven { url '<https://jitpack.io>' }
        }
    }

**Step 2.** Add the dependency in your application related dependencies

    dependencies {
            compile 'com.github.ZiggeoJitpackService:Android-SDK:$version'
    }

## Codes<a name="codes"></a>

This section will introduce you to the most common ways you would integrate our video library into your app.

### Init<a name="init"></a>

    /**
      * @param appToken - Ziggeo application token
      * @param context - Application context
      */
    Ziggeo ziggeo = new Ziggeo(appToken, context);

### Recorder<a name="recorder"></a>

Recorder can be added in 2 ways. As a fullscreen recorder and the embedded recorder.

The fullscreen recorder is useful when you want your recorder to take entire screen.

The embedded recordder is useful when you want your recorder to be part of your app. For example if you had an avatar in your app and you want it to be a quick video.

**Create fullscreen Video Recorder**

    /**
      * Launch standalone activity with camera recorder and player.
      */
    ziggeo.startCameraRecorder();

**Create embedded Video Recorder**

    /**
      * Embed the recorder.
      *
      * @param fragmentManager - {@link FragmentManager}
      * @param contentId       - Identifier of the container this fragment is to be placed in.
      */
    ziggeo.attachCameraRecorder(@NonNull FragmentManager fragmentManager, int contentId);

- See examples section to see how to configure the recorder with more specific options, instead of using defaults

### Screen Recorder

*Start Screen recording*

    /**
      * Start foreground service for screen recording and screen recording
      */
    ziggeo.startScreenRecorder(@Nullable ScreenRecordServiceNotificationConfig config);

### Player<a name="Player"></a>

Player can be used to play local videos, videos from other services and of course videos from Ziggeo servers.

Just like recorder, the player can too be implemeted as fullscreen or as embedded video player.

**Create fullscreen Video Player**

URI playback

    /**
      * Launch standalone activity with the player to play the file from uri.
      *
      * @param path - {@link Uri} One or more path to file.
      */
    ziggeo.startPlayer(@NonNull Uri... path);

Playback from Ziggeo servers

    /**
      * Launch standalone activity with the player to play the file from stream.
      *
      * @param videoToken - One or more video token.
      */
    ziggeo.startPlayer(@NonNull String... videoToken);

**Create embedded Video Player**

URI playback

    /**
      * Embed the player to play the file from uri.
      *
      * @param fragmentManager - {@link FragmentManager}
      * @param contentId       - Identifier of the container this fragment is to be placed in.
      * @param path            - {@link Uri} One or more path to file.
      */
    ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, Uri... path);

Playback from Ziggeo servers

    /**
      * Embed the player to play the file from stream.
      *
      * @param fragmentManager - {@link FragmentManager}
      * @param contentId       - Identifier of the container this fragment is to be placed in.
      * @param videoToken      - One or more video token.
      */
    ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, String... videoToken);

### Events / Callbacks<a name="events"></a>

Callbacks allow you to know when something happens. They fire in case of some event happening, such as if error occurs. This way you can design your app to fine detail and be able to provide you customers with great experience.

We have separated the events that are available to you into several different categories.

### Common

Common callbacks happen for both player and recorder. It usually does not depend on the embed method you have used, however each callback has additional details next to it.

Ups, something unexpected happened! Now its your time to react.

    /**
     * Called in case of any error occurred
     *
     * @param throwable
     */
    void error(@NonNull Throwable throwable);

The embedding (player, recorder) is loaded up for the very first time after it was created

    /**
     * Triggered when the player / recorder is loaded for the first time
     */
    void loaded();

### Recorder

The callbacks in this section are specific to recorder only. This means that they will not fire for the player at all.

Want to know when the recording can be made?

    /**
     * Gets triggered as soon as the recording can be made.
     * (Camera is ready and permissions are granted)
     */
    void readyToRecord();

Some permissions are not given, so we can not do much at this point

    /**
     * Triggered when access to camera, mic or file storage is not granted.
     *
     * @param permissions - list of not granted permissions
     */
    void accessForbidden(@NonNull List<String> permissions);

All permissions have been granted

    /**
     * Gets triggered when someone gives OK for our system to use camera, microphone and file storage.
     */
    void accessGranted();

Want to know when there is no camera?

    /**
     * Called when no cameras found.
     * It checks the {@link android.content.pm.PackageManager.FEATURE_CAMERA}
     * and the number of available cameras.
     */
    void noCamera();

Want to know when there is no mic?

    /**
     * Called when no microphone found.
     * It checks the {@link android.content.pm.PackageManager.FEATURE_MICROPHONE}
     */
    void noMicrophone();

Want to know when there are camera(s) available?

    /**
     * Called when at least one camera is accessible
     */
    void hasCamera();

Want to know when mic(s) are available?

    /**
     * Called when at microphone is accessible
     */
    void hasMicrophone();

Counting down before starting to record

    /**
     * Triggered while a video recorder counts down (happens before recordingStarted)
     *
     * @param timeLeft - seconds before start, e.g. 10, 9, 8, 7, ...
     */
    void countdown(int timeLeft);

Recording has just started

    /**
     * Triggered when a recording process has started
     */
    void recordingStarted();

Recording is in process and `time` tells you for how long

    /**
     * Continuous update notification during the recording process.
     * Fires every second.
     *
     * @param time - parameter stating how much time has passed since the event has started.
     */
    void recordingProgress(long time);

Want to detect if someone cancels the recording?

    /**
     * Called when the user cancelled the recording and closed the screen
     */
    void canceledByUser();

Recording has just finished

    /**
     * Triggered when a recording process has stopped
     * (Press on the Stop button or when set duration/size limit exhausted)
     */
    void recordingStopped(@NonNull String path);

Need to make sure someone confirms the video submission? Use this callback and record its action on your side as you like

    /**
     * Video has been recorded and is available for preview and redo option and this shows a button
     * that just raises this event, does not do anything on our end,
     * however the button is basically there to confirm the submission of that video.
     * 
     * This is a requirement in some countries.
     * 
     * Will be fired in case of sendImmediately = false.
     */
    void manuallySubmitted();

Want to know when upload starts?

    /**
     * Triggered when a video uploadingStarted has started
     */
    void uploadingStarted(@NonNull String path);

Want to know the progress of the uploads?

    /**
     * Continuous updates on the upload progress.
     */
    void uploadProgress(@NonNull String videoToken, @NonNull File file, long uploaded, long total);

Want to know once upload finishes?

    /**
     * Fires after upload has finished.
     */
    void uploaded(@NonNull String path, @NonNull String token);

Want to know if the video can be processed?

 - This utilizes our quick check algorithm to inspect the video before it is sent to processing and see that it can actually be processed. This allows you to react if something is wrong with the video
```
/**
 * Triggered after video is uploaded and verified that it can be processed.
 */
void verified(@NonNull String token);
```
Interested in the progress of the processing / transcoding of the video?

    /**
     * Continuous update notifications while processing the video.
     */
    void processing(@NonNull String token);

Interested knowing when the video is successfully processed?

    /**
     * Video successfully processed.
     */
    void processed(@NonNull String token);

### Player

Want to know once the player can play the video?

    /**
     * Triggered when a video player is ready to play a video
     */
    void readyToPlay();

Want to react when playback is started?

    /**
     * Fires any time a playback is started
     */
    void playing();

What to react when someone pause's the video?

    /**
     * Fires when the pause button is clicked (and at the end of the video)
     */
    void paused();

Want to know when the video playback ends?

    /**
     * Fires when a video playback has ended (reaches the end)
     */
    void ended();

Want to know if and where to someone changes the playback (seeks the video)?

    /**
     * Triggered when the user moves the progress indicator to continue video playback from a different position
     */
    void seek(long millis);

### Sensor

Want to know lightning conditions?

    ziggeo.setSensorCallback(@Nullable SensorManager.Callback callback);
    
    /**
     * Called every second.
     *
     * @param level - {@link Sensor.TYPE_LIGHT} value
     */
    void lightSensorLevel(float level);

Are you interested in knowing microphone health status?

    /**
     * Called every second and checks recorder's amplitude.
     *
     * @param level - GOOD, MODERATE or BAD value
     */
    void microphoneHealth(@NonNull MicSoundLevel level);

### API<a name="API"></a>

Our API (at this time) has 2 unique segments. The main one is videos. This deals with the video and videos as a whole.

Now as each video can have streams (sub videos) we also have an API that can deal with each stream as well.

For example, removing a video will remove all of its streams. On the other hand when removing a single stream, the rest of streams and the video itself will stay available. Of course, except that one stream.

### Videos API

*Index videos*

A way to find the videos based on your query and show them off. By default it is 50, however it can return back up to 100 videos at a time. Pagination is of course included.

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

*Get video info*

A way for you to get info about the specific video, without using index and looking it up there

    /**
      * Get a single video by token or key.
      *
      * @param keyOrToken - video token or key.
      *                   If you're using key make sure to add "_" prefix.
      * @param callback   - callback to receive action result
      */
    ziggeo.videos().get(String keyOrToken, Callback callback);

*Download video*

Want to save the video on your device? This is the way to get it.

    /**
      * Download the video data file.
      *
      * @param keyOrToken - video token or key.
      *                   If you're using key make sure to add "_" prefix.
      * @param callback   - callback to receive action result
      */
      ziggeo.videos().downloadVideo(@NonNull String keyOrToken, @NonNull Callback callback);

*Download image/snapshot*

    /**
      * Download the video data file.
      *
      * @param keyOrToken - video token or key.
      *                   If you're using key make sure to add "_" prefix.
      * @param callback   - callback to receive action result
      */
       ziggeo.videos().downloadImage(@NonNull String keyOrToken, @NonNull Callback callback);

*Create a video*

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

*Update a video*

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

*Delete the video*

    /**
      * Delete a single video by token or key.
      *
      * @param keyOrToken - video token or key.
      *                   If you're using key make sure to add "_" prefix.
      * @param callback   - callback to receive action result
      */
    ziggeo.videos().destroy(String keyOrToken, Callback callback);

### Streams API

*Create new stream*

It is possible to create the empty placeholder for the new data

    /**
      * Create a new stream
      *
      * @param videoTokenOrKey - video for which stream will be created
      * @param callback        - callback to receive action result
      */
    ziggeo.streams().create(String videoTokenOrKey, Callback callback);

It is also possible (and recommended) to create the video and upload the data right away

    /**
      * Create a new stream
      *
      * @param videoTokenOrKey - video for which stream will be created
      * @param videoFile       - Video file to be uploaded
      * @param callback        - callback to receive action result
      */
     ziggeo.streams().create(@NonNull String videoTokenOrKey, @NonNull File videoFile, @Nullable HashMap<String, String> argsMap, @NonNull Callback callback);

*Attach an image*

    /**
      * Attaches an image to a new stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param imageFile        - file to attach
      * @param callback         - callback to receive action result or handler progress
      */
    ziggeo.streams().attachImage(videoTokenOrKey, String streamTokenOrKey, File imageFile, Callback callback);

*Attach a video*

    /**
      * Attaches a video to a new stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param videoFile        - file to attach
      * @param callback         - callback to receive action result or handle progress
      */
    ziggeo.streams().attachVideo(String videoTokenOrKey, String streamTokenOrKey, File videoFile, Callback callback);

*Bind the stream*

Once binded it is sent to processig

    /**
      * Closes and submits the stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param callback         - callback to receive action result
      */
    ziggeo.streams().bind(String videoTokenOrKey, String streamTokenOrKey, Callback callback);

*Download the stream video*

Downloads the video associated with the stream

    /**
      * Download the video data associated with the stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param callback         - callback to receive action result
      */
     ziggeo.streams().downloadVideo(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);

*Download the image*

Downloads the image that is associated with the stream

    /**
      * Download the image data associated with the stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param callback         - callback to receive action result
      */
     ziggeo.streams().downloadImage(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);

*Get the info from single stream*

    /**
      * Get a single stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param callback         - callback to receive action result
      */
    ziggeo.streams().get(String videoTokenOrKey, String streamTokenOrKey, Callback callback);

*Delete the stream*

    /**
      * Delete the stream
      *
      * @param videoTokenOrKey  - video token
      * @param streamTokenOrKey - stream to attach a file
      * @param callback         - callback to receive action result
      */
    ziggeo.streams().delete(String videoTokenOrKey, String streamTokenOrKey, Callback callback);

### Request Cancellation

Sometimes you might make an API request and realize that you want to cancel the request. For example if your customer realizes that they have selected a wrong video to upload.

For such cases, we have made it possible to do the same.

Every service call returns a `Call` object, allowing you to cancel the request execution. You can do that by invoking the `cancel` method.

For example:

    Call call = mVideoService.create(...);
    call.cancel();

Note: you can also cancel the last active request for recorder using
`ziggeo.cancel()`

### Authentication

Our API is utilizing patent pending authorization token system. It allows you to secure and fine tune what someone can do and for how long.

The following will be needed if you have enabled the authorization tokens in your app.

Client Auth

    /**
      * Sets the client auth token.
      *
      * @param token - token which will be used for authentication in requests
      */
    ziggeo.setClientAuthToken(@NonNull String token);

Server Auth

    /**
      * Sets the server auth token.
      *
      * @param token - token which will be used for authentication in requests
      */
    ziggeo.setServerAuthToken(@NonNull String token);

### Examples

### Theming

- There is an ability to style the player. You can use one of the predefined themes and change colors for them.
Styling available through `styles.xml` and through `PlayerStyle` class.
If both way used, only params in code will be handled.
Sample can be found in `ZiggeoPlayerActivity`.
- Use `hideControls()` method in `PlayerStyle` and `CameraRecorderStyle` for making the control invisible.
Or `hidePlayerControls` and `hideRecorderControls` in `styles.xml`

### Configure the recorder

Our recorder is utilizing helper class to define different properties of the recorder element. So we would always define it first

    RecorderConfig.Builder configBuilder = new RecorderConfig.Builder();

*Set max duration*

    /**
      * Set the maximum duration of the video. `0` for endless recording.
      * Also used in elapsed time indicator in the top-right corner.
      * <p>
      * Default value is `0`
      *
      * @param duration - duration in millis
      */
    configBuilder.maxDuration(long maxDuration);

*Set countdown time*

    /**
     * Set the delay before the recording will be started
     * Default value is `3` seconds
     *
     * @param startDelay - delay
     */
    configBuilder.startDelay(int startDelay);

*Auto start recorder*

    /**
     * Configure if the recording should be started 
     * after initialisation of the recorder.
     */
    configBuilder.autostartRecording(boolean autostart);

*Set which camera you prefer*

    /**
      * Set what camera facing to use by default.
      * Default value is {@link CameraView.FACING_BACK}
      *
      * @param facing - back or front facing
      */
    configBuilder.facing(@CameraView.Facing int facing);

*Set the quality of recording*

    /**
      * Set what quality to use for recording.
      * Default value is {@link CameraView.QUALITY_HIGH}
      *
      * @param videoQuality - the quality
      */
    configBuilder.quality(@CameraView.Quality int videoQuality);

*Can camera be switched during recording?*

    /**
      * If true the button for switching will not be shown.
      * Default value is `false`.
      */
    configBuilder.disableCameraSwitch(boolean disable);

*Should video be sent to Ziggeo servers right away?*

    /**
      * If true the video will be sent right after it was recorded.
      * Default value is `true`.
      */
    configBuilder.sendImmediately(boolean send);

*Show stop dialog*

    configBuilder.confirmStopRecording(boolean confirmStopRecording);
    
    /**
      * Configure a dialog to confirm recording stop.
      * The dialog will be shown for both cases: either user press `stop` button or `sendAndClose` checkmark.
      *
      * @param config - the config where you can set string resources to use for title, message and positive and negative buttons.
      */
    configBuilder.initStopRecordingConfirmationDialog(@Nullable StopRecordingConfirmationDialogConfig config);

*Register callback*

    /**
      * Register a callback to be invoked when a recording 
      * is started, stopped, an error occurred, etc.
      *
      * @param callback - the callback
      */
    configBuilder.callback(callback);

*Extra arguments to be sent*
This can be used to specify effect profiles, video profiles, custom data, etc.

    /**
      * Set extra arguments for create video request.
      *
      * @param extraArgs - args will be sent with create video request
      */
    configBuilder.extraArgs(@Nullable HashMap<String, String> extraArgs);

### Extra arguments examples

*Custom data*

    HashMap<String, String> extraArguments = new HashMap<>();
    extraArguments.put("data", "{\"key\":\"value\"}");
    
    RecorderConfig config = new RecorderConfig.Builder()
                    .maxDuration(5000)
                    .extraArgs(extraArguments)
                    .build();

*Effect Profile*

    HashMap<String, String> extraArguments = new HashMap<>();
    extraArguments.put("effect_profile", "1234567890");
    
    RecorderConfig config = new RecorderConfig.Builder()
                    .extraArgs(extraArguments)
                    .build();

*Video Profile*

    HashMap<String, String> extraArguments = new HashMap<>();
    extraArguments.put("video_profile", "1234567890");
    
    RecorderConfig config = new RecorderConfig.Builder()
                    .extraArgs(extraArguments)
                    .build();

## Update Information<a name="update"></a>

The information is for upgrading from `v.0.78.4` to `0.79.0`. You will need to inspect the Changelog if you are switching from older versions.

### No broken changes, you can safely upgrade.

- Added zoom support for the camera.
- Added `mute` config for player

## Changelog<a name="Changelog"></a>

Version 0.79.0*(2019-03-18)*
----------------------------
* Added zoom support for the camera.
* Added `mute` config for player
  
Version 0.78.4*(2019-03-05)*
----------------------------
* Fixed access to the recorder configuration.
  
Version 0.78.3*(2019-03-04)*
----------------------------
* Fixed issue in player cause by wrong type in JSON
  
Version 0.78.2*(2019-03-04)*
----------------------------
* Fixed stop confirmation for screen recorder
* Fixes for Xamarin integration
  
Version 0.78.1*(2019-02-14)*
----------------------------
* Added `setSensorCallback()` method for all possible info from sensors 
* Added docs for several methods
* Fixed player issue with seekbar: it was not possible to change the progress of the video
* Fixes for Xamarin integration:  
  * removed duplicated `error` callback from two different interfaces, instead, it moved to a separate `IErrorCallback` 
  * own styles implemented instead of using Exoplayer’s styles 
  * fixed assigning of ids for views 
  * added attrs missing in Exoplayer’s binding
  
Version 0.78.0*(2019-02-06)*
----------------------------
 - Changes in autostart logic. Method `setAutostartRecordingAfter` is deprecated, instead there are now two methods
   a. `setAutostartRecording` - if `true`, when the recorder is initialized, the count down timer will be started. After the time will run out the recording will be started.
   Default if `false`.
   b. `setStartDelay` - set the value for count down timer before start the recording. Default is `3` seconds.

 - Foreground service for recordings uploading. During the uploading notifications will be visible in notification bar. In case of error there are two options available `Retry` and `Try later`
 In case of try later the SDK will try to make uploading after the delay. Default delay is 2 hours.
 Also it is possible to do uploading only when WiFi is available.
 See sample below:
 ```
         ziggeo.configureUploading(new UploadingConfig.Builder()
                .syncInterval(15 * 60 * 1000)
                .useWifiOnly(true)
                .build());
 ```
 - Face outline image.
 FaceOutlineConfig config = new FaceOutlineConfig();
  config.setShow(true);
// config.setOutlineImageResource(R.drawable.youImage); // optional
// put created object in `RecorderConfig`
```
 - New file selector with multiple selection ability.
 - `ziggeo.clearRecorder` - the method that allows the devs to clear the cache through code. Would remove the videos even if they are not uploaded.
 - Playlist for the player. `ziggo.startPlayer(token1, token2, ...)`
 - Player theming. 
   There is now ability to style the player. You can use one of the predefined themes and change colors for them. Styling available through styles.xml and through code. If both way used, only params in code will be handled. 
   Sample can be found in `ZiggeoPlayerActivity`.
```
 - New callback system for player, recorder and camera
 - Subtitles for video player
 `playerConfig.showSubtitles(boolean value)`

Fixes/Small changes
 - New method `getNumberOfCameras()` in `CameraView` class.
 - fixed camera issue on Samsung Galaxy J7
 - date pattern in file names changed from `dd-MM-yyyy_hh-mm-ss` to `dd.MM.yyyy_HH.mm.ss`

Broken changes
 - java8
 - method `startRecorder(boolean audioOnly)` is deprecated. Please, use `startAudioRecorder()`.
 - method `startRecorder()` is deprecated. Please, use `startCameraRecorder()`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId)` is deprecated. Please, use `attachCameraRecorder(FragmentManager fragmentManager, int contentId)`.
 - method `attachRecorder(FragmentManager fragmentManager, int contentId, boolean audioOnly)` is deprecated. Please, use `attachAudioRecorder(FragmentManager fragmentManager, int contentId)`.
 - methods `setExtraArgsForRecorder`, `getExtraArgsForRecorder`, `configureStopRecordingConfirmationDialog`, `getStopRecordingConfirmationDialogConfig`, `setAutostartRecordingAfter`, `setAutostartRecordingAfter`, `getAutostartRecordingAfter`, `setVideoRecordingProcessCallback`, `setRecordingProcessCallback`, `getVideoRecordingProcessCallback`, `getRecordingProcessCallback`,
 `setTurnOffCameraWhileUploading`, `setColorForStoppedCameraOverlay`, `setDrawableForStoppedCameraOverlay`, `setCoverSelectorEnabled`, `setCoverSelectorEnabled`, `setMaxRecordingDuration`, `setPreferredCameraFacing`, `setPreferredQuality`, `getPreferredQuality`, `setCameraSwitchDisabled`, `setSendImmediately`, `isSendImmediately` are deprecated. Please, use methods in `CameraRecorderConfig.Builder` class.
 - methods `configureLocalPlayback`, `getLocalPlaybackConfig`, `setExtraArgsForPlayer` are deprecated. Please, use corresponding
 - method `addCallback` in `CameraView` class is deprecated. Please, use `setCameraCallback` and `

Version 0.77.1*(2018-12-10)*
----------------------------
* Added methods for setting theme params for non-native SDKs.

Version 0.77.0*(2018-10-16)*
--------------------------
* Deep linking support
* Playlist support
* Added theming params for hiding recorder and player controls
* Audio recorder implemented
* `IVideoRecordingCallback` is deprecated, use `IRecordingProcessCallback` instead.

Version 0.76.1*(2018-10-29)*
--------------------------
* Fixed token value returned by `create` request for Xamarin.

Version 0.76.0*(2018-10-10)*
--------------------------
* Method `onProgressUpdate(long sentBytes, long totalBytes)` in ProgressCallback class is deprecated.
  Use `onProgressUpdate(@NonNull File file, long sentBytes, long totalBytes)` instead.
* Added delegates for tracking Uploading progress in Xamarin
* Gradle version updated
* Fixed crash logging
* Added deep linking for player
* Player switched to CDN server.
* Fixed issue when screen recording is not started if screen share permission has not been given previously.
* Methods `startScreenRecordService`, `stopScreenRecordService`, `stopScreenRecord` are deprecated.
* Added check on null bundle to prevent crash.

Version 0.75.0*(2018-8-19)*
--------------------------
* New: Methods marked as deprecated
 `startScreenCaptureService`, `stopScreenCaptureService`, `startScreenCapture`, `stopScreenCapture`
 Use instead
 `startScreenRecordService`, `stopScreenRecordService`, `startScreenRecord`, `stopScreenRecord`

Version 0.75.0*(2018-8-19)*
--------------------------
* New: Added send-and-close feature for screen recorder.
* New: Showing sound bar on player screen when sound icon was long clicked.
* Fix: Forced screen recorder service notification text and background color to black
* Fix: Fixed crash when extra args for recorder are null
* Fix: Added nullability annotations for player
* Fix: For screen recorder if cover shot selector is visible, it will be closed when new recording is started
* Fix: UI lag when stopping the recording
* Fix: Removed library for getting the device name

Version 0.74.3*(2018-8-10)*
--------------------------
* Added crash tracking system.
* Bugfixing
* Gradle version updated

Version 0.74.2*(2018-8-1)*
--------------------------
 * Removed old deprecated methods
 ```
 setExtraArgsForCreateVideo
 setExtraArgsForEmbeddedRecorder
 setExtraArgsForEmbeddedPlayer
 setExtraArgsForPlayVideo
 setRecorderCacheFolder
 getRecorderCacheFolder
 setMaxRecorderCacheSize
 getMaxRecorderCacheSize
 setMaxRecorderCacheFilesCount
 getMaxRecorderCacheFilesCount
 setPlayerCacheFolder
 getPlayerCacheFolder
 setMaxPlayerCacheSize
 getMaxPlayerCacheSize
 setMaxPlayerCacheFilesCount
 getMaxPlayerCacheFilesCount
 initStopRecordingConfirmationDialog
 getCacheFolder
 getMaxCachedVideosCount
 getMaxCacheSize
 setMaxCachedFilesCount
 ```
 * Added mute sound functionality for player

Version 0.74.0*(2018-7-28)*
--------------------------
 * API 21+: Added screen recorder feature.
 * Fixed vector icon causing crash on android below API 21.
 * `com.ziggeo.androidsdk.net.rest.ProgressCallback` moved to `com.ziggeo.androidsdk.net.callbacks.ProgressCallback`
 * Class `ResponseException` renamed to `ResponseException`
 * In class `ResponseException` methods `getResponse` and `setResponse` are deprecated. Use `getMessage` and `setMessage` instead.
 * Classes `IStreamsService`, `StreamsService`,  `IVideosService`,  `VideosService` moved from `com.ziggeo.androidsdk.net.rest.services.` to `com.ziggeo.androidsdk.net.services.`

Version 0.73.12*(2018-7-18)*
--------------------------
 * Fixed error during recorder initialisation on Android 8.0 for Camera2 api.

Version 0.73.11*(2018-6-21)*
--------------------------
 * Changed checkmark behavior - currently it closes the recorder.
   Also the recorder will be closed if video is sent immediately after the recording is finished.


Version 0.73.10*(2018-6-13)*
--------------------------
 * Changed return type for `ziggeo.getVideoRecordingProcessCallback()` from class to its interface.
 * Fixed issue 20. Lost bundle args were causing a crash.
 * Gradle version updated.
 * Small xml layout improvements.
 * Removed deprecated method from IVideoRecordingCallback.
 * Added applyEffect method to Videos API.

Version 0.73.8*(2018-5-12)*
--------------------------
 * Added callback to indicate video uploading started.

Version 0.73.7*(2018-5-11)*
--------------------------
 * Fixed recorder icons visibility

Version 0.73.6*(2018-5-08)*
--------------------------
 * Removed rests of Hungarian notation.
 * Replaced old icons with vectors
 * General code/layouts improvements

Version 0.73.5*(2018-5-07)*
--------------------------
 * Additional permission check for player
 * Small code improvements and bugfixes

Version 0.73.4*(2018-4-25)*
--------------------------
 * Removed some unused classes.
 * Small code improvements.
 * Improved gradle config for naming of output build files.

Version 0.73.3*(2018-4-23)*
---------------------------
 * Added a few getters to prevent issues when binding the SDK with Xamarin.

Version 0.73.2*(2018-4-11)*
--------------------------
 * Added ability to set client and server auth tokens for all requests.

Version 0.73.1*(2018-3-28)*
--------------------------
 * Fix: Added 1 hour expiration time for a session token
 * Config improvements:
    * Gradle wrapper updated to 4.4
    * Gradle plugin version updated to 3.1.0
    * Build tools version updated to 27.0.3
    * Project dependencies provided with the latest approach

Version 0.73.0*(2018-3-20)*
---------------------------
 * Docs updated.
 * Fixed issue when dialog for stop recording confirmation overlaps with covershot selection dialog.
 * Fixed issue when dialog for stop recording confirmation does not hide when recording actually stops after time limit exceeded.
 * Changes for player's cache
  * To configure cache `ziggeo.configureLocalPlayback(@Nullable LocalPlaybackConfig config)` should be used.
    Other methods is deprecated.
  * Default maximum cache size changed to 512Mb
  * Default maximum files count in cache changed to 5
  * Default cache path cahnged to `/Android/data/[app_package_name]/cache/video-cache/` if card is mounted and app has appropriate permission
    or `video-cache` subdirectory in default application's cache directory otherwise.
 * Added `ziggeo.setDrawableForStoppedCameraOverlay(@DrawableRes int drawableResource)`
 * Deprecated `onError()` method in IVideoRecordingCallback and added `onError(throwable)`.
 * Deprecated method for configuration of stop rectoring confirmation dialog.
   Instead of `ziggeo.initStopRecordingConfirmationDialog(boolean show, @StringRes int titleResId, @StringRes int mesResId,
                                                                   @StringRes int posBtnResId, @StringRes int negBtnResId)`

    please use `ziggeo.configureStopRecordingConfirmationDialog(@Nullable StopRecordingConfirmationDialogConfig config)`
 * Deprecated methods related to recorder's cache
  * `ziggeo.setRecorderCacheFolder(@NonNull String cacheFolderPath)`
  * `ziggeo.getRecorderCacheFolder()`
  * `ziggeo.setMaxRecorderCacheSize(long maxCacheSize)`
  * `ziggeo.getMaxRecorderCacheSize()`
  * `ziggeo.setMaxRecorderCacheFilesCount(int filesCount)`
  * `ziggeo.getMaxRecorderCacheFilesCount()`
 * Deleted old deprecated method for overriding layout for recorder
   `ziggeo.setRecorderLayout(@LayoutRes int layoutId)`
 * Deleted old deprecated classes and methods
   * `IOUtils.checkVideoIsInCache(String token)`
   * `HttpStatusCodes`
   * `CameraHelper`
   * `ziggeo.cancel()`
   * `ziggeo.setCacheFolder(@NonNull String cacheFolderPath)`
   * `ziggeo.setMaxCacheSize(long maxCacheSize)`
   * `ziggeo.setShowCoverShotSelectionPopup(boolean showCoverShotSelectionPopup)`
   * `ziggeo.startPlayer(@NonNull Context context, @NonNull Uri path)`
   * `ziggeo.startPlayer(@NonNull Context context, @NonNull String videoToken)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager fragmentManager, int contentId, long maxDurationInMillis,                Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, boolean disableCameraSwitching, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, int preferredCameraId, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, boolean disableCameraSwitching, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.attachRecorder(@NonNull FragmentManager manager, int contentId, long maxDurationInMillis, int preferredCameraId, CameraHelper.Quality videoQuality, Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, boolean disableCameraSwitching, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @CameraView.Facing int preferredCamera, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, boolean disableCameraSwitching, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`
   * `ziggeo.createVideo(@NonNull Context context, long maxDurationInMillis, @CameraView.Facing int preferredCamera, @Nullable CameraHelper.Quality videoQuality, @Nullable Callback callback)`

Version 0.72.7*(2018-3-6)*
--------------------------
 * Lambdas removed from the SDK.

Version 0.72.6*(2018-3-5)*
--------------------------
 * Method `setExtraArgsForCreateVideo` was deprecated, please use `setExtraArgsForEmbeddedRecorder`.
 * Method `setExtraArgsForPlayVideo` was deprecated, please use `setExtraArgsForEmbeddedPlayer`.

Version 0.72.4*(2018-3-1)*
--------------------------
 * Fix: Session token now automatically appended to every request.

Version 0.72.3*(2018-2-26)*
---------------------------
 * New: Removed deprecated callbacks for video compressor.
 * Fix: Fixed authentication through server/client tokens.

Version 0.72.2*(2018-2-16)*
---------------------------
 * New: Removed deprecated video encoder.

Version 0.72.1*(2018-2-8)*
--------------------------
 * New: Changed launch mode for recorder activity to `singleInstance`.

Version 0.72.0*(2018-1-29)*
----------------------------
 * New: Added settings for player cache.
 * Fix: Cache for player
 * Fix: Checking for filled cache.
 * Fix: Blank preview for case when the app returns from background.

Version 0.71.7*(2017-12-25)*
----------------------------
 * Fix: Fixed issue with loosing config for camera in case of autoswitching from Camera2 to Camera1.
 * Fix: Added seconds to video file name to prevent losing videos recorded during one minute.

Version 0.71.6*(2017-11-17)*
----------------------------
 * Fix: Crash when starting activity from application context.

Version 0.71.5*(2017-11-12)*
----------------------------
 * Fix: Removed compressor for medium video size. Lower bitrate set for medium and low quality. 
 * Fix: Showing preview after start pressed if the preview was hidden. 
 * Versions of dependencies updated.

Version 0.71.1*(2017-11-7)*
----------------------------
 * New: Analytics integration.
 * Fix: Orientation calculation for recorder in Camera1 
 * Fix: Params transferring between Ziggeo object and internal activities/fragments
 * Fix: Default state for `switchCameraDisabled` set to `false`
 * Other fixes and improvements

Version 0.70.1*(2017-10-10)*
----------------------------
 * New: Added ability to start player as separate screen.

Version 0.70.0*(2017-09-19)*
----------------------------
 * Fix: Fixed player layout.
 * Fix: Fixed logic for immediate video sending.
 * New: Methods for getting Videos and Stream services exposed to interface.

Version 0.69.2*(2017-09-19)*
----------------------------
 * New: Video compressor exposed to interface.

Version 0.69.1*(2017-09-15)*
----------------------------
 * Fix: Forced AAC audio codec and H264 video codec for Camera1

Version 0.69.0*(2017-09-14)*
----------------------------
 * Fix: Improved video size scaling for medium quality
 * New: Added video compressor in case of using medium video quality
 * New: Added two methods to VideoRecordingCallback to indicate compression progress

Version 0.68.1*(2017-08-17)*
----------------------------
 * Fix: Missing resource id crash
 * Fix: Removed wrong `Deprecated` mark

Version 0.68.0*(2017-08-16)*
----------------------------
 * Broken compatibility: `CameraView` should be using for layout customisation instead of `SurfaceView`
 * New: Customisation through layout is `Deprecated`. `CameraView` should be used instead.
 * Fix: Reduced video file size
 * New: Added quality settings for `CameraView`
 * Fix: Bugfixing
 
Version 0.67.6*(2017-08-10)*
----------------------------
 * Fix: Resolved issue related to uploading speed.

Version 0.67.5 *(2017-08-2)*
----------------------------
 * New: `createVideo` methods now deprecated. All params should be set through setters.
 Use `startRecorder` instead.
 * New: Change `onProgressUpdate` callback. Now it accepts `sent` and `total` bytes as args.
 * New: If max duration is zero video will be endless.
 * New: Added extra time format for case of zero maximum duration.
 * Fix: Small issues
 * Fix: UI improvements
 
Version 0.67.0 *(2017-07-9)*
----------------------------
 * Fix: Refactoring of SDK configuration
 * Fix: Issue related to missing cover selector dialog
 
Version 0.66.0 *(2017-04-28)*
----------------------------
 * New: SDK refactored with support library
 * New: Added sample for playing uploaded video
 
Version 0.65.1 *(2017-04-13)*
----------------------------
 * Fix: Proper selection of resolution for video recording 

Version 0.65.0 *(2017-04-05)*
----------------------------
 * New: Added support for EU host.
 * Fix: Minor UI fixes

Version 0.64.0 *(2017-04-03)*
----------------------------
 * New: Added special `CameraView` class to provide ability to create fully customizable recorder.

Version 0.63.2 *(2017-03-07)*
----------------------------
 * New: Ability to get sdk via simple gradle dependency
 * New: Autostart recording after delay using `ziggeo.setAutostartRecordingAfter(long millis)`
 * New: `delete()` method for `StreamService` and `VideoService` was deprecated. Please, use `destroy()` instead.
 * New: Added dialog to confirm stop recording. Use through `ziggeo.initStopRecordingConfirmationDialog()`
 * New: Ability to format time on recorder screen `ziggeo.setTimeFormatAction()`
