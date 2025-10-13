object ConfigureApp {
    const val urlRepoDependencies = "https://maven.pkg.github.com/AfoxPlus/app-yalisto-android"
    const val organization = "afoxplus"
    const val projectName = "app-yalisto-android"
    const val applicationId = "com.afoxplus.yalisto"
    const val versionCode = 13
    const val versionName = "6.3.1"

    //Only for LocalModules
    const val groupId = "com.afoxplus.android"
    const val artifactId = "yalisto"

    object Versions {
        const val compileSdkVersion = 36
        const val minSdkVersion = 24
        const val targetSdkVersion = 36
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val kotlinCompilerExtensionVersion = "1.5.3"
        const val jdkVersion = 17
    }

    object Gradle {
        const val sonarqube =
            "https://drive.google.com/uc?export=download&id=1JTrnI8AoVkIgc1_uESGLSfQt5Oi9_Pjs"
        const val jacoco =
            "https://drive.google.com/uc?export=download&id=1IFjDqr-JL6xK8bVbrzKC1zRwUdCJdSui"
        const val uploadArtifact =
            "https://drive.google.com/uc?export=download&id=1n319i6WX86UF9v80aj0Mi0BEZttExR4m"
    }
}