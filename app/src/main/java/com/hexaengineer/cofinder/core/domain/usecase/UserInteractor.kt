package com.hexaengineer.cofinder.core.domain.usecase

import com.hexaengineer.cofinder.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

    override fun getUserDetail(id: String) = userRepository.getUserDetail(id)

    override fun getDetail(id: String) = userRepository.getDetail(id)

    override fun postPersonality(id: String, personality: String) =
        userRepository.postPersonality(id, personality)
}