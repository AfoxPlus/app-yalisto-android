plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

apply(from = "sonarqube.gradle")
apply(from = "jacoco.gradle")

android {
    compileSdk = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        applicationId = ConfigureApp.applicationId
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = ConfigureApp.versionCode
        versionName = ConfigureApp.version
        testInstrumentationRunner = Versions.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "EXAMPLE_FIELD", "\"example-release\"")
        }

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "EXAMPLE_FIELD", "\"example-debug\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions.jvmTarget = "${JavaVersion.VERSION_11}"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    lint {
        isCheckDependencies = true
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Deps.Jetpack.core)
    implementation(Deps.Jetpack.kotlin)
    implementation(Deps.Jetpack.activity)
    implementation(Deps.Jetpack.fragment)
    implementation(Deps.Jetpack.appcompat)

    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)
    implementation(Deps.UI.glide)
    kapt(Deps.UI.glideCompiler)
    implementation(Deps.UI.uikit)

    implementation(Deps.Arch.zxingAndroid) { isTransitive = false }
    implementation(Deps.Arch.zxingCore)
    implementation(Deps.Arch.network)
    implementation(Deps.Arch.retrofit2)
    implementation(Deps.Arch.gson)
    implementation(Deps.Arch.loggingInterceptor)
    implementation(Deps.Arch.coroutinesCore)
    implementation(Deps.Arch.hiltAndroid)
    kapt(Deps.Arch.hiltCompiler)

    implementation(Deps.Arch.home)
    implementation(Deps.Arch.products)
    implementation(Deps.Arch.restaurants)
    implementation(Deps.Arch.orders)

    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.testCore)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.mockitoKotlin)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)
}