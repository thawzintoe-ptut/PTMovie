plugins {
    id("kotlin")
}

dependencies {
    "api"(project(Module.domain))
    "implementation"(ThreeTenBp.android)
}
