package com.pekyurek.emircan.presentation.core.extensions

import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.postDelayed
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pekyurek.emircan.R

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(imageView: ImageView, urL: String) {
    imageView.loadImageFromUrl(urL)
}

@BindingAdapter("setHasFixedSize")
fun setHasFixedSize(recyclerView: RecyclerView, value: Boolean) {
    recyclerView.setHasFixedSize(value)
}

@BindingAdapter("setScrollingMovementMethod")
fun setScrollingMovementMethod(textView: TextView, value: Boolean) {
    if (value) textView.movementMethod = ScrollingMovementMethod()
}

@BindingAdapter("updateCoinPrice")
fun updateCoinPrice(textView: TextView, price: String) {
    val oldPrice = textView.text.toString().toDoubleOrNull()
    val newPrice = price.toDoubleOrNull()
    textView.text = price
    when {
        oldPrice == null || newPrice == null -> return
        oldPrice > newPrice -> textView.setTextColor(ContextCompat.getColor(textView.context, R.color.red))
        newPrice > oldPrice -> textView.setTextColor(ContextCompat.getColor(textView.context, R.color.green))
        newPrice == oldPrice -> textView.setTextColor(ContextCompat.getColor(textView.context, R.color.blue))
    }
    return
}