package com.hexaengineer.cofinder.ui.home

import androidx.lifecycle.ViewModel
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {
    val user = userUseCase.getAllUser()
}