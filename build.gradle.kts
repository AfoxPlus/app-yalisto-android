plugins {
    id("com.android.application") version Versions.androidApplicationVersion apply false
    id("com.android.library") version Versions.androidLibraryVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("com.google.dagger.hilt.android") version Versions.hiltVersion apply false
    id("org.jetbrains.kotlin.kapt") version Versions.kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version Versions.kotlinVersion apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}

/*
allprojects {
    configurations.all {
        resolutionStrategy {
            dependencySubstitution {
                substitute(module(Deps.UI.uikit)).using(project(":uikit"))
                substitute(module(Deps.Arch.network)).using(project(":network"))
                substitute(module(Deps.Arch.products)).using(project(":products"))
                substitute(module(Deps.Arch.restaurants)).using(project(":restaurants"))
                substitute(module(Deps.Arch.orders)).using(project(":orders"))
                substitute(module(Deps.Arch.home)).using(project(":home"))
                substitute(module(Deps.Arch.places)).using(project(":places"))
                substitute(module(Deps.Arch.bdui)).using(project(":bdui"))
            }
        }
    }
}
 */