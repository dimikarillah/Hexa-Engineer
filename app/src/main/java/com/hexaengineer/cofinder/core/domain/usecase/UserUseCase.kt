package com.hexaengineer.cofinder.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hexaengineer.cofinder.core.data.Resource
import com.hexaengineer.cofinder.core.domain.model.DetailUser
import com.hexaengineer.cofinder.core.domain.model.Personality
import com.hexaengineer.cofinder.core.domain.model.User

interface UserUseCase {
    fun getAllUser(): LiveData<Resource<List<User>>>

    fun getUserDetail(id: String): LiveData<Resource<List<DetailUser>>>

    fun getDetail(id: String): LiveData<DetailUser>

    fun postPersonality(id: String, personality: String): LiveData<Personality>
}