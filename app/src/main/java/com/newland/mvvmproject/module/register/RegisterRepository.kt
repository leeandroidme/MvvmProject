package com.newland.mvvmproject.module.register

import com.newland.mvvmproject.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterRepository(private val api: ApiService) {
    suspend fun register(username: String, password: String, repass: String) =
        withContext(Dispatchers.IO) {
            api.register(username, password, repass)
        }
}