object BuildConfig {
    const val compileSdk = 30
    const val minSdk = 26
    const val targetSdk = 30

    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 1
    private const val versionBuild = 0

    const val versionName =
        "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode =
        versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild
}

object GradlePlugin{
    const val android_tool = "com.android.tools.build:gradle:7.0.3"
}

object Kotlin {
    private const val version = "1.5.31"
    const val stdblib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    const val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
}

object CommonLibs {
    const val android_gradle_plugin = "com.android.tools.build:gradle:4.2.2"
    const val dexter = "com.karumi:dexter:5.0.0"
    const val phrase = "com.squareup.phrase:phrase:1.1.0"
    const val sonar = "com.facebook.sonar:sonar:0.0.1"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val junit = "junit:junit:4.13"
    const val javaxInject = "javax.inject:javax.inject:1"
    const val rabbkt = "com.aungkyawpaing.rabbkt:rabbkt:1.0.1"

    const val easy_image = "com.github.jkwiecien:EasyImage:3.0.3"
    const val android_crop = "com.soundcloud.android:android-crop:1.0.1@aar"
    const val ucrop = "com.github.yalantis:ucrop:2.2.2"
    const val fastscroll = "com.simplecityapps:recyclerview-fastscroll:2.0.0"

    const val colorpicker = "com.github.QuadFlask:colorpicker:0.0.13"
    const val circle_image_view = "de.hdodenhof:circleimageview:3.0.0"
    const val shape_of_views = "com.github.florent37:shapeofview:1.4.7"

    const val material_stepper = "com.stepstone.stepper:material-stepper:4.3.1"
    const val lottie_animation = "com.airbnb.android:lottie:3.4.0"
}

object AndroidXAppCompat {
    const val core_ktx= "androidx.core:core-ktx:1.6.0"
    const val app_compat = "androidx.appcompat:appcompat:1.3.1"
    const val swipe_refresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val recycler_view = "androidx.recyclerview:recyclerview:1.2.1"
    const val recycler_selection = "androidx.recyclerview:recyclerview-selection:1.1.0"
}

object AndroidXRecyclerView {
    private const val version = "1.2.1"

    const val recycler_view = "androidx.recyclerview:recyclerview:$version"
    const val selection = "androidx.recyclerview:recyclerview-selection:$version"
}

object AndroidXActivity {
    private const val version = "1.4.0"

    const val activity = "androidx.activity:activity:$version"
    const val activity_ktx = "androidx.activity:activity-ktx:$version"
}

object AndroidXCore {
    private const val version = "1.6.0"

    const val core = "androidx.core:core:$version"
    const val core_ktx = "androidx.core:core-ktx:$version"
}

object AndroidXFragment {
    private const val version = "1.3.6"

    const val fragment = "androidx.fragment:fragment:$version"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:$version"
    const val fragment_testing = "androidx.fragment:fragment-testing:$version"
}


object MaterialDesign {
    const val material = "com.google.android.material:material:1.4.0"
}

object AndroidJunit{
    const val junit = "junit:junit:4.13.2"
    const val junit_ext = "androidx.test.ext:junit:1.1.3"
    const val junit_espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
}

object AndroidArchLifeCycle {
    private const val lifecycle_version = "2.4.0-rc01"
    const val common_java= "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
}

object AndroidArchNavigation {
    private const val version = "2.3.5"

    const val common = "androidx.navigation:navigation-common:$version"
    const val common_ktx = "androidx.navigation:navigation-common-ktx:$version"
    const val fragment = "androidx.navigation:navigation-fragment:$version"
    const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:$version"
    const val runtime = "androidx.navigation:navigation-runtime:$version"
    const val runtime_ktx = "androidx.navigation:navigation-runtime-ktx:$version"
    const val safe_args_generator = "androidx.navigation:navigation-safe-args-generator:$version"
    const val safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    const val testing = "androidx.navigation:navigation-testing:$version"
    const val testing_ktx = "androidx.navigation:navigation-testing-ktx:$version"
    const val ui = "androidx.navigation:navigation-ui:$version"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:$version"
}

object AndroidArchWork {
    private const val version = "2.5.0"

    const val work_runtime = "androidx.work:work-runtime:$version"
    const val work_runtime_ktx = "androidx.work:work-runtime-ktx:$version"
    const val work_testing = "androidx.work:work-testing:$version"

}

object KotlinCoroutine {
    private const val version = "1.3.9"

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
}

object AssistedInject {
    private const val version = "0.8.1"
    const val annotations_dagger = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
    const val processor_dagger = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
}

object Dagger {
    private const val version = "2.39.1"

    const val core = "com.google.dagger:dagger:$version"
    const val compiler = "com.google.dagger:dagger-compiler:$version"
    const val android_core = "com.google.dagger:dagger-android:$version"
    const val android_support = "com.google.dagger:dagger-android-support:$version"
    const val android_processor = "com.google.dagger:dagger-android-processor:$version"
}

object LeakCanary {
    const val android = "com.squareup.leakcanary:leakcanary-android:2.7"
}

object Moshi {
    private const val version = "1.12.0"
    const val core = "com.squareup.moshi:moshi:$version"
    const val code_gen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
}

object OkHttp {
    private const val version = "4.9.2"
    const val client = "com.squareup.okhttp3:okhttp:$version"
    const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:$version"
}

object Retrofit {
    private const val version = "2.9.0"
    const val core = "com.squareup.retrofit2:retrofit:$version"
    const val moshi_converter = "com.squareup.retrofit2:converter-moshi:$version"
}

object Chuck{
    private const val version = "1.1.0"
    const val core = "com.readystatesoftware.chuck:library:$version"
    const val no_op = "com.readystatesoftware.chuck:library-no-op:$version"
}

object ThreeTenBp {
    const val android = "com.jakewharton.threetenabp:threetenabp:1.3.1"
}

object Coil {
    const val coil = "io.coil-kt:coil:1.3.2"
}

object Glide {
    private const val version = "4.11.0"

    const val runtime = "com.github.bumptech.glide:glide:$version"
    const val compiler = "com.github.bumptech.glide:compiler:$version"
    const val transformations = "jp.wasabeef:glide-transformations:4.0.1"
}

object SqlDelight {
    private const val version = "1.5.2"
    const val gradle_plugin = "com.squareup.sqldelight:gradle-plugin:$version"
    const val android_driver = "com.squareup.sqldelight:android-driver:$version"
    const val runtime = "com.squareup.sqldelight:runtime-common:$version"
    const val flow_plugin = "com.squareup.sqldelight:coroutines-extensions-jvm:$version"
}

object RoomDb{
    private const val room_version = ""
    const val roomRuntime =  "androidx.room:room-runtime:$room_version"
    const val roomCompiler =  "androidx.room:room-compiler:$room_version"
    const val roomRx2 =  "androidx.room:room-rxjava2:$room_version"
    const val roomRx3 =  "androidx.room:room-rxjava3:$room_version"
    const val roomGuava =  "androidx.room:room-guava:$room_version"
    const val roomTestHelper =  "androidx.room:room-testing:$room_version"
    const val roomPaging =  "androidx.room:room-paging:2.4.0-beta01"

}

