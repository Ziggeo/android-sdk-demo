# Android-SDK
### How to include the sdk:
Put **ziggeo-sdk-v*.aar** in **libs** folder for your project.
In Android Studio choose File->New->New Module and select Import .JAR/.AAR Package.
Add the following dependencies to your build.gradle file
```
        compile(name:'ziggeo-sdk-v0.0.1', ext:'aar')
        compile 'cz.msebera.android:httpclient:4.4.1.1'
        compile 'com.google.guava:guava:18.0'
        compile 'com.danikula:videocache:2.3.2'
```

## Fullscreen video recorder:

```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
long maxVideoDutaion = 1000 * 60 * 5; //for ex. 5 mins.
```
##### Without extra args
```java
/**
  * Use this for launch standalone activity with video recorder and player.
  * With standalone recorder can be used {@link com.ziggeo.androidsdk.eventbus.events.CloseRecorderEvent}
  *
  * @param context             - context
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  */
ziggeo.createVideo(this, maxVideoDutaion);
```
##### With extra args
```java
ArrayList<BasicNameValuePair> extraArgs = ...;

/**
  * Use this for launch standalone activity with video recorder and player.
  * With standalone recorder can be used {@link com.ziggeo.androidsdk.eventbus.events.CloseRecorderEvent}
  *
  * @param context             - context
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  * @param extraArgs           - extra arguments for create video request.
  */
ziggeo.createVideo(this, maxDurationInMillis, extraArgs)
```


## Embedded video recorder:

```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
long maxVideoDutaion = 1000 * 60 * 5; //for ex. 5 mins.
```
##### Without extra args
```java
/**
  * Use this for launch embedded recorder
  *
  * @param manager             - {@link FragmentManager}
  * @param contentId           - Identifier of the container this fragment is to be placed in.
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  */
ziggeo.attachRecorder(getFragmentManager(), R.id._your_id_here_, maxDuration);
```
##### With extra args
```java
ArrayList<BasicNameValuePair> extraArgs = ...;

 /**
  * Use this for launch embedded recorder
  *
  * @param manager             - {@link FragmentManager}
  * @param contentId           - Identifier of the container this fragment is to be placed in.
  * @param maxDurationInMillis - allowed max video duration in milliseconds.
  * @param extraArgs           - extra arguments for create video request.
  */
ziggeo.attachRecorder(getFragmentManager(), R.id._your_id_here_, maxDuration, extraArgs);
```

## Video player:
```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
Uri path = ...; // path to file or stream url
// or
String token = ...; // video token
```
##### Without extra args
```java
/**
  * Use this for launch embedded player
  *
  * @param manager   - {@link FragmentManager}
  * @param contentId - Identifier of the container this fragment is to be placed in.
  * @param path      - {@link Uri} path to file.
  */
ziggeo.attachPlayer(manager, contentId, path);
```

```java
 /**
  * Use this for launch player
  *
  * @param manager   - {@link FragmentManager}
  * @param contentId - Identifier of the container this fragment is to be placed in.
  * @param token     - video token.
  */
ziggeo.attachPlayer(manager, contentId, token);
```

##### With extra args
```java
ArrayList<BasicNameValuePair> extraArgs = ...;

/**
  * Use this for launch player
  *
  * @param manager   - {@link FragmentManager}
  * @param contentId - Identifier of the container this fragment is to be placed in.
  * @param token     - video token.
  * @param extraArgs - extra args to append to the playback stream url.
  */
ziggeo.attachPlayer(manager, contentId, token, extraArgs);
