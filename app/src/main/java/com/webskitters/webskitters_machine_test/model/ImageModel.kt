package com.webskitters.webskitters_machine_test.model

import com.google.gson.annotations.SerializedName

data class ImageModel (

    @SerializedName("albumId") val albumId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String,
    @SerializedName("thumbnailUrl") val thumbnailUrl : String,
    var check : Boolean = false
)