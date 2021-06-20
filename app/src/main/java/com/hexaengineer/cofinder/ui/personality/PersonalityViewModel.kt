package com.hexaengineer.cofinder.ui.personality

import androidx.lifecycle.ViewModel
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase

class PersonalityViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun postPersonality(id: String, personality: String) =
        userUseCase.postPersonality(id, personality)
}