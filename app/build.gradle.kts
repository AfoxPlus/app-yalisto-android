plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.parcelize)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jlleitschuh.gradle.ktlint)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.devtools.ksp)
    id("org.sonarqube") version "3.3"
    id("jacoco")
}

apply {
    from("sonarqube.gradle")
    from("jacoco.gradle")
    from("graph.gradle.kts")
}

android {
    namespace = ConfigureApp.applicationId
    compileSdk = ConfigureApp.Versions.compileSdkVersion

    defaultConfig {
        applicationId = ConfigureApp.applicationId
        minSdk = ConfigureApp.Versions.minSdkVersion
        targetSdk = ConfigureApp.Versions.targetSdkVersion
        versionCode = ConfigureApp.versionCode
        versionName = ConfigureApp.versionName
        testInstrumentationRunner = ConfigureApp.Versions.testInstrumentationRunner
        renderscriptSupportModeEnabled = true
        vectorDrawables.useSupportLibrary = true
    }

    testOptions {
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = findProperty("SIGNING_KEY_ALIAS_YALISTO") as String?
                ?: System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = findProperty("SIGNING_KEY_PASSWORD_YALISTO") as String?
                ?: System.getenv("SIGNING_KEY_PASSWORD")
            storeFile = file("../.signing/release-yalisto-key.jks")
            storePassword = findProperty("SIGNING_STORE_PASSWORD_YALISTO") as String?
                ?: System.getenv("SIGNING_STORE_PASSWORD")
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("staging") {
            initWith(getByName("debug"))
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }

    bundle {
        language {
            enableSplit = true
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    lint {
        disable.addAll(
            listOf(
                "TypographyFractions",
                "TypographyQuotes",
                "JvmStaticProvidesInObjectDetector",
                "FieldSiteTargetOnQualifierAnnotation",
                "ModuleCompanionObjects",
                "ModuleCompanionObjectsNotInModuleParent"
            )
        )
        checkDependencies = true
        abortOnError = false
        ignoreWarnings = false
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    //Jetpack
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlin.reflect)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Jetpack Compose
    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material.icons.extended)
    //Image Async
    implementation(libs.coil.compose)
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Lifecycle Scopes
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.androidx.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Map
    implementation(Deps.Arch.map)
    implementation(Deps.Arch.mapCompose)
    implementation(Deps.Arch.mapLocation)

    //Scan
    implementation(Deps.Arch.zxingAndroid) { isTransitive = false }
    implementation(Deps.Arch.zxingCore)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging.ktx)

    //DataStore
    implementation(Deps.Arch.dataStore)
    implementation(Deps.Arch.dataStoreCore)

    // Chucker
    debugImplementation(Deps.Arch.chucker)
    "stagingImplementation"(Deps.Arch.chucker)
    releaseImplementation(Deps.Arch.chuckerNoOp)

    // Test
    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.testCore)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.mockitoKotlin)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)

    // Business Dependencies
    implementation(Deps.UI.uikit)
    implementation(Deps.Arch.network)
    implementation(Deps.Arch.products)
    implementation(Deps.Arch.restaurants)
    implementation(Deps.Arch.orders)
    implementation(Deps.Arch.home)
    implementation(Deps.Arch.places)
    implementation(Deps.Arch.bdui)
}