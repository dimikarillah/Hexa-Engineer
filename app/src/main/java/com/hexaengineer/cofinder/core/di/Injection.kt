package com.hexaengineer.cofinder.core.di

import android.content.Context
import com.hexaengineer.cofinder.core.data.UserRepository
import com.hexaengineer.cofinder.core.data.source.local.LocalDataSource
import com.hexaengineer.cofinder.core.data.source.local.room.UserDatabase
import com.hexaengineer.cofinder.core.data.source.remote.RemoteDataSource
import com.hexaengineer.cofinder.core.data.source.remote.network.ApiConfig
import com.hexaengineer.cofinder.core.domain.repository.IUserRepository
import com.hexaengineer.cofinder.core.domain.usecase.UserInteractor
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase
import com.hexaengineer.cofinder.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IUserRepository {
        val database = UserDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UserRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): UserUseCase {
        val repository = provideRepository(context)
        return UserInteractor(repository)
    }
}
