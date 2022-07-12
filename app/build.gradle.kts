plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-android-extensions")
    id ("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.magistor8.translator"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Coroutines
    implementation (Dependencies.COROUTINES_CORE)
    implementation (Dependencies.COROUTINES_ANDROID)
    implementation (Dependencies.LIFECYCLE_VIEWMODEL)
    implementation (Dependencies.COROUTINES_RETROFIT_ADAPTER)
    //Retrofit
    implementation (Dependencies.RETROFIT)
    implementation (Dependencies.RETROFIT_GSON)
    //Room
    implementation (Dependencies.ROOM)
    kapt (Dependencies.ROOM_KAPT)
    //Koin
    implementation (Dependencies.KOIN)
    //Coil
    implementation (Dependencies.COIL)
    //Core
    implementation (Dependencies.CORE)
    implementation (Dependencies.APPCOMPAT)
    //Material
    implementation (Dependencies.MATERIAL)
    implementation (Dependencies.CONSTRAINT)
    //AppModule
    implementation(project(":Core"))
}