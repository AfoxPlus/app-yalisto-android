pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://maven.pkg.github.com/afoxplus/app-yalisto-android")
            credentials {
                username = extra.has("REPO_USERID_AFOXPLUS").let {
                    if (it)
                        extra.get("REPO_USERID_AFOXPLUS") as String else System.getenv("REPO_USERID")
                }
                password = extra.has("REPO_TOKEN_AFOXPLUS").let {
                    if (it)
                        extra.get("REPO_TOKEN_AFOXPLUS") as String else System.getenv("REPO_TOKEN")
                }
            }
        }
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.vanniktech:gradle-dependency-graph-generator-plugin:0.8.0")
    }
}

rootProject.name = "app-yalisto-android"
include(":app")

/*
include("uikit")
project(":uikit").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-uikit/module")

include("network")
project(":network").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-network/module")

include("products")
project(":products").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-products/module")

include("restaurants")
project(":restaurants").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-restaurants/module")

include("orders")
project(":orders").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-orders/module")

include("home")
project(":home").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-home/module")

include("places")
project(":places").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-places/module")

include("bdui")
project(":bdui").projectDir =
    File("${settings.rootProject.projectDir.parent}/app-android-bdui/module")
*/
