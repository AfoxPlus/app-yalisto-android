plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.plugin.parcelize) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.gms.google.services) apply false
    alias(libs.plugins.jlleitschuh.gradle.ktlint) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
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
}*/