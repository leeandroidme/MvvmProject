package com.newland.mvvmproject.module.register

import com.newland.mvvmproject.db.dao.HistoryDao
import com.newland.mvvmproject.db.entity.History
import com.newland.mvvmproject.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent

class RegisterRepository(private val api: ApiService) {
    private val historyDao by KoinJavaComponent.inject(HistoryDao::class.java)
    suspend fun register(username: String, password: String, repass: String) =
        withContext(Dispatchers.IO) {
            api.register(username, password, repass)
        }

    suspend fun insertHistory(history: History) {
        withContext(Dispatchers.IO) {
            historyDao.insertHistory(history)
        }
    }

    suspend fun getHistory(user_id: String): History {
        return historyDao.queryHistorys(user_id)
    }
}