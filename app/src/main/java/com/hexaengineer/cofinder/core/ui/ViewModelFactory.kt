package com.hexaengineer.cofinder.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hexaengineer.cofinder.core.di.Injection
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase
import com.hexaengineer.cofinder.ui.detail.DetailViewModel
import com.hexaengineer.cofinder.ui.home.HomeViewModel
import com.hexaengineer.cofinder.ui.personality.PersonalityViewModel

class ViewModelFactory private constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTourismUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(PersonalityViewModel::class.java) -> {
                PersonalityViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}