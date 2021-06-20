package com.hexaengineer.cofinder.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hexaengineer.cofinder.core.data.source.remote.network.ApiResponse
import com.hexaengineer.cofinder.core.data.source.remote.network.ApiService
import com.hexaengineer.cofinder.core.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllUser(): LiveData<ApiResponse<List<UserItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserItem>>>()

        //get data from remote api
        val client = apiService.getUserList()

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val dataArray = response.body()?.user
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getUserDetail(id: String): LiveData<ApiResponse<List<DataItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<DataItem>>>()

        //get data from remote api
        val client = apiService.getUserDetail(id)

        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                val dataArray = response.body()?.data
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getUserDetail(userId: String, callback: LoadUserDetailCallback) {
        apiService.getUserDetail(userId)
            .enqueue(object : Callback<UserDetailResponse> {
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    response.body()?.let { callback.onAllUserDetailReceived(it) }
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    Log.d("error: ", t.printStackTrace().toString())
                }
            })
    }

    fun postPersonality(userId: String, words: String, callback: LoadPersonalityCallback) {
        apiService.postPersonality(userId, words)
            .enqueue(object : Callback<PostPersonalityResponse> {
                override fun onResponse(
                    call: Call<PostPersonalityResponse>,
                    response: Response<PostPersonalityResponse>
                ) {
                    response.body()?.let { callback.onPersonalityReceived(it) }
                    Log.e("Personality: ", "onSuccess: ${response.message()}")
                }

                override fun onFailure(call: Call<PostPersonalityResponse>, t: Throwable) {
                    Log.e("Personality: ", "onFailure: ${t.message.toString()}")
                }
            })
    }

    interface LoadUserDetailCallback {
        fun onAllUserDetailReceived(userItem: UserDetailResponse)
    }

    interface LoadPersonalityCallback {
        fun onPersonalityReceived(userItem: PostPersonalityResponse)
    }
}