package com.hexaengineer.cofinder.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hexaengineer.cofinder.core.data.source.local.LocalDataSource
import com.hexaengineer.cofinder.core.data.source.remote.RemoteDataSource
import com.hexaengineer.cofinder.core.data.source.remote.network.ApiResponse
import com.hexaengineer.cofinder.core.data.source.remote.response.DataItem
import com.hexaengineer.cofinder.core.data.source.remote.response.PostPersonalityResponse
import com.hexaengineer.cofinder.core.data.source.remote.response.UserDetailResponse
import com.hexaengineer.cofinder.core.data.source.remote.response.UserItem
import com.hexaengineer.cofinder.core.domain.model.DetailUser
import com.hexaengineer.cofinder.core.domain.model.User
import com.hexaengineer.cofinder.core.domain.model.Personality
import com.hexaengineer.cofinder.core.domain.repository.IUserRepository
import com.hexaengineer.cofinder.core.utils.AppExecutors
import com.hexaengineer.cofinder.core.utils.DataMapper

class UserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllUser(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getAllUser()) {
                    DataMapper.mapUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                //data == null || data.isEmpty()
                true

            override fun createCall(): LiveData<ApiResponse<List<UserItem>>> =
                remoteDataSource.getAllUser()

            override fun saveCallResult(data: List<UserItem>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    override fun getUserDetail(id: String): LiveData<Resource<List<DetailUser>>> =
        object : NetworkBoundResource<List<DetailUser>, List<DataItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<DetailUser>> {
                return Transformations.map(localDataSource.getUserDetail()) {
                    DataMapper.mapDetailUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<DetailUser>?): Boolean =
                //data == null || data.isEmpty()
                true

            override fun createCall(): LiveData<ApiResponse<List<DataItem>>> =
                remoteDataSource.getUserDetail(id)

            override fun saveCallResult(data: List<DataItem>) {
                val userDetail = DataMapper.mapDetailUserResponsesToEntities(data)
                localDataSource.insertUserDetail(userDetail)
            }
        }.asLiveData()

    override fun getDetail(userId: String): LiveData<DetailUser> {
        val userDetail = MutableLiveData<DetailUser>()
        remoteDataSource.getUserDetail(userId, object : RemoteDataSource.LoadUserDetailCallback {
            override fun onAllUserDetailReceived(userItem: UserDetailResponse) {
                val user = DetailUser(
                    id = userItem.data[0].id,
                    personalities = userItem.data[0].personalities,
                    description = userItem.data[0].description,
                    address = userItem.data[0].address,
                    kontak = userItem.data[0].kontak
                )
                userDetail.postValue(user)
            }
        })
        return userDetail
    }

    override fun postPersonality(userId: String, personality: String): LiveData<Personality> {
        val userPersona = MutableLiveData<Personality>()
        remoteDataSource.postPersonality(userId, personality,object : RemoteDataSource.LoadPersonalityCallback{
            override fun onPersonalityReceived(userItem: PostPersonalityResponse) {
                val personality = Personality(
                    data = userItem.data,
                    ext_prediction = userItem.ext_prediction,
                    neu_prediction = userItem.neu_prediction,
                    agr_prediction = userItem.agr_prediction,
                    con_prediction = userItem.con_prediction,
                    opn_prediction = userItem.opn_prediction,
                    time_consumed = userItem.time_consumed
                )
                userPersona.postValue(personality)
            }
        })
        return userPersona
    }
}

