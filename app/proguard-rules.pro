# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn retrofit2.**
-dontwarn org.junit.**
-dontwarn okhttp3.**
-dontwarn dagger.**
-dontwarn android.test.**

-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection

-keep class com.squareup.okhttp3.** { *; }

-keep class com.afoxplus.orders.repositories.** { <fields>; }
-keep class com.afoxplus.orders.entities.** { <fields>; }

-keep class com.afoxplus.products.repositories.** { <fields>; }
-keep class com.afoxplus.products.entities.** { <fields>; }

-keep class com.afoxplus.restaurants.repositories.** { <fields>; }
-keep class com.afoxplus.restaurants.entities.** { <fields>; }

