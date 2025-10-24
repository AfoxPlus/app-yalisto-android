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

val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
allprojects {
    configurations.all {
        resolutionStrategy {
            dependencySubstitution {
                substitute(module(catalog.findLibrary("yalisto.uikit").get().get().toString()))
                    .using(project(ModuleDependency.Core.DESIGN_SYSTEM))
                substitute(module(catalog.findLibrary("yalisto.network").get().get().toString()))
                    .using(project(ModuleDependency.Integration.NETWORK))
                substitute(module(catalog.findLibrary("yalisto.products").get().get().toString()))
                    .using(project(ModuleDependency.Feature.PRODUCTS))
                substitute(module(catalog.findLibrary("yalisto.restaurants").get().get().toString()))
                    .using(project(ModuleDependency.Feature.RESTAURANTS))
                substitute(module(catalog.findLibrary("yalisto.orders").get().get().toString()))
                    .using(project(ModuleDependency.Feature.ORDERS))
                substitute(module(catalog.findLibrary("yalisto.home").get().get().toString()))
                    .using(project(ModuleDependency.Feature.HOME))
                substitute(module(catalog.findLibrary("yalisto.places").get().get().toString()))
                    .using(project(ModuleDependency.Feature.PLACES))
                substitute(module(catalog.findLibrary("yalisto.bdui").get().get().toString()))
                    .using(project(ModuleDependency.Integration.BDUI))
            }
        }
    }
}