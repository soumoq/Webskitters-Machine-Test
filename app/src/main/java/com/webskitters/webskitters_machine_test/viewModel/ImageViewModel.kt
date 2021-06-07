package com.webskitters.webskitters_machine_test.viewModel

import android.content.Context
import retrofit2.Call
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.webskitters.webskitters_machine_test.model.ImageModel
import com.webskitters.webskitters_machine_test.retrofit.ImageService
import com.webskitters.webskitters_machine_test.retrofit.RestApiServiceBuilder
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.view.dialog.ProgressDialog
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel : ViewModel() {
    val imageList = MutableLiveData<List<ImageModel>>()


    fun getImage(context: Context){
        val progressDialog = ProgressDialog(context)
        progressDialog.setCancelable(false)
        progressDialog.show()

        val languageService = RestApiServiceBuilder().buildService(ImageService::class.java)
        val response: Call<List<ImageModel>> = languageService.getLanguage()
        response.enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                progressDialog.dismiss()
                if (response.isSuccessful) {
                    imageList.value = response.body()
                } else {
                    context.toast("Something went wrong!")
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("LogTag", "onFailure: ")
                context.toast("Something went wrong!!")
            }
        })

    }

}