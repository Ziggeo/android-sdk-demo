[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/ZiggeoJitpackService/Android-SDK.svg)](https://jitpack.io/#ZiggeoJitpackService/Android-SDK)


# Ziggeo's Android SDK

## Index

1. [Why Ziggeo's Android SDK?](#why-us)
2. [Prerequisites](#prerequisites)
	1. [Download](#download)
	2. [Dependencies](#dependencies)
	3. [Install](#install)
3. [Demo](#demo)
4. [Codes](#codes)
	1. [Init](#init)
	2. [Recorder](#recorder)
		1. [Video Camera Recorder](#video-recorder)
		2. [Video Screen Recorder](#screen-recorder)
		3. [Audio Recorder](#audio-recorder)
	3. [Player](#player)
		1. [Video Player](#video-player)
		2. [Audio Player](#audio-player)
	4. [QR Scanner](#qr-scanner)
	5. [Configs](#configs)
		1. [Theming](#theming)
		2. [Recorder Configs](#recorder-config)
	6. [Events / Callbacks](#events)
		1. [Global Callbacks](#callbacks-global)
		2. [Recorder Callbacks](#callbacks-recorder)
		3. [Player Callbacks](#callbacks-player)
		4. [Sensor Callbacks](#callbacks-sensor)
	7. [API](#api)
		1. [Request Cancellation](#api-cancel)
		2. [Videos API](#api-videos)
		3. [Video Streams API](#api-video-streams)
	8. [Authentication](#authentication)
5. [Compiling and Publishing App](#compile)
6. [Update Information](#update)
7. [Changelog](#changelog)


## Why Ziggeo's Android SDK? <a name="why-us"></a>

Ziggeo is powerfull whitelabel video SAAS that helps people be part and lead the video revolution. It has award winning multimedia API and its CEO and CTO can often be seen talking in various events around the world.

Ziggeo's Android SDK is utilizing the API to bring you a native library you can add to your project. Just add to project and allow your application evolve into something much more, something video!

It offers you pre-built functionality to call and manipulate. This library also offers you direct access to the Ziggeo API as well if you want to do more things yourself.

### Who it is for?

Do you have a complex Android app that is missing that one key ingredient - the video?

Do you need a video library that can be seamlessly integrated into your existing app?

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
		implementation 'com.github.ZiggeoJitpackService:Android-SDK:$version'
	}
```

## Demo<a name="demo"></a>

Our SDK is available through JitPack service and using above you can add it to your project. [This repository](https://github.com/Ziggeo/android-sdk-demo) is the demo of the same.


## Codes<a name="codes"></a>

This section will introduce you to the most common ways you would integrate our video library into your app.

### Init<a name="init"></a>

```
/**
  * @param appToken - Ziggeo application token
  * @param context - Application context
  */
Ziggeo ziggeo = new Ziggeo(appToken, context);
```

- You can grab your appToken by logging [into your](https://ziggeo.com/signin) account and under application you will use > Overview you will see the app token.

### Recorder<a name="recorder"></a>

Ziggeo supports different media formats and offers multiple recorder options for you.
1. Video Camera Recorder
2. Video Screen Recorder
3. Audio Recorder

Each will be showcased within its own section bellow.

#### Video (Camera) Recorder<a name="video-recorder"></a>

Video Recorder can be added in 2 ways. As a fullscreen recorder and the embedded camera recorder.

The fullscreen camera recorder is useful when you want your recorder to take entire screen.

The embedded camera recorder is useful when you want your recorder to be part of your app. For example if you had an avatar in your app and you want it to be a short video.

**Create fullscreen Video Recorder**

```
/**
  * Launch standalone activity with camera recorder and player.
  */
ziggeo.startCameraRecorder();
```

**Create embedded Video Recorder**

```
/**
  * Embed the recorder.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  */
ziggeo.attachCameraRecorder(@NonNull FragmentManager fragmentManager, int contentId);
```

- See [Configs](#configs) section bellow to see how to configure the recorder with more specific options, instead of using defaults

#### Video (Screen) Recorder<a name="screen-recorder"></a>

By utilizing the following you will be creating a foreground service for screen recording

```
ziggeo.startScreenRecorder(@Nullable ScreenRecordServiceNotificationConfig config);
```

- See [Configs](#configs) section bellow to see how to configure the recorder with more specific options, instead of using defaults


#### Audio Recorder<a name="audio-recorder"></a>

Audio Recorder can be added in 2 ways. As a fullscreen recorder and the embedded audio recorder.

The fullscreen audio recorder is useful when you want your recorder to take entire screen.

The embedded audio recorder is useful when you want your recorder to be part of your app. For example if you wanted the player to be something that is present while giving higher focus to other elements.

**Create fullscreen Audio Recorder**

```
/**
  * Launch standalone activity with microphone recorder.
  */
ziggeo.startAudioRecorder();
```

**Create embedded Audio Recorder**

```
/**
  * Embed the recorder.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  */
ziggeo.attachAudioRecorder(@NonNull FragmentManager fragmentManager, int contentId);
```

- See [Configs](#configs) section bellow to see how to configure the recorder with more specific options, instead of using defaults


### Player<a name="player"></a>

Capturing different types of media expects support for playback of the same. As such Ziggeo has a player for different type of media you might capture and use within your apps.

Ziggeo provides to following player:
1. Video Player
2. Audio Player

Each will be showcased within its own section bellow.

#### Video Player<a name="video-player"></a>

Player can be used to play local videos, videos from other services and of course videos from Ziggeo servers.

Just like recorder, the player can too be implemented as fullscreen or as embedded video player.

**Create fullscreen Video Player**

Standard Playback

```
/**
  * Launch standalone activity with the player to play the file from stream.
  *
  * @param videoToken - One or more video token(s).
  */
ziggeo.startPlayer(@NonNull String... videoToken);
```

Playback from third-party source

```
/**
  * Launch standalone activity with the player to play the file from uri.
  *
  * @param path - {@link Uri} One or more path to file.
  */
ziggeo.startPlayer(@NonNull Uri... path);
```

**Create embedded Video Player**

Standard Playback

```
/**
  * Embed the player to play the file from stream.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param videoToken      - One or more video token.
  */
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, String... videoToken);
```

Playback from third-party source

```
/**
  * Embed the player to play the file from uri.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param path            - {@link Uri} One or more path to file.
  */
ziggeo.attachPlayer(@NonNull FragmentManager fragmentManager, int contentId, Uri... path);
```

#### Audio Player<a name="audio-player"></a>

Player can be used to play local audios, as well as audios from other services and of course audios from Ziggeo servers.

Just like video player, the audio player can be used as fullscreen or as embedded audio player.

**Create fullscreen Audio Player**

Standard Playback

```
/**
  * @param audioToken - One or more audio token(s).
  */
ziggeo.startAudioPlayer(@NonNull String... audioToken);
```

Playback from third-party source

```
/**
 * @param path - {@link Uri} One or more path to file.
  */
ziggeo.startAudioPlayer(@NonNull Uri... path);
```

**Create embedded Audio Player**

Standard Playback

```
/**
  * Embed the player to play the file from stream.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param audioToken      - One or more audio token.
  */
ziggeo.attachAudioPlayer(@NonNull FragmentManager fragmentManager, int contentId, String... audioToken);
```

Playback from third-party source

```
/**
  * Embed the player to play the file from uri.
  *
  * @param fragmentManager - {@link FragmentManager}
  * @param contentId       - Identifier of the container this fragment is to be placed in.
  * @param path            - {@link Uri} One or more path to file.
  */
ziggeo.attachAudioPlayer(@NonNull FragmentManager fragmentManager, int contentId, Uri... path);
```

### QR Scanner<a name="qr-scanner"></a>

QR Scanner makes it easy for your code to retrieve data from the captured QR code.

```
ziggeo.setQrScannerConfig(...)
ziggeo.startQrScanner();
```

See more in `QrScannerActivity`


### Configs<a name="configs"></a>

Each embeddings (players and recorders) has default config and often a config you can set a bit differently if you wanted to.

This section will show you various options at your disposal.

#### Theming<a name="theming"></a>

- There is an ability to style the player. You can use one of the predefined themes and change colors for them.
- Styling is available through `styles.xml` and through `PlayerStyle` class.
- If you use both, only the params in code will be handled.
- Sample can be found in `ZiggeoPlayerActivity`.
- Use `hideControls()` method in `PlayerStyle` and `CameraRecorderStyle` for making the control invisible or `hidePlayerControls` and `hideRecorderControls` in `styles.xml`

#### Recorder Config<a name="recorder-config"></a>

Our recorder is utilizing helper class to define different properties of the recorder element. So we would always define it first. The recorder config is mutual for both audio and video recorders.

```
RecorderConfig.Builder configBuilder = new RecorderConfig.Builder();
```

**Set max duration**

The duration of the recording is always set as endless, meaning there is no limit in how long your video or audio recording can be. The value for this is 0.

If you set it up with 30000 this would be equal to 30 seconds of recording, after which the recording will be automatically stopped.

- When set, this time will also be used in the elapsed time indicator at the top-right corner.
- Note: Duration is in milliseconds.

```
/**
  * @param duration - duration in ms
  */
configBuilder.maxDuration(long maxDuration);
```

**Set countdown time**

When camera capture is started, the person might not be ready or might need to adjust the device before they are ready for capture. By default our recorder offers 3 seconds before the actual recording starts.

- Note: If you set it to 0, the person recording themselves might need to turn their phone, flip camera, or to align themselves first before they would actually start so we suggest keeping it somewhere within 2-5 seconds.

```
/**
  * @param startDelay - delay in seconds
  */
configBuilder.startDelay(int startDelay);
```

**Auto start recorder**

By default the recorder will show an option to start recording process. This is usually the preferred way for most use cases. In some use cases you might prefer that the recorder starts as soon as it is loaded up within the app. In such cases you can set the the following as true.

- Note: You might also want to check out `startDelay()` as well.

```
/**
  * @param autostart - boolean (true or false)
  */
configBuilder.autostartRecording(boolean autostart);
```

**Set which camera you prefer**

This option allows you to select which camera should be used for recording. By default the back camera is used `{@link CameraView.FACING_BACK}`, however you can change it with this option.

- Note: You can choose `FACING_FRONT` or `FACING_BACK`

```
/**
  * @param facing - back or front facing
  */
configBuilder.facing(@CameraView.Facing int facing);
```

**Set the quality of recording**

Set the quality that you want to use for your video recording. Usually the higher the quality, the better, however in some specific usecases where quality is not as important you could use this option to change it.
The default quality is `{@link CameraView.QUALITY_HIGH}`.

- Note: You can choose `QUALITY_HIGH`, `QUALITY_MEDIUM` and `QUALITY_LOW`.

```
/**
  * @param videoQuality - the quality
  */
configBuilder.quality(@CameraView.Quality int videoQuality);
```

**Forbid camera switch during recording**

By default we allow the camera to be switched within the recorder. Sometimes this might not be desirable, and if so you can forbid the ability to switch by setting this to true.

```
/**
  * @param disabled - boolean value (true or false)
  */
configBuilder.disableCameraSwitch(boolean disable);
```

**Submit videos immediately**

By default all videos are immediately sent to our servers. This allows them to be processed and to go through all of the workflows that you have set.
In some cases, you might want to show you button to confirm the video before it is sent or any other action you prefer, in which case you can delay this action.

- Note: You might also be interested in `confirmStopRecording()`. 

```
/**
  * @param send - boolean send (true or false)
  */
configBuilder.sendImmediately(boolean send);
```

**Show stop dialog**

In some cases you might want to be able to confirm after the recording is stopped. You could do that with our `confirmStopRecording()`  bellow, which is part of `configBuilder` and you can also provide it with the confirmation dialog.

```
/**
  * @param confirmStopRecording - boolean confirmation (true or false)
  */
configBuilder.confirmStopRecording(boolean confirmStopRecording);
```

This is also commonly followed with the dialog. It will be shown if the person recording video presses "Stop" button as well as if they click on "sendAndClose" checkmark.

- Note: You can set title, message and wording for the positive and negative buttons.

```
/**
  * @param config - the config where you can set string resources to use for title, message and positive and negative buttons.
  */
configBuilder.initStopRecordingConfirmationDialog(@Nullable StopRecordingConfirmationDialogConfig config);
```

**Set Extra Arguments**
This can be used to specify effect profiles, video profiles, custom data, tags, etc.

```
/**
  * @param extraArgs - args will be sent with create video request
  */
configBuilder.extraArgs(@Nullable HashMap<String, String> extraArgs);
```

##### Extra arguments examples

**Working with Custom Data**

Custom data is set with extraArgs and represents a JSON Object as string. This custom-data can be anything that you want to attach to the media you are recording or uploading.

```
HashMap<String, String> extraArguments = new HashMap<>();
extraArguments.put("data", "{\"key\":\"value\"}");

RecorderConfig config = new RecorderConfig.Builder()
                .maxDuration(5000)
                .extraArgs(extraArguments)
                .build();
```

**Applying Effect Profile**

If you would like to add your logo or apply some effect to every video that you record or upload, you will want to use effect profiles. They can be used by specifying the effect profile token or key.

- Note: If you are using effect profile key, please add `_` (underscore) before the name, even if the name has underscore within it (the first underscore is removed to match the key you are specifying).

```
HashMap<String, String> extraArguments = new HashMap<>();
extraArguments.put("effect_profile", "1234567890");

RecorderConfig config = new RecorderConfig.Builder()
                .extraArgs(extraArguments)
                .build();
```

**Set Video Profile**

Video profiles allow you to create video in various resolutions of interest. For example if you want to upload a 1080p video and want to have its versions available in SD format as well, this would be the way to do it.

You can add the video profile token by adding video profile token or video profile key.

- Note: If you are using video profile key, please add `_` (underscore) before the name, even if the name has underscore within it (the first underscore is removed to match the key you are specifying).

```
HashMap<String, String> extraArguments = new HashMap<>();
extraArguments.put("video_profile", "1234567890");

RecorderConfig config = new RecorderConfig.Builder()
                .extraArgs(extraArguments)
                .build();
```

- Note: All recorders are using the same config class described above.


### Events / Callbacks<a name="events"></a>

Callbacks allow you to know when something happens. They fire in case of some event happening, such as if error occurs. This way you can design your app to fine detail and be able to provide you customers with great experience.

We have separated the events that are available to you into several different categories.

Before doing that, you will need to register a callback and this is done within the `configBuilder`. If you are unsure how it is created please check section above [dedicated to it](#configs)

**Register callback**

```
/**
  * Register a callback to be invoked when a recording
  * is started, stopped, an error occurred, etc.
  *
  * @param callback - the callback
  */
configBuilder.callback(callback);
```

#### Global Callbacks<a name="callbacks-global"></a>

Global callbacks happen for both player and recorder. It usually does not depend on the embed method you have used, however each callback has additional details next to it.

**Error**

Ups, something unexpected happened! Now it's your time to react.

The following callback is called at any point in time when some error happens. It will also provide you with throwable parameter.

```
/**
 * @param throwable
 */
void error(@NonNull Throwable throwable);
```

**Loaded**

The embedding (player, recorder) is loaded up for the very first time after it was created

```
void loaded();
```

#### Recorder Callbacks<a name="callbacks-recorder"></a>

The callbacks in this section are specific to recorder only. This means that they will not fire at all for the player embeds.

The callbacks are listed in the order that they should appear in within your code.

- Note: Some callbacks might not be called. For example if video is uploaded and not recorded, recording specific callbacks will never fire.

**Permissions are given**

Gets triggered when someone gives OK for our system to use camera, microphone and file storage.

```
void accessGranted();
```

**Permissions are not given**

Some permissions are not given, so we can not do much at this point.

- Note: `permissions` parameter will share a list of permissions that were not granted.

```
void accessForbidden(@NonNull List<String> permissions);
```

**Camera is available**

Sometimes you might want to know that there is/are camera(s) available. This callback will fire when even one camera is available.

```
void hasCamera();
```

**Camera unavailable**

Most often the mobile device will have camera, however there are cases when camera can be disconnected, or it is otherwise unavailable. In such case, this event will fire.

- Note: Our SDK checks `{@link android.content.pm.PackageManager.FEATURE_CAMERA}` and the number of the available cameras

```
void noCamera();
```

**Microphone is available**

Most devices will have microphone available. It could however happen that it is not available, or that it is completely disconnected. This event will fire once we find any microphone.

```
void hasMicrophone();
```

**Microphone unavailable**

In most cases, the event above will be raised. In some specific cases, the microphone might not be available, at which time this event will be raised instead.

- Note: Our code will check `{@link android.content.pm.PackageManager.FEATURE_MICROPHONE}`

```
void noMicrophone();
```

**Ready to record**

In most cases, once permissions are given, the recording can start and as such this callback will fire. It means that camera is ready and that all permissions are granted. All that is left is to start recording.

```
void readyToRecord();
```

**Countdown to recording**

If you want to know when the countdown is shown, this event will be useful. It will be raised during countdown and right before the `recordingStarted` event.

- Note: The timeLeft will provide you with the seconds as they are shown on screen.

```
void countdown(int timeLeft);
```

**Recording has started**

This event fires once recording has just started. This is useful if you want to know that the video was recording and not upload since upload events will fire for all.

It can also be useful if you are using embedded recorder and you want to stop all other activities and bring more focus to the capture.

```
void recordingStarted();
```

**Recording in progress**

This event is raised when recording is in process. This is a continuous update notification that will fire through entire duration of recording process.

- Note: `time` parameter will let you know how much time has passed since the recording had started.

```
void recordingProgress(long time);
```

**Recording cancelled**

Want to detect if someone cancels the recording? Use this event to know when someone cancelled the recording and closed the screen.

```
void canceledByUser();
```

**Recording Finished**

This event will be raised when recording had just finished. It will happen in cases when the end user clicks on Stop button as well as if there was duration or size limit that was reached.

```
void recordingStopped(@NonNull String path);
```

**Confirm Recording**

Need to make sure someone confirms the video submission? Use this callback and record its action on your side as you like.

As this might be a requirement in some countries you are utilizing your app, you can easily use this for any sort of confirmation of captured video.

- Note: Our code only fires this event. It is up to you to then use this event to capture and save that someone confirmed the use of the video and in what way. This is done so as it offers you most flexibility in what you want to do and how.
- Note: This will only be fired if you set the `sendImmediately` to false. You can see more about it in the [Recorder Config](#recorder-config) section above.

```
void manuallySubmitted();
```

**Uploading started**

Want to know when upload starts? In that case you will want to listen to this event. It will be raised every time uploads start to happen.

```
void uploadingStarted(@NonNull String videoToken);
```

**Upload progress**

Do you want to know the progress of the uploads? This event will be continuously raised as the uploaded data changes, allowing you to track the progress of every upload.  

```
void uploadProgress(@NonNull String videoToken, @NonNull File file, long uploaded, long total);
```

**Upload finished**

Want to know once upload finishes? Then you would want to listen to this event. Our SDK will raise it once all uploading is complete.

```
void uploaded(@NonNull String path, @NonNull String token);
```

**Media Verified**

Do you want to know if the media just uploaded can be processed? In most cases this is true, however in rare cases, it might not be possible.

This utilizes our quick check algorithm to inspect the media before it is sent to processing to see that it can actually be processed. This allows you to react if something is wrong with the media, before the processing stages. It also offers you a way to skip the processing stages, since once verified client side code can do anything, even if not related to the media.

```
void verified(@NonNull String token);
```

**Processing**

While we do not offer an insight into how much of the media was processed, we can tell you how long it is going for. This event will be raised for the entire duration of media processing.

```
void processing(@NonNull String token);
```

**Processing Finished**

Interested in knowing when the media is successfully processed? Listening for this event will allow you to know just that. As soon as it fires, the media is available for playback

```
void processed(@NonNull String token);
```

#### Player Callbacks<a name="callbacks-player"></a>

**Media playback available**

Want to know once the player can play the video? This event will let you know once the media is available for playback. By listening to it, you can avoid listening to progress events as it will fire once the media is ready regardless if it has to be processed first, or if it is waiting to download the media to make it available for playback

```
void readyToPlay();
```

**Playback started**

Want to react when playback is started? This event will be raised every time the playback is started.

```
void playing();
```

**Playback paused**

What to react when someone pause's the video?. This event will be raised when the Pause button is clicked.

- Note: It will also fire at the end of the video

```
void paused();
```

**Playback Ended**

Want to know when the media playback ends? This event will be raised any time the playback reaches the end of media length.

```
void ended();
```

**Playback seeking**

Want to know if and where to someone changes the point of playback (seeks the media)? This event will be raised when the person watching the media moves the player's progress indicator to a new position. This will fire for both going forward as well as going back in playback.

- Note: The value returned will be milliseconds of time when seek operation was set to, looking from the start.

```
void seek(long millis);
```

#### Sensor Callbacks<a name="callbacks-sensor"></a>

```
ziggeo.setSensorCallback(@Nullable SensorManager.Callback callback);
```

**Lightning conditions**

Want to know lightning conditions? Our light sensor even will be raised every second to provide you with a value of how light / dark the environment seems.

- Note: Our SDK is checking {@link Sensor.TYPE_LIGHT} value

```
void lightSensorLevel(float level);
```

**Microphone levels**

Are you interested in knowing microphone health status? This event will be raised every second with the information about the recorder amplitude.

- Note: The returned values can be `GOOD`, `MODERATE` or `BAD`

```
void microphoneHealth(@NonNull MicSoundLevel level);
```

### API<a name="api"></a>

Our API is split into unique segments. The main one is videos. This deals with the video and videos as a whole.

Now as each video can have streams (sub videos) we also have an API that can deal with each stream as well.

For example, removing a video will remove all of its streams. On the other hand when removing a single stream, the rest of streams and the video itself will stay available. Of course, except that one stream.

Ziggeo also has APIs for other nodes, however there is a difference in API features available for client vs server side SDKs. The client side SDK calls, are safe to be called from app, while server side SDKs and their calls should only be used on server side and then passed over to your app.

If you have any questions about the specifics, do reach out to our [support team](mailto:suppport@ziggeo.com)

#### Request Cancellation<a name="api-cancel"></a>

API calls are usually made for specific action, however often, this is driven by the end user selection and they might at times change their mind. To be able to do that, you need to be able to cancel the request.

A good example of such case is if your customer realizes that they have selected a wrong video to upload.

Every service call returns a `Call` object, allowing you to cancel the request execution. You can do that by invoking the `cancel` method.

For example:

```
Call call = mVideoService.create(...);
call.cancel();
```

- Note: you can also cancel the last active request for recorder using `ziggeo.cancel()`

#### Videos API<a name="api-videos"></a>

**Find videos**

A way to find the videos based on your query and show them off. By default it is 50, however it can return back up to 100 videos at a time. Pagination control is also present.

- Note: For each call the videos will be returned from the newest first (by default).

```
/**
  * @param argsMap  - limit: Limit the number of returned videos. Can be set up to 100.
  *                 - skip: Skip the first [n] entries.
  *                 - reverse: Reverse the order in which videos are returned.
  *                 - states: Filter videos by state
  *                 - tags: Filter the search result to certain tags
  * @param callback - - callback to receive action result
  */
ziggeo.videos().index(HashMap<String, String> argsMap, Callback callback);
```

**Get video info**

A way for you to get info about the specific video, utilizing its video token or key.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param keyOrToken - video token or key.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().get(String keyOrToken, Callback callback);
```

**Download video**

Want to save the video on your device? This is the way to download it. All you need is to specify the video token or key.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param keyOrToken - video token or key.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().downloadVideo(@NonNull String keyOrToken, @NonNull Callback callback);
```

**Download image/snapshot**

If you would like to download the poster / image of the video, you can do that by specifying the video token or key.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param keyOrToken - video token or key.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().downloadImage(@NonNull String keyOrToken, @NonNull Callback callback);
```

**Create a video**

If you have video uploading code that you can not remove right away, however want to utilize Ziggeo, you could make a call to the video create API instead. That way your existing codes are used to create a new video in your Ziggeo app.

- Note: When setting a key, you do not need to include underscore as prefix. Even if you do, you will still need to include one in other calls.

```
/**
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

**Update a video**

Sometimes you might want to update the video with some tags, key or some other value that makes sense for your workflows. To do that, you would use the following call with either video token or key.

- Note: Keys used as video reference have to start with underscore, regardless of if they have it in their name already or not. If you are setting the key in arguments, that does not need underscore prefix.

```
/**
  * @param keyOrToken - video token or key.
  * @param argsMap    - min_duration: Minimal duration of video
  *                   - max_duration: Maximal duration of video
  *                   - tags: Video Tags
  *                   - key: Unique (optional) name of video
  *                   - volatile: Automatically removed this video if it remains empty
  *                   - expiration_days: After how many days will this video be deleted
  * @param callback   - callback to receive action result
  */
ziggeo.videos().update(String keyOrToken, HashMap<String, String> argsMap, Callback callback);
```

**Delete the video**

Use your video token or key to permanently remove the video from your app.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param keyOrToken - video token or key.
  * @param callback   - callback to receive action result
  */
ziggeo.videos().destroy(String keyOrToken, Callback callback);
```

#### Video Streams API<a name="api-video-streams"></a>

Streams are sub sections of each higher category. In this specific case, these are streams (or sub sections) of videos. Since each video can have different number of streams we might want to do something with them.

Example of different streams could be:
1. Your original stream (original and unmodified data that we got)
2. Default stream (stream after processing original stream)
3. Alternate resolutions
4. Streams with effects (for example with logo applied)
5. Variations of above

**Create new video stream**

It is possible to create the empty placeholder for the new data. It takes the video token or key under which the new stream should be created.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param videoTokenOrKey - video for which stream will be created
  * @param callback        - callback to receive action result
  */
ziggeo.streams().create(String videoTokenOrKey, Callback callback);
```

It is also possible (and recommended) to create the video and upload the data right away

```
/**
  * Create a new stream
  *
  * @param videoTokenOrKey - video for which stream will be created
  * @param videoFile       - Video file to be uploaded
  * @param callback        - callback to receive action result
  */
ziggeo.streams().create(@NonNull String videoTokenOrKey, @NonNull File videoFile, @Nullable HashMap<String, String> argsMap, @NonNull Callback callback);
```

**Attach image**

If you want to change the image/snapshot/poster of some stream, this would allow you to do it. You would do it by specifying the video token or key and the stream token.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param imageFile        - file to attach
  * @param callback         - callback to receive action result or handler progress
  */
ziggeo.streams().attachImage(videoTokenOrKey, String streamTokenOrKey, File imageFile, Callback callback);
```

**Attach video**

```
/**
  * Attaches a video to a new stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to attach a file
  * @param videoFile        - file to attach
  * @param callback         - callback to receive action result or handle progress
  */
ziggeo.streams().attachVideo(String videoTokenOrKey, String streamTokenOrKey, File videoFile, Callback callback);
```

**Download video stream**

Downloads the video associated with the stream. Make sure to specify the video token or key of the video as well as the stream token that you want to download.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream you want to download
  * @param callback         - callback to receive action result
  */
ziggeo.streams().downloadVideo(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);
```

**Download image**

Downloads the image that is associated with the stream by specifying the video token or key and the stream token.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * Download the image data associated with the stream
  *
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to download image from
  * @param callback         - callback to receive action result
  */
ziggeo.streams().downloadImage(@NonNull String videoTokenOrKey, @NonNull String streamTokenOrKey, @NonNull Callback callback);
```

**Get video stream info**

Sometimes you might want to grab data from specific stream instead of getting entire video data. In that case, use the video token or key and the stream token to grab it.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.

```
/**
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to retrieve data from
  * @param callback         - callback to receive action result
  */
ziggeo.streams().get(String videoTokenOrKey, String streamTokenOrKey, Callback callback);
```

**Delete the stream**

Using video token or key and the stream token you can remove any specific stream that you like.

- Note: Keys have to start with underscore, regardless of if they have it in their name already or not.
- Note: You will need to have [Authentication token](#authentication) (by default) for this request. Never hardcode the auth token into your app, it should always be retrieved as unique value from your server. For more details, please reach out to our [support team](mailto:support@ziggeo.com).

```
/**
  * @param videoTokenOrKey  - video token
  * @param streamTokenOrKey - stream to remove
  * @param callback         - callback to receive action result
  */
ziggeo.streams().delete(String videoTokenOrKey, String streamTokenOrKey, Callback callback);
```

### Authentication<a name="authentication"></a>

Our API is utilizing patent pending authorization token system. It allows you to secure and fine tune what someone can do and for how long.

The following will be needed if you have enabled the authorization tokens in your app.

- Note: This shows you how to add and utilize auth tokens. On client side, the auth tokens should never be created, nor stored permanently. Ideally, you will create the auth tokens within your server and then if you decide, pass that token to the specific client to allow them to do something within certain timeframe. Hardcoding permanent auth tokens would make it possible for anyone to find them and use, regardless of your desired workflow just by inspecting your app code.

Both client and server side auth tokens have equal say in what one can do. The difference is in how they are created.

#### Client Auth<a name="authentication-client"></a>

This section helps you set up a client auth token to be used in the requests you send to our servers. The client auth is created on your server without reaching to our servers first. This is ideal for high speed communication.

```
/**
  * @param authData - authData which will be used for authentication in requests
  */
ziggeo.setClientAuthToken(@NonNull String authData);
```

#### Server Auth<a name="authentication-server"></a>

The following will help you utilize the server side auth tokens. The server side auth tokens are created on your server as well, however they are created by passing the grants object to our server. Our server then sends you a short token that you can use in any of the calls you make, per the grants you specified.

```
/**
  * @param token - token which will be used for authentication in requests
  */
ziggeo.setServerAuthToken(@NonNull String token);
```

## Compiling and Publishing App<a name="compile"></a>

All the standard compiling and publishing steps apply. For more details please see [Android Studio docs](https://developer.android.com/studio/run).

## Update Information<a name="update"></a>

Version 2.0.0 and up contains several broken changes, e.g., AndroidX migration, some methods renamed.
You will need to inspect the Changelog if you are switching from older versions.

## Changelog<a name="Changelog"></a>

If you are interested in our changelog you can find it as a separate file next to this readme. It is named as [CHANGELOG.md](CHANGELOG.md)
