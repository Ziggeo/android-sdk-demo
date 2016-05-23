# Android-SDK
## Please, use latest build tools and compile sdk version.
### How to include the sdk:
Put **ziggeo-sdk-v*.aar** in **libs** folder for your project.
In Android Studio choose File->New->New Module and select Import .JAR/.AAR Package.
Add the following dependencies to your build.gradle file
```
        compile(name: 'ziggeo-sdk-v0.44.0', ext: 'aar')
        compile 'com.squareup.okhttp3:okhttp:3.2.0'
        compile 'com.google.guava:guava:18.0'
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
  */
ziggeo.createVideo(this, maxVideoDutaion);
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
  */
ziggeo.attachRecorder(getFragmentManager(), R.id._your_id_here_, maxDuration);
```
##### Add extra args
```java
ziggeo.setExtraArgsForCreateVideo(RestParams extraArgs);
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
ziggeo.attachPlayer(manager, contentId, path);
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
ziggeo.attachPlayer(manager, contentId, token);
```

##### Add extra args
```java
ziggeo.setExtraArgsForPlayVideo(...);
```

## Events (read more here: https://github.com/greenrobot/EventBus):
##### Subscribe for events 
```java
BusProvider.getInstance().register(this);
```
##### Unsubscribe 
```java
BusProvider.getInstance().unregister(this);
```

##### Receive events
```java
@Subscribe
public void onVideoSent(VideoSentEvent event){}

@Subscribe
public void onCreateVideoError(CreateVideoErrorEvent event){}
```

##### Send events
```java
// for ex. to close recorder from anywhere
BusProvider.getInstance().post(new CloseRecorderEvent());
```

## Ziggeo API access
##### Direct video file uploading
```java
ziggeo.uploadVideoFile(Context context, Video videoFile);
```

##### Index
```java
/**
  * Query an array of videos (will return at most 50 videos by default).
  * Newest videos come first.
  *
  * @param context -  context
  * @param params  - request params
  */
ziggeo.index(Context context, RestParams params);

@Subscribe
public void onIndexRequestSuccess(IndexRequestSuccessEvent event) {
    Log.e(TAG, "onIndexRequestSuccess:" + event.getResult());
}

@Subscribe
public void onIndexRequestError(IndexRequestErrorEvent event) {
    Log.e(TAG, "onIndexRequestError:" + event.getException().getMessage());
}
```


