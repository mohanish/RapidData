package com.rapidata.ui.apputils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rapidata.R

class AppBindings {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: AppCompatImageView, url: String?) {
            Glide.with(view.context).load(url)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(view)
        }
    }
}