package com.webskitters.webskitters_machine_test.retrofit

import com.webskitters.webskitters_machine_test.model.ImageModel
import retrofit2.Call
import retrofit2.http.GET

interface ImageService {
    @GET("photos")
    fun getLanguage(): Call<List<ImageModel>>
}