package com.ptut.appbase.core.mvp

class InvalidMvpImplementationException : Throwable() {

  override val message: String?
    get() = "MVP implementation is not correctly implemented"

}