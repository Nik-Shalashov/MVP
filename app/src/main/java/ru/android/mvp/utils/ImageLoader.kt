package ru.android.mvp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun load(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}