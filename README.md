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
    3. [QR Scanner](#qr-scanner)
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
            implementation 'com.github.ZiggeoJitpackService:Android-SDK:$version'
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

### QR Scanner<a name="qr-scanner"></a>
```
    ziggeo.setQrScannerConfig(...)
    ziggeo.startQrScanner();
```
See more in `QrScannerActivity`

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

The information is for upgrading from `0.81.1` to `0.82.0`. You will need to inspect the Changelog if you are switching from older versions.
#### Broken changes
* Deleted `cancelRequest` method
#### Other changes
* Added QR Scanner feature
* OkHttp version set to `3.12.6` - this is max version that supports API 16
* Improvements for uploading queue and related notifications

## Changelog<a name="Changelog"></a>

If you are interested in our changelog you can find it as a separate file next to this readme. It is named as [CHANGELOG.md](CHANGELOG.md)
