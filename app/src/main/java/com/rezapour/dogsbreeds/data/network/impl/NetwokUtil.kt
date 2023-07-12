package com.rezapour.dogsbreeds.data.network.impl

import retrofit2.Response

internal fun <T> Response<T>.isResponseValid(): Boolean {
    return body() != null
}