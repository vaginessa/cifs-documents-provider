plugins {
    id(Deps.App.libraryPlugin)
    id(Deps.App.kotlinAndroidPlugin)
    id(Deps.App.kotlinKaptPlugin)
    id(Deps.App.daggerHiltPlugin)
    id(Deps.Ui.navigationSafeargsPlugin)
    id(Deps.Util.licensePlugin)
}

android {
    compileSdk = Deps.compileSdkVersion
    namespace = "${Deps.namespaceBase}.presentation"

    defaultConfig {
        minSdk = Deps.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Deps.javaVersionEnum
        targetCompatibility = Deps.javaVersionEnum
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(Deps.javaVersion))
        }
    }
    buildFeatures {
        dataBinding = true
    }

}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))

    // App

    implementation(Deps.App.core)
    implementation(Deps.App.appcompat)
    implementation(Deps.App.coroutine)
    implementation(Deps.App.daggerHilt)
    kapt(Deps.App.daggerHiltCompiler)

    // UI

    implementation(Deps.Ui.constraintLayout)
    implementation(Deps.Ui.material)
    implementation(Deps.Ui.activityKtx)
    implementation(Deps.Ui.fragmentKtx)
    // Lifecycle
    implementation(Deps.Ui.lifecycleViewModel )
    implementation(Deps.Ui.lifecycleRuntime)
    // Navigation
    implementation(Deps.Ui.navigationUi)
    implementation(Deps.Ui.navigationFragmentKtx)

    // Util

    // Paging
    implementation(Deps.Data.pagingKtx)

    // Localization
    implementation(Deps.Util.localization)
    // OSS License
    implementation(Deps.Util.license)

    // Test

    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Test.junitExt)
}