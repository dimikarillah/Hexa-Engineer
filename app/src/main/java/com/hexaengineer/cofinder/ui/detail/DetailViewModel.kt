package com.hexaengineer.cofinder.ui.detail

import androidx.lifecycle.ViewModel
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetail(id: String) = userUseCase.getDetail(id)
}