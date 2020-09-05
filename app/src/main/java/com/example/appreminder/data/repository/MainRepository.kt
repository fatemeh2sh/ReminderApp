package com.example.appreminder.data.repository

import com.example.appreminder.data.local.ReminderDao
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(private var reminderDao: ReminderDao) {

    suspend fun insertReminder(item:Reminder){
        reminderDao.insert(item)
    }

    suspend fun deleteAll(){
        reminderDao.deleteAll()
    }

    suspend fun delete(id:Int){
        reminderDao.delete(id)
    }

    fun get(id:Int): Flow<Resource<Reminder>>{
        return flow {

            emit(Resource.loading(null))
            emit(reminderDao.get(id).map {
                if (it != null ) {
                    Resource.success(it)
                } else {
                    Resource.error("Error DataBase",null)
                }
            })
        }.flowOn(Dispatchers.IO) as Flow<Resource<Reminder>>
    }

    fun getAll(): Flow<Resource<List<Reminder>>> {
        return flow {

            emit(Resource.loading(null))
            emitAll(reminderDao.getAll().map {
                if (!it.isNullOrEmpty()) {
                    Resource.success(it)
                } else {
                    Resource.error("Error DataBase",null)
                }
            })

        }.flowOn(Dispatchers.IO) as Flow<Resource<List<Reminder>>>
    }
}