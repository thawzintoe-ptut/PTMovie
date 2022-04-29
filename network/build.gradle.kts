apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Module.data))
    "implementation"(Dagger.compiler)

    // OkHttp
    "implementation"(OkHttp.client)
    "implementation"(OkHttp.logger)

    // Retrofit
    "api"(Retrofit.core)
    "implementation"(Retrofit.moshi_converter)

    // Dagger
    "implementation"(Dagger.core)
    "implementation"(Dagger.compiler)

    // JavaxInject
    "implementation"(CommonLibs.javaxInject)

    // Logging
    "implementation"(CommonLibs.timber)

}
