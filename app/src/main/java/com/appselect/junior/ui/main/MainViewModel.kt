package com.appselect.junior.ui.main

import androidx.lifecycle.ViewModel
import com.appselect.junior.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

}