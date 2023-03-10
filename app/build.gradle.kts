import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

// TODO: Buat sebuah file dengan nama apikey.properties pada project root yang berisi NEWS_API_KEY
val apikeyPropertiesFile by lazy { rootProject.file("apikey.properties") }
val apikeyProperties by lazy { Properties() }
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    compileSdk = AppInfo.compileSdkVersion

    defaultConfig {
        applicationId = AppInfo.applicationId
        minSdk = AppInfo.minSdkVersion
        targetSdk = AppInfo.targetSdkVersion
        versionCode = AppInfo.versionCode
        versionName = AppInfo.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // TODO: define BASE_URL and NEWS_API_KEY here
        buildConfigField("String", "NEWS_API_KEY", apikeyProperties.getProperty("NEWS_API_KEY"))
        buildConfigField("String", "BASE_URL", apikeyProperties.getProperty("BASE_URL"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        targetCompatibility(JavaVersion.VERSION_11)
        sourceCompatibility(JavaVersion.VERSION_11)
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Deps.kotlin)
    implementation(Deps.kotlinReflect)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)
    implementation(Deps.legacySupport)
    implementation(Deps.browserHelper)

    // SquareUp
    implementation(platform(Deps.SquareUp.okhttpBOM))
    implementation(Deps.SquareUp.okhttp3)
    implementation(Deps.SquareUp.okhttp3Logging)
    implementation(Deps.SquareUp.retrofit)
    implementation(Deps.SquareUp.retrofitMoshi)
    implementation(Deps.SquareUp.moshi)
    implementation(Deps.SquareUp.retrofitGson)

    // KOIN
    implementation(Deps.Koin.core)
    implementation(Deps.Koin.android)

    // LIFECYCLE
    implementation(Deps.lifecycleLiveData)
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)
    testImplementation(Deps.archCoreTesting)
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)

    // KOTLIN COROUTINES
    implementation(Deps.KotlinX.coroutineCore)
    implementation(Deps.KotlinX.coroutineAndroid)

    // ROOM
    implementation(Deps.Room.runtime)
    kapt(Kapt.roomCompiler)
    implementation(Deps.Room.ktx)

    // TESTING
    testImplementation(Deps.Testing.jUnit)
    testImplementation(Deps.Testing.mockito)

    //Paging
    implementation(Deps.paging)
    implementation(Deps.Room.roomPaging)

    //Hilt
    implementation(Deps.DaggerHilt.hilt)
    kapt(Kapt.hiltCompiler)

    //Glide
    implementation(Deps.glide)
}