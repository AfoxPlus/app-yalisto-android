plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.parcelize)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jlleitschuh.gradle.ktlint)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    id("org.sonarqube") version "3.3"
    id("jacoco")
}

apply {
    from(Gradle.Sonarqube)
    from(Gradle.Jacoco)
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
    //Jetpack UI
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Jetpack Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose)

    //Image Async
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
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.maps.compose)

    //Scan
    implementation(libs.zxing.android.embedded) { isTransitive = false}
    implementation(libs.zxing.core)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging.ktx)

    //DataStore
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)

    // Chucker
    debugImplementation(libs.chucker.library)
    "stagingImplementation"(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

    // Test
    testImplementation(libs.bundles.unit.test)

    // Business Dependencies
    implementation(project(ModuleDependency.Core.DESIGN_SYSTEM))
    implementation(project(ModuleDependency.Integration.NETWORK))
    implementation(project(ModuleDependency.Integration.BDUI))
    implementation(project(ModuleDependency.Feature.PRODUCTS))
    implementation(project(ModuleDependency.Feature.RESTAURANTS))
    implementation(project(ModuleDependency.Feature.ORDERS))
    implementation(project(ModuleDependency.Feature.HOME))
    implementation(project(ModuleDependency.Feature.PLACES))

    //Compose Debug
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.tooling.preview)

}