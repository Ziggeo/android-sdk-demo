plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.android.extensions")
}

val buildUid = System.getenv("BUILD_COMMIT_SHA") ?: "local"
android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.ziggeo.androidsdk.demo"

        minSdkVersion(16)
        targetSdkVersion(29)

        versionName = "1.0.0"
        versionCode = 1

        buildToolsVersion = "29.0.2"

        defaultConfig {
            multiDexEnabled = true
            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
                )
            }
        }
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    //Support
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${extra["kotlinVersion"] as String}")
    //Log
    implementation("com.jakewharton.timber:timber:4.7.1")
    //MVP Moxy
    val moxyVersion = "1.7.0"
    kapt("tech.schoolhelper:moxy-x-compiler:$moxyVersion")
    implementation("tech.schoolhelper:moxy-x:$moxyVersion")
    implementation("tech.schoolhelper:moxy-x-androidx:$moxyVersion")
    //Cicerone Navigation
    implementation("ru.terrakok.cicerone:cicerone:5.0.0")
    //DI
    val toothpickVersion = "3.0.2"
    implementation("com.github.stephanenicolas.toothpick:toothpick-runtime:$toothpickVersion")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion")
    //Image load and cache
    val glideVersion = "4.9.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")
    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.7")
    implementation("com.jakewharton.rxrelay2:rxrelay:2.1.0")
    //Gson
    implementation("com.google.code.gson:gson:2.8.5")
    //JUnit
    testImplementation("junit:junit:4.12")
    //Mockito
    testImplementation("org.mockito:mockito-core:3.0.0")
    //Mockito Kotlin
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    //Espresso
    androidTestImplementation("com.agoda.kakao:kakao:2.1.0")
    androidTestImplementation("androidx.annotation:annotation:1.1.0")
    val rulesAndRunner = "1.2.0"
    androidTestImplementation("androidx.test:runner:$rulesAndRunner")
    androidTestImplementation("androidx.test:rules:$rulesAndRunner")
    val espresso = "3.2.0"
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espresso")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso")
    androidTestImplementation("androidx.test.espresso:espresso-web:$espresso")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$espresso")
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espresso")
}

configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:${extra["kotlinVersion"] as String}")
    }
}

gradle.buildFinished {
    println("VersionName: ${android.defaultConfig.versionName}")
    println("VersionCode: ${android.defaultConfig.versionCode}")
    println("BuildUid: $buildUid")
}