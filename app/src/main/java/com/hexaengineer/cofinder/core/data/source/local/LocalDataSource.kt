package com.hexaengineer.cofinder.core.data.source.local

import androidx.lifecycle.LiveData
import com.hexaengineer.cofinder.core.data.source.local.entity.UserDetailEntity
import com.hexaengineer.cofinder.core.data.source.local.entity.UserEntity
import com.hexaengineer.cofinder.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllUser(): LiveData<List<UserEntity>> = userDao.getAllUser()

    fun getUserDetail(): LiveData<List<UserDetailEntity>> = userDao.getUserDetail()

    fun insertUser(userList: List<UserEntity>) = userDao.insertUser(userList)

    fun insertUserDetail(userDetail: List<UserDetailEntity>) = userDao.insertUserDetail(userDetail)

}