package com.msg.gauth.domain.auth.presentation

import com.msg.gauth.domain.auth.presentation.dto.request.SignUpDto
import com.msg.gauth.domain.auth.presentation.dto.request.SigninRequestDto
import com.msg.gauth.domain.auth.presentation.dto.response.RefreshResponseDto
import com.msg.gauth.domain.auth.presentation.dto.response.SigninResponseDto
import com.msg.gauth.domain.auth.services.*
import com.msg.gauth.domain.auth.presentation.dto.request.PasswordInitReqDto
import com.msg.gauth.domain.auth.presentation.dto.request.SignupImageDeleteReqDto
import com.msg.gauth.domain.auth.presentation.dto.response.SignupImageResDto
import com.msg.gauth.domain.auth.services.InitPasswordService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val refreshService: RefreshService,
    private val logoutService: LogoutService,
    private val signInService: SignInService,
    private val signUpService: SignUpService,
    private val initPasswordService: InitPasswordService,
    private val signupImageUploadService: SignupImageUploadService,
    private val signupImageDeleteService: SignupImageDeleteService,
) {
    @PatchMapping
    fun refresh(@RequestHeader("RefreshToken") refreshToken: String): ResponseEntity<RefreshResponseDto> =
        ResponseEntity.ok(refreshService.execute(refreshToken))

    @DeleteMapping
    fun logout(): ResponseEntity<Void> {
        logoutService.execute()
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun signin(@Valid @RequestBody signinRequestDto: SigninRequestDto): ResponseEntity<SigninResponseDto> =
        ResponseEntity.ok(signInService.execute(signinRequestDto))


    @PostMapping("/signup")
    fun signUpMember(@Valid @RequestBody signUpDto: SignUpDto): ResponseEntity<Void> {
        signUpService.execute(signUpDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("/image")
    fun uploadSignupImage(@RequestParam("image") image: MultipartFile): ResponseEntity<SignupImageResDto> =
        ResponseEntity.ok(signupImageUploadService.execute(image))

    @DeleteMapping("/image")
    fun deleteSignupImage(@RequestBody signupImageDeleteReqDto: SignupImageDeleteReqDto): ResponseEntity<Void>{
        signupImageDeleteService.execute(signupImageDeleteReqDto)
        return ResponseEntity.ok().build()
    }


    @PatchMapping("/password/initialize")
    fun initPassword(@Valid @RequestBody passwordInitReqDto: PasswordInitReqDto): ResponseEntity<Void> {
        initPasswordService.execute(passwordInitReqDto)
        return ResponseEntity.noContent().build()
    }
}