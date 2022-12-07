package com.wemax.mtech.di

import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    private val userRepository: UserRepository,
    private val emailService: EmailService
) {

    fun registerUser(email: String,password: String){
        userRepository.saveUser(email,password)
        emailService.sendEmail(email)
    }

}