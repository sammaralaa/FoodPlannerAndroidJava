// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id ("androidx.navigation.safeargs") version "2.7.7" apply false
}
buildscript{
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.google.gms:google-services:4.4.2")

    }
}