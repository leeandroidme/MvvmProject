package com.newland.mvvmproject.module.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newland.mvvmproject.module.register.RegisterRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository) : ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun register(username: String, password: String, repass: String) {
        viewModelScope.launch {
            flow {
                emit(repository.register(username, password, repass))
            }.catch {
                message.value = it.message
            }.onStart {
                message.value = "注册开始了。。。"
            }.onCompletion {
                message.value = "注册完成了。。。"
            }.collectLatest {
                message.value = it.message()
            }
        }
    }
}