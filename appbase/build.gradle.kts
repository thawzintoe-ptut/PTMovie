apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "api"(project(Module.domain))
    "api"(project(Module.data))
    "api"(project(Module.cache))
    "api"(project(Module.network))
}
