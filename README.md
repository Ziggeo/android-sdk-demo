# Android-SDK
### How to sdk:
Put **sdk.aar** in **libs** folder.
Press File->New->New Module and select Import .JAR/.AAR Package.
Add dependencies to your build.gradle file
```
compile 'cz.msebera.android:httpclient:4.4.1.1'
compile 'com.google.guava:guava:18.0'
```

### How to use video recorder:

```java
Ziggeo ziggeo = new Ziggeo(APP_TOKEN);
long maxVideoDutaion = 1000 * 60 * 5;//for ex. 5 mins.
ziggeo.createVideo(this, maxVideoDutaion);
```

### How to use video player:
```java
String path = ...; // path to file or stream url
Fragment videoPlayerFragment = VideoPlayerFragment.getInstance(path);
getFragmentManager().beginTransaction().replace(android.R.id.content, videoPlayerFragment).commit();
```
