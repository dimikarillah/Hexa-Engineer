package com.hexaengineer.cofinder.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hexaengineer.cofinder.core.data.source.local.entity.UserDetailEntity
import com.hexaengineer.cofinder.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(tourism: List<UserEntity>)

    @Query("SELECT * FROM user_detail")
    fun getUserDetail(): LiveData<List<UserDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetail(tourism: List<UserDetailEntity>)
}
