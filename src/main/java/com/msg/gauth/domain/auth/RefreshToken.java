package com.msg.gauth.domain.auth;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@RedisHash
@AllArgsConstructor
public class RefreshToken {
    @Id
    Long userId;

    @Indexed
    String token;

    @TimeToLive
    Long timeToLive;

    public void updateToken(String token, Long timeToLive) {
        this.token = token;
        this.timeToLive = timeToLive;
    }
}