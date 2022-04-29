plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt") version "1.19.0-RC2"
}

// apply {
//    from("$rootDir/compose-module.gradle")
// }

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = BuildConfig.appId
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha08"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
    }
}

dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)

    api(project(Module.appBase))
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation(AndroidXAppCompat.core_ktx)
    implementation(AndroidXAppCompat.app_compat)
    implementation(AndroidXAppCompat.swipe_refresh)
    implementation(AndroidXAppCompat.constraint_layout)
    implementation(AndroidXAppCompat.recycler_view)
    implementation(AndroidXAppCompat.recycler_selection)
    implementation(MaterialDesign.material)
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    testImplementation(AndroidJunit.junit)
    androidTestImplementation(AndroidJunit.junit_ext)
    androidTestImplementation(AndroidJunit.junit_espresso_core)

    implementation(AndroidArchNavigation.fragment_ktx)
    implementation(AndroidArchNavigation.ui_ktx)
    kapt(AndroidArchNavigation.safe_args_generator)

    // AndroidX work
    implementation(AndroidArchWork.work_runtime_ktx)
    // Inject
    implementation(Dagger.core)
    implementation(Dagger.android_core)
    implementation(Dagger.android_support)
    kapt(Dagger.compiler)
    implementation(CommonLibs.javaxInject)
    kapt(Dagger.android_processor)
    implementation(AssistedInject.annotations_dagger)
    kapt(AssistedInject.processor_dagger)

    kapt(AndroidArchLifeCycle.lifecycle_compiler)
    api(CommonLibs.timber)

    implementation(Coil.coil)
    implementation(Glide.runtime)

    implementation(CommonLibs.lottie_animation)
    implementation(AppStartup.runtime)
    debugImplementation(LeakCanary.android)
}
