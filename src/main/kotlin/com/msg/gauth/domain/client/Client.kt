package com.msg.gauth.domain.client

import com.msg.gauth.domain.user.User
import com.msg.gauth.global.entity.BaseIdEntity
import javax.persistence.*

@Entity
class Client(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val serviceName: String,
    val serviceUri: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val createdBy: User
): BaseIdEntity()