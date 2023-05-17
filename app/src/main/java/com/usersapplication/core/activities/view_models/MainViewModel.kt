package com.usersapplication.core.activities.view_models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //  private @ApplicationContext val context: Context, // memory leak
)  : ViewModel(){
    init {

    }
}