plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}

/*
allprojects {
    configurations.all {
        resolutionStrategy {
            dependencySubstitution {
                substitute(module(Deps.Arch.home)).using(project(":home"))
                substitute(module(Deps.Arch.invitations)).using(project(":invitation"))
            }
        }
    }
}
*/