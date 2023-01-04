package com.msg.gauth.domain.auth.services

import com.msg.gauth.domain.auth.repository.RefreshTokenRepository
import com.msg.gauth.domain.user.utils.UserUtil
import com.msg.gauth.global.annotation.service.TransactionalService

@TransactionalService
class LogoutService(
    private val userUtil: UserUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun execute() {
        val currentUser = userUtil.fetchCurrentUser()
        refreshTokenRepository.findByUserId(currentUser.id)?.let {
            refreshTokenRepository.delete(it)
        }
    }
}