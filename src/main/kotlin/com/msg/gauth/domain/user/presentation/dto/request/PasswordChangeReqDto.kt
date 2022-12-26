package com.msg.gauth.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class PasswordChangeReqDto(
    @field:NotBlank
    @field:Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&])[A-Za-z[0-9]\$@\$!%*#?&]{8,20}$")
    val password: String,
)