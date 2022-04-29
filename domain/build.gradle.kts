plugins {
    id("kotlin")
}

dependencies {
    "api"(CommonLibs.javaxInject)
    "api"(KotlinCoroutine.core)
    "api"(ThreeTenBp.android)
}
