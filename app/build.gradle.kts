plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.androidproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //Room
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    //Gson
    implementation ("com.google.code.gson:gson:2.11.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //navigation componant
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")

    //rounded avatar
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //animation
    implementation ("com.airbnb.android:lottie:6.0.0")

    //firebase
    implementation ("com.google.firebase:firebase-auth:23.0.0")

    //youtube
    //implementation ("com.google.android.youtube:youtube-android-player-api:1.2.2")

    //implementation ("com.google.android.youtube:youtube-android-player-api:1.2.2")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
}
