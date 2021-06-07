package com.webskitters.webskitters_machine_test.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.webskitters.webskitters_machine_test.R

fun ImageView.loadImage(uri: GlideUrl) {
    val options = RequestOptions()
        .error(R.drawable.ic_baseline_error_outline_24)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}


fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

