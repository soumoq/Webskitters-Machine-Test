package com.webskitters.webskitters_machine_test.viewModel

import ImageModel
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.webskitters.webskitters_machine_test.retrofit.ImageService
import com.webskitters.webskitters_machine_test.retrofit.RestApiServiceBuilder
import com.webskitters.webskitters_machine_test.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel : ViewModel() {
    val imageList = MutableLiveData<List<ImageModel>>()

    private val languageService = RestApiServiceBuilder().buildService(ImageService::class.java)

    fun getImage(context: Context){
        val response: Call<List<ImageModel>> = languageService.getLanguage()
        response.enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    imageList.value = response.body()
                } else {
                    context.toast("Something went wrong!")
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("LogTag", "onFailure: ")
                context.toast("Something went wrong!!")
            }
        })

    }

}