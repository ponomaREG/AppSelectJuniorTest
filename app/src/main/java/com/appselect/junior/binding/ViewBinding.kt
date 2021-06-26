package com.appselect.junior.binding

import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter

@BindingAdapter("toast")
fun bindToast(view: View, message: String?){
    if(message.isNullOrEmpty().not()) Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
}