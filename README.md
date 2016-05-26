# Android-SDK
## Please, use latest build tools and compile sdk version.
### How to include the sdk:
Put **ziggeo-sdk-v*.aar** in **libs** folder for your project.
In Android Studio choose File->New->New Module and select Import .JAR/.AAR Package.
Add the following dependencies to your build.gradle file
```
        compile(name: 'ziggeo-sdk-v0.50.0', ext: 'aar')
        compile 'com.squareup.okhttp3:okhttp:3.2.0'
        compile 'com.danikula:videocache:2.3.2'
        compile "com.android.support:support-v4:23.3.0"
        compile "com.android.support:support-v13:23.3.0""
```

## Fullscreen video recorder:
```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
long maxVideoDuration = 1000 * 60 * 5; //for ex. 5 mins.

/**
  * Use this for launch standalone activity with video recorder and player.
  * With standalone recorder can be used {@link com.ziggeo.androidsdk.eventbus.events.CloseRecorderEvent}
  *
  * @param context             - context
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  * @param callback            - callback to receive video recording result
  */
ziggeo.createVideo(Context context, long maxDurationInMillis, Callback callback);
```
## Embedded video recorder:
```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
long maxVideoDutaion = 1000 * 60 * 5; //for ex. 5 mins.

/**
  * Use this for launch embedded recorder
  *
  * @param manager             - {@link FragmentManager}
  * @param contentId           - Identifier of the container this fragment is to be placed in.
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  * @param callback            - callback to receive video recording result
  */
ziggeo.attachRecorder(FragmentManager manager, int contentId, long maxDurationInMillis, Callback callback);
```
##### Add extra args
```java
ziggeo.setExtraArgsForCreateVideo(HashMap<String, String> extraArgs);
```

##### By default recorded video will send immideately after it was recorded. 
##### To prevent this use
```java
ziggeo.setSendImmediately(false);
```
##### You also can disable camera switching
```java
/**
     * Use this for launch embedded recorder
     *
     * @param manager                - {@link FragmentManager}
     * @param contentId              - Identifier of the container this fragment is to be placed in.
     * @param maxDurationInMillis    - allowed max video duration in milliseconds.
     * @param disableCameraSwitching - removes ability to switch cameras.
     *                               The default camera is {@link Camera.CameraInfo.CAMERA_FACING_BACK}
     */
    public void attachRecorder(FragmentManager manager, int contentId, long maxDurationInMillis,
                               boolean disableCameraSwitching)
/**
     * Use this for launch standalone activity with video recorder and player.
     * With standalone recorder can be used {@link com.ziggeo.androidsdk.eventbus.events.CloseRecorderEvent}
     *
     * @param context                - context
     * @param maxDurationInMillis    - allowed max video duration in milliseconds.
     * @param disableCameraSwitching - removes ability to switch cameras.
     *                               The default camera is {@link Camera.CameraInfo.CAMERA_FACING_BACK}
     */
    public void createVideo(Context context, long maxDurationInMillis, boolean disableCameraSwitching)
```

##### Or select default camera and disable switching.
```java
/**
     * Use this for launch standalone activity with video recorder and player.
     * With standalone recorder can be used {@link com.ziggeo.androidsdk.eventbus.events.CloseRecorderEvent}
     *
     * @param context             - context
     * @param maxDurationInMillis - allowed max video duration in milliseconds.
     * @param preferredCameraId   - allow the app use only selected camera if exists.
     */
    public void createVideo(Context context, long maxDurationInMillis, int preferredCameraId)
/**
     * Use this for launch embedded recorder
     *
     * @param manager             - {@link FragmentManager}
     * @param contentId           - Identifier of the container this fragment is to be placed in.
     * @param maxDurationInMillis - allowed max video duration in milliseconds.
     * @param preferredCameraId          - allow the app use only selected camera if exists.
     */
    public void attachRecorder(FragmentManager manager, int contentId, long maxDurationInMillis,
                               int preferredCameraId)
```
##### And select video quality using
```java
com.ziggeo.androidsdk.recording.CameraHelper.Quality
```

## Video player:
```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
Uri path = ...; // path to file or stream url
// or
String token = ...; // video token

/**
  * Use this for launch embedded player from path
  *
  * @param manager   - {@link FragmentManager}
  * @param contentId - Identifier of the container this fragment is to be placed in.
  * @param path      - {@link Uri} path to file.
  */
ziggeo.attachPlayer(FragmentManager manager, int contentId, Uri path);
```

```java
 /**
  * Use this for launch embedded player from stream.
  * Can be also used with extra args.
  *
  * @param manager   - {@link FragmentManager}
  * @param contentId - Identifier of the container this fragment is to be placed in.
  * @param token     - video token.
  */
ziggeo.attachPlayer(FragmentManager manager, int contentId, String videoToken);
```

##### Add extra args
```java
ziggeo.setExtraArgsForPlayVideo(...);
```

## Ziggeo API access
##### Videos api
```java
/**
     * Delete a single video by token or key.
     *
     * @param keyOrToken - video token or key.
     *                   If you're using key make sure to add "_" prefix.
     * @param callback   - callback to receive action result
     */
ziggeo.videos().delete(String keyOrToken, Callback callback);

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

```java
/**
     * Create a new stream
     *
     * @param videoTokenOrKey - video for which stream will be created
     * @param callback        - callback to receive action result
     */
ziggeo.streams().create(String videoTokenOrKey, Callback callback);

/**
     * Attaches an image to a new stream
     *
     * @param videoTokenOrKey  - video token
     * @param streamTokenOrKey - stream to attach a file
     * @param imageFile        - file to attach
     * @param callback         - callback to receive action result
     */
ziggeo.streams().attachImage(videoTokenOrKey, String streamTokenOrKey, File imageFile, Callback callback);

/**
     * Attaches a video to a new stream
     *
     * @param videoTokenOrKey  - video token
     * @param streamTokenOrKey - stream to attach a file
     * @param videoFile        - file to attach
     * @param callback         - callback to receive action result
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
