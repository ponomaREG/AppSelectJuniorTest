package com.appselect.junior.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.appselect.junior.extension.loadByUrl

@BindingAdapter("url")
fun bindLoadImageByUrl(view: ImageView, url: String?){
    if(url.isNullOrEmpty().not()) view.loadByUrl(url!!)
}