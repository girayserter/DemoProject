package com.example.demoproject

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.lang.Exception


object BindingAdapter {

    @BindingAdapter("setImageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }

    @BindingAdapter("setBackgroundUrl")
    @JvmStatic
    fun setBackgroundUrl(constraintLayout: ConstraintLayout, url: String) {
        Picasso.get().load(url).into(object :Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                constraintLayout.background = BitmapDrawable(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }
}