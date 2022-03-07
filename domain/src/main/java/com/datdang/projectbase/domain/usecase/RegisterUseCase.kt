package com.datdang.projectbase.domain.usecase

import com.datdang.projectbase.domain.model.RegistrationForm
import com.datdang.projectbase.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase.Action<RegistrationForm> {

    override fun execute(param: RegistrationForm): Single<Unit> {
        return userRepository.register(param)
    }
}
