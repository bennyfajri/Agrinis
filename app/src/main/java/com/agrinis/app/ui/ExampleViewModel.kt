package com.agrinis.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agrinis.app.data.models.response.FooResponse
import com.agrinis.app.network.Resource
import com.agrinis.app.repository.ExampleRepository
import kotlinx.coroutines.launch

/**
 * @author Created by Arca International on 21/11/2022
 */
class ExampleViewModel(
    private val repository: ExampleRepository
) : ViewModel() {

    val showLoading = MutableLiveData<Boolean>()

    private var _fooResponse = MutableLiveData<Resource<FooResponse>>()
    val fooResponse: LiveData<Resource<FooResponse>>
        get() = _fooResponse

    fun foo() = viewModelScope.launch {
        showLoading.value = true
        repository.foo()
            .collect {
                showLoading.value = false
                _fooResponse.value = it
            }
    }
}