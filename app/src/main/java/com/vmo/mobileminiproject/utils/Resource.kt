package com.vmo.mobileminiproject.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int? = 0, val codeString: String? = "") {
    companion object {
        fun <T> success(data: T?): Resource<T> {

            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?, code: Int): Resource<T> {

            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> error(msg: String, data: T?, code: Int, codeString: String): Resource<T> {

            return Resource(Status.ERROR, data, msg, code, codeString)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    constructor(status: Status, data: T?, message: String?): this(status, data, message, 0)
}
