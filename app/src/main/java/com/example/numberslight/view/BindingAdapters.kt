package com.example.numberslight.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val HTTP_PREFIX = "http://"
private const val HTTPS_PREFIX = "https://"

@BindingAdapter("url")
fun loadImage(imageView: ImageView, url: String?) {
    var imageUrl = url
    if (imageUrl?.startsWith(HTTP_PREFIX) == true) {
        imageUrl = url?.replace(HTTP_PREFIX, HTTPS_PREFIX)
    }
    Picasso.get().load(imageUrl).into(imageView)
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView?, adapter: RecyclerView.Adapter<*>?) {
    adapter?.let {
        recyclerView?.adapter = it
    }
}