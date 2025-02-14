# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# General
#-printconfiguration proguard-merged-config.txt
#-dontoptimize #Specifies not to optimize the input class files.
-dontusemixedcaseclassnames #Specifies not to generate mixed-case class names while obfuscating.
-dontskipnonpubliclibraryclasses #Specifies not to ignore non-public library classes. As of version 4.5, this is the default setting
-optimizationpasses 5 #Specifies the number of optimization passes to be performed. By default, a single pass is performed.
-verbose #Specifies to write out some more information during processing
-keepattributes SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,Signature,Exceptions,InnerClasses,AnnotationDefault,RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations #Specifies any optional attributes to be preserved.
-keep,allowshrinking class * implements java.io.Serializable {*;} # affects classes that implement serializable
-keep,allowshrinking class * implements android.os.Parcelable {*;} # affects classes that implement parcelable
#---------------------------------------------------------

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

# Retrofit
-dontwarn retrofit2.**
-dontwarn org.junit.**
-dontwarn okhttp3.**
-dontwarn dagger.**
-dontwarn android.test.**
#---------------------------------------------------------

# Dagger
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class com.squareup.okhttp3.** { *; }
#---------------------------------------------------------

# Kotlin
-keepclassmembers,allowoptimization @kotlin.** class com.afoxplus.** {
    <fields>;
}
-keep class kotlin.coroutines.Continuation
#---------------------------------------------------------


# Compose
-keep class androidx.compose.** { *; }
-keep class androidx.compose.foundation.lazy.** { *; }
#---------------------------------------------------------

