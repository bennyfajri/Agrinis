// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(BuildPlugins.serialization)
    }
}

plugins {
    id("com.android.application") version("7.2.2") apply false
    id("com.android.library") version("7.2.2") apply false
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.kotlin
}