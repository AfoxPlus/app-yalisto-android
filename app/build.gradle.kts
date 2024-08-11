plugins {
    id("com.android.application") version "8.0.2"
    id("org.jetbrains.kotlin.android") version "1.8.22"
    id("org.jetbrains.kotlin.kapt") version "1.8.22"
    id("com.google.dagger.hilt.android") version "2.44.2"
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.7.20"
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
    compileSdk = Versions.compileSdkVersion

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        versionCode = ConfigureApp.versionCode
        versionName = ConfigureApp.versionName
        testInstrumentationRunner = Versions.testInstrumentationRunner
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
        kotlinCompilerExtensionVersion = "1.4.8"
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
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Deps.Jetpack.kotlin)
    implementation(Deps.Jetpack.core)
    implementation(Deps.Jetpack.appcompat)
    implementation(Deps.Jetpack.fragment)
    implementation(Deps.UI.materialDesign)
    implementation(Deps.UI.constraintLayout)

    // JetpackCompose
    implementation(Deps.JetpackCompose.activity)
    implementation(Deps.JetpackCompose.constraintlayout)
    implementation(Deps.JetpackCompose.navigation)
    implementation(platform(Deps.JetpackCompose.bom))
    implementation(Deps.JetpackCompose.ui)
    implementation(Deps.JetpackCompose.graphics)
    implementation(Deps.JetpackCompose.toolingPreview)
    debugImplementation(Deps.JetpackCompose.tooling)
    implementation(Deps.JetpackCompose.material3)
    implementation(Deps.JetpackCompose.materialIconExtended)
    implementation(Deps.JetpackCompose.coilCompose)
    implementation(Deps.JetpackCompose.hiltNavigationCompose)

    // External Libraries
    implementation(Deps.Arch.hiltAndroid)
    kapt(Deps.Arch.hiltCompiler)
    implementation(Deps.Arch.coroutinesCore)
    implementation(Deps.Arch.retrofit2)
    implementation(Deps.Arch.gson)
    implementation(Deps.Arch.loggingInterceptor)
    implementation(Deps.UI.glide)
    kapt(Deps.UI.glideCompiler)
    implementation(Deps.Arch.zxingAndroid) { isTransitive = false }
    implementation(Deps.Arch.zxingCore)

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
    implementation(Deps.Arch.invitations)
}