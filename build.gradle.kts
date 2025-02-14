plugins {
    id("com.android.application") version Versions.androidApplicationVersion apply false
    id("com.android.library") version Versions.androidLibraryVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("com.google.dagger.hilt.android") version Versions.hiltVersion apply false
    id("org.jetbrains.kotlin.kapt") version Versions.kotlinKaptVersion apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version Versions.kotlinParcelizeVersion apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}

/*
allprojects {
    configurations.all {
        resolutionStrategy {
            dependencySubstitution {
                substitute(module(Deps.Arch.home)).using(project(":home"))
            }
        }
    }
}*/