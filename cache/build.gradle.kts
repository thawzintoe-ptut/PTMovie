apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Module.data))
    // SqlDelight
    "implementation"(SqlDelight.android_driver)
    "implementation"(SqlDelight.flow_plugin)
    // Moshi
    "implementation"(Moshi.core)
    "kapt"(Moshi.code_gen)

    // JavaxInject
    "implementation"(CommonLibs.javaxInject)

    // AndroidX Extension
    "implementation"(AndroidXCore.core_ktx)

    // Dagger
    "implementation"(Dagger.core)
    "kapt"(Dagger.compiler)

    //
    "implementation"(ThreeTenBp.android)
}
