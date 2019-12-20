package com.dev.ccodetest.platform

/**
 * Custom result object.
 */
class LiveDataWrapper<T>(val responseRESPONSESTATUS: RESPONSESTATUS, val response: T? = null, val error: Throwable? = null) {

    enum class RESPONSESTATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> loading() = LiveDataWrapper<T>(RESPONSESTATUS.LOADING)
        fun <T> success (data: T) = LiveDataWrapper<T>(RESPONSESTATUS.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataWrapper<T>(RESPONSESTATUS.ERROR, null, err)
    }
}